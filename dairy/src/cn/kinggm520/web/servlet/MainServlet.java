package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.Diary;
import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.PageBean;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.MainService;
import cn.kinggm520.service.impl.MainServiceImpl;
import cn.kinggm520.util.PropertiesUtil;
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
 * 时间:  2020-02-24 23:55
 */
@WebServlet("/mainServlet")
public class MainServlet extends HttpServlet {

    private MainService mainService = new MainServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();


        String page = request.getParameter("page"); //当前页
        if (StringUtils.isEmpty(page)) {
            page = "1";
        }

//        获取分页每页显示数量
        int pageSize = Integer.parseInt(PropertiesUtil.getValue("diary.properties", "pageSize"));

//        获取用户头像图片地址
        String imageUrl = PropertiesUtil.getValue("diary.properties", "imageFile");

//        获取日志类别id
        String s_typeId = request.getParameter("s_typeId");

//        获取日志创建年月
        String s_releaseDate = request.getParameter("s_releaseDate");

//        封装查询条件  为了按时间和类别分页显示
        Diary diary = new Diary();


//        获取搜索文本框的值
        String s_title = request.getParameter("s_title");

//        获取搜索标记
        String all = request.getParameter("all");

        if ("true".equals(all)) {  //点击了搜索按钮
            if (!StringUtils.isEmpty(s_title)) { //且搜索框值不为空
                diary.setTitle(s_title);
            }
            //                必须移除session的值
            session.removeAttribute("s_releaseDate");
            session.removeAttribute("s_typeId");

//            将全局模糊查询条件放入session
            session.setAttribute("s_title", s_title);
        } else {

//            设置分类查询条件  日志类别 和时间
            if (!StringUtils.isEmpty(s_typeId)) {
                diary.setTypeId(Integer.parseInt(s_typeId));
                session.setAttribute("s_typeId", s_typeId);
//            当点击日志类别查询时清除 时间类别的session
                session.removeAttribute("s_releaseDate");

//            当点击日志类别查询时清除 全局模糊查询的session
                session.removeAttribute("s_title");

            }

            if (!StringUtils.isEmpty(s_releaseDate)) {

                diary.setReleaseDate(s_releaseDate);
                session.setAttribute("s_releaseDate", s_releaseDate);
//            当点击时间类别查询时清除日志类别的session
                session.removeAttribute("s_typeId");

//            点击时间类别查询时清除 全局模糊查询的session
                session.removeAttribute("s_title");

            }

            if (StringUtils.isEmpty(s_typeId)) {
                Object o = session.getAttribute("s_typeId");
                if (o != null) {
                    diary.setTypeId(Integer.parseInt((String) o));
                }
            }

            if (StringUtils.isEmpty(s_releaseDate)) {
                Object o = session.getAttribute("s_releaseDate");
                if (o != null) {
                    diary.setReleaseDate((String) o);
                }
            }

//           判断模糊查询条件是否为空
            if (StringUtils.isEmpty(s_title)) {
                Object o = session.getAttribute("s_title");
                if (o != null) {
                    diary.setTitle((String) o);
                }
            }


        }


        PageBean pageBean = new PageBean(Integer.parseInt(page), pageSize);

//       查询
        User currentUser = (User) request.getSession().getAttribute("currentUser");


        List<Diary> diaries = mainService.diaryListService(currentUser, pageBean, diary);
        int total = mainService.diaryCountService(currentUser, diary);

        String pageCode = mainService.genPagation(total, Integer.parseInt(page), pageSize);

//        获取日志类别信息
        List<DiaryType> list = mainService.diaryTypeCountListService(currentUser);

//        按时间分类
        List<Diary> diaryCountList = mainService.diaryCountListService(currentUser);

        request.setAttribute("totalCount", total);
        request.setAttribute("totalPage", total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        request.setAttribute("pageCode", pageCode);

        request.setAttribute("diaries", diaries);
        session.setAttribute("diaryTypeCountList", list);
        session.setAttribute("diaryCountList", diaryCountList);

//        设置头像地址
//        session.setAttribute("imageName", imageUrl + currentUser.getImageName());



//        session.setAttribute(currentUser.getUserName(), imageUrl + currentUser.getImageName());


//      设置模糊查询数据回显
        request.setAttribute("s_title",s_title);
        request.setAttribute("mainPage", "diary/diaryList.jsp");
        request.getRequestDispatcher("main.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
