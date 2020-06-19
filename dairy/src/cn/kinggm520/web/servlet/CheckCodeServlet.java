package cn.kinggm520.web.servlet;

import cn.kinggm520.service.CheckCodeService;
import cn.kinggm520.service.impl.CheckCodeServiceImpl;
import cn.kinggm520.util.IdentifyCode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckCodeService checkCodeService= new CheckCodeServiceImpl();

        String code = checkCodeService.checkCodeService(response.getOutputStream()); //获取验证码字符串 并存入session
        HttpSession session = request.getSession();
        session.setAttribute("v_code",code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
