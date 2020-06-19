package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryService;
import cn.kinggm520.service.impl.DiaryServiceImpl;
import com.alibaba.druid.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
@WebServlet("/diaryServlet")
public class DiaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");
        String diaryId = request.getParameter("diaryId");
        DiaryService diaryService = new DiaryServiceImpl();


        if ("show".equals(action)) {
            try {
                Diary diary = diaryService.diaryShow(currentUser, diaryId);

                request.setAttribute("diary", diary);
                request.setAttribute("mainPage", "diary/diaryShow.jsp");
                request.getRequestDispatcher("main.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if ("preSave".equals(action)) {

            if (!StringUtils.isEmpty(diaryId)) {
                Diary diary = diaryService.diaryShow(currentUser, diaryId);
                request.setAttribute("diary", diary);
            }

//            修改日记页面
            request.setAttribute("mainPage", "diary/diarySave.jsp");
            request.getRequestDispatcher("main.jsp").forward(request, response);


        } else if ("save".equals(action)) {
//            保存日记
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String typeId = request.getParameter("typeId");


            Diary diary = new Diary(title, content, Integer.parseInt(typeId), currentUser.getUserName());

            if (!StringUtils.isEmpty(diaryId)) {
//                diaryId不为空  表示修改日记
                diary.setDiaryId(Integer.parseInt(diaryId));
            }


            int saveNums = 0;

            if (!StringUtils.isEmpty(diaryId)) {
//                diaryId不为空  表示修改日记
                 saveNums=diaryService.diaryUpdate(diary);
            } else {
//                为空表示添加
                saveNums = diaryService.diaryAdd(diary, currentUser);
            }


            if (saveNums > 0) {
//                request.getRequestDispatcher("mainServlet?all=true").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/mainServlet?all=true");
            } else {
                request.setAttribute("diary", diary);
                request.setAttribute("error", "保存失败！");
                request.setAttribute("mainPage", "diary/diarySave.jsp");
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }


        } else if ("delete".equals(action)) {
//            删除日记
            int i = diaryService.diaryDelete(diaryId);
            if (i != 0) {
//                删除成功
                response.sendRedirect(request.getContextPath() + "/mainServlet?all=true");


            } else {
//                删除失败
            }


        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
