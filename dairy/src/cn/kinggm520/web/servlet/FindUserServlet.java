package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.User;
import cn.kinggm520.service.RegisterService;
import cn.kinggm520.service.impl.RegisterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-05 13:12
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setContentType("application/json;charset=utf-8");
        String username = request.getParameter("userName");
        RegisterService registerService = new RegisterServiceImpl();
        User user=new User();
        user.setUserName(username);
        int i = registerService.isExitService(user);
        Map<String,String> map = new HashMap<>();

        if(i==0){
//            用户名可用
            map.put("ok","用户名可用");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),map);
        }

        if(i==1){
//            用户名不可用
            map.put("error","用户名已存在,换一个试试！");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),map);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
