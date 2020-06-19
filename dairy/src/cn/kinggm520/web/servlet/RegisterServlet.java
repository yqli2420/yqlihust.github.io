package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.DiaryType;
import cn.kinggm520.domain.User;
import cn.kinggm520.service.DiaryTypeService;
import cn.kinggm520.service.RegisterService;
import cn.kinggm520.service.impl.DiaryTypeServiceImpl;
import cn.kinggm520.service.impl.RegisterServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-07 21:59
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
   private RegisterService registService = new RegisterServiceImpl();
    private DiaryTypeService diaryTypeService = new DiaryTypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //BeanUtils封装JavaBean
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        获取验证码
        String v_code = request.getParameter("v_code");

        HttpSession session = request.getSession();
        Object v_code1 = session.getAttribute("v_code");
        session.removeAttribute("v_code");

        if(v_code1!=null&&v_code1.toString().equalsIgnoreCase(v_code)){ //验证码正确
            int flag=registService.isExitService(user);
            if ( flag== 0) {
                DiaryType diaryType = new DiaryType();
                diaryType.setTypeName("默认分类");
                user.setJoinDate(new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss").format(new Date()));
                int i = registService.registerService(user);
                int j =diaryTypeService.diaryTypeAdd(diaryType,user);

                if (i != 0&&j!=0) {
//                注册成功
                    request.setAttribute("user",user);
                    request.setAttribute("feedBack",1);
                    request.getRequestDispatcher("register.jsp").forward(request,response);

                } else {
//                注册失败
                    request.setAttribute("user",user);
                    request.setAttribute("feedBack",2);
                    request.getRequestDispatcher("register.jsp").forward(request,response);


                }

            }


            if(flag==1){
//            用户名已存在 注册失败
                request.setAttribute("user",user);
                request.setAttribute("feedBack",3);
                request.getRequestDispatcher("register.jsp").forward(request,response);

            }


             }else {
//            验证码错误
            request.setAttribute("user",user);
            request.setAttribute("feedBack",4);
            request.getRequestDispatcher("register.jsp").forward(request,response);
             }



    }


}
