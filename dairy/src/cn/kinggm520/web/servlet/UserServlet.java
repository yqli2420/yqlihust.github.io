package cn.kinggm520.web.servlet;

import cn.kinggm520.domain.User;
import cn.kinggm520.service.UserService;
import cn.kinggm520.service.impl.UserServiceImpl;
import cn.kinggm520.util.PropertiesUtil;
import cn.kinggm520.util.UuidUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-02-07 21:59
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");

        //        获取用户头像图片地址
        String imageUrl = PropertiesUtil.getValue("diary.properties", "imageFile");


        if ("preSave".equals(action)) {

            //            修改个人信息
            request.setAttribute("mainPage", "user/userSave.jsp");
            request.getRequestDispatcher("main.jsp").forward(request, response);

        } else if ("save".equals(action)) {
//            保存个人信息
//            上传头像
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = null;

            try {
                items = upload.parseRequest(request);


            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            Iterator<FileItem> iterator = items.iterator();
            User user = currentUser;
            String filePath = "";
            boolean imageChange = false;
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    if ("nickName".equals(fieldName)) {
                        user.setNickName(item.getString("utf-8"));
                    }
                    if ("mood".equals(fieldName)) {
                        user.setMood(item.getString("utf-8"));
                    }
                } else if (!"".equals(item.getName())) {
//                    上传文件
                    imageChange = true;
                    try {
                        String imageName = user.getUserName();
                        user.setImageName(imageName + "." + item.getName().split("\\.")[1]);

                        filePath = PropertiesUtil.getValue("diary.properties", "imagePath") + imageName + "." + item.getName().split("\\.")[1];
                        item.write(new File(filePath));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }


            int i = userService.userUpdate(user);
            if (i != 0) {

                session.setAttribute("currentUser", user);
//                session.setAttribute(currentUser.getUserName(), imageUrl + currentUser.getImageName());


                response.sendRedirect(request.getContextPath() + "/mainServlet?all=true");

            } else {
//                修改失败
                request.setAttribute("currentUser", user);
                request.setAttribute("error", "保存失败！");
                request.setAttribute("mainPage", "userServlet/userSave.jsp");
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }


        }


    }


}
