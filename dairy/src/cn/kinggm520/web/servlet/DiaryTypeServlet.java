package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryTypeService;
import cn.kinggm520.service.impl.DiaryTypeServiceImpl;
import com.alibaba.druid.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-07 11:03
 */
@WebServlet("/diaryTypeServlet")
public class DiaryTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");
        String diaryTypeId = request.getParameter("diaryTypeId");
        String typeName = request.getParameter("typeName");

        DiaryTypeService diaryTypeService = new DiaryTypeServiceImpl();

        if ("list".equals(action)) {

            List<DiaryType> diaryTypeList = diaryTypeService.diaryTypeList(currentUser);
            request.setAttribute("diaryTypeList", diaryTypeList);
            request.setAttribute("mainPage", "diaryType/diaryTypeList.jsp");
            request.getRequestDispatcher("main.jsp").forward(request, response);


        } else if ("preSave".equals(action)) {
//            预操作
            if (!StringUtils.isEmpty(diaryTypeId)) {
//                diaryTypeId不为空  查询数据
                DiaryType diaryType = diaryTypeService.diaryTypeShow(diaryTypeId);
                request.setAttribute("diaryType", diaryType);
            }

            request.setAttribute("mainPage", "diaryType/diaryTypeSave.jsp");
            request.getRequestDispatcher("main.jsp").forward(request, response);



        } else if ("save".equals(action)) {
//            保存
            DiaryType diaryType = new DiaryType();
            diaryType.setTypeName(typeName);
            diaryType.setUserName(currentUser.getUserName());

            if (!StringUtils.isEmpty(diaryTypeId)) {
                diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
            }

            int saveNum = 0;

            if (!StringUtils.isEmpty(diaryTypeId)) {
//               修改
                saveNum = diaryTypeService.diaryTypeUpdate(diaryType);

            } else {
//                添加
                saveNum = diaryTypeService.diaryTypeAdd(diaryType, currentUser);

            }

            if (saveNum != 0) {
//                成功
//                request.getRequestDispatcher("diaryTypeServlet?action=list").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/diaryTypeServlet?action=list");


            } else {
//                失败
                request.setAttribute("diaryType", diaryType);
                request.setAttribute("error", "保存失败！");
                request.setAttribute("mainPage", "diaryType/diaryTypeSave.jsp");
                request.getRequestDispatcher("main.jsp").forward(request, response);

            }


        } else if ("delete".equals(action)) {
//            删除
            if (diaryTypeService.existDiaryWithTypeId(diaryTypeId, currentUser)) {
//            该类别下有日记  不能删除该类别
                request.setAttribute("error", "该日记类别下有日记，不能删除！");
                request.getRequestDispatcher("diaryTypeServlet?action=list").forward(request, response);

            } else {

                int i = diaryTypeService.diaryTypeDelete(diaryTypeId);
                if (i != 0) {
                    response.sendRedirect("diaryTypeServlet?action=list");

                } else {
                    request.setAttribute("error", "删除失败！");
                    request.getRequestDispatcher("diaryTypeServlet?action=list").forward(request, response);
                }

            }

        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
