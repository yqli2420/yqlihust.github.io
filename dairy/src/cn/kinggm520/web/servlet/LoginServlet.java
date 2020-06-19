package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.User;
import cn.kinggm520.service.LoginService;
import cn.kinggm520.service.impl.LoginServiceImpl;
import cn.kinggm520.util.PropertiesUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-07 21:59
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private   LoginService loginService = new LoginServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        //BeanUtils封装JavaBean
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User currentUser = loginService.loginService(user);

        if (currentUser == null) {
            request.setAttribute("error", "用户名或密码错误");
            request.setAttribute("user", user);
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            if ("remember-me".equals(request.getParameter("remember"))) {
                Cookie remember = new Cookie("remember", user.getUserName() + "&" + user.getPassword());
                remember.setMaxAge(3600 * 24 * 7);   // 7天
                response.addCookie(remember);
            }

//            currentUser.setImageName(PropertiesUtil.getValue("diary.properties", "imageFile") +currentUser.getImageName());

            session.setAttribute("currentUser", currentUser);
//            request.getRequestDispatcher("mainServlet").forward(request, response);

            response.sendRedirect(request.getContextPath()+"/mainServlet");
        }


    }

}
