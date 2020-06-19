<%@ page import="cn.kinggm520.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

if(request.getAttribute("user")==null) {     /*第一次进入登录页 才去获取cookie*/

    String userName = null;
    String password = null;
    Cookie[] cookies = request.getCookies();

    if (cookies != null && cookies.length != 0) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().contains("remember")) {
                String[] strings = cookies[i].getValue().split("&");
                userName = strings[0];
                password = strings[1];

            }

        }

        if (userName == null) {
            userName = "";
        }

        if (password == null) {
            password = "";
        }


        pageContext.setAttribute("user",new User(userName,password));
    }

}
%>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>随风日记本登录页</title>

    <!-- Bootstrap -->
    <link href="./bootStrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="./bootStrap/js/jquery-3.2.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="./bootStrap/js/bootstrap.min.js"></script>

    <!--引入插件相关文件-->
    <link href="./validator/jquery.validator.css" rel="stylesheet">
    <script src="./validator/jquery-3.3.1.min.js"></script>
    <script src="./validator/jquery.validator.min.js"></script>
    <!--中文国际化文件-->
    <script src="./validator/local/zh-CN.js"></script>

    <style>
        body {
            background-image: url(./image/login.jpg);
            margin: 0;
            background-size: 100% 100%;
            background-attachment: fixed;
        }

        .div1 {
            padding: 20px;
            padding-top: 60px;
            width: 300px;
            height: 400px;
            background: rgba(0, 0, 0, 0.6);
            border-radius: 5px;
            box-shadow: 0 0 20px #0007f3;

            /*div居中显示*/
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -200px 0 0 -150px
        }

        h2 {
            color: white;
            margin: auto;
            font-family: 幼圆;
        }

    </style>
</head>

<body>

<div class="div1">
    <div style="text-align: center">
        <h2>用户登录</h2>
    </div>

    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">

        <div class="form-group ">
            <div class="col-sm-12">
                <input type="text" style="margin-top: 30px" class="form-control" id="userName" name="userName"
                       placeholder="请输入用户名" value="${user.userName}" data-rule="用户名:required">
            </div>
            <div class="col-sm-12">
                <span class="msg-box" for="userName"></span>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-12">
                <input type="password" name="password" style="margin-top: 10px" class="form-control" placeholder="请输入密码"
                       value="${user.password}" data-rule="密码:required">
            </div>
            <div class="col-sm-12">
                <span class="msg-box" for="password"></span>
            </div>
        </div>

        <label style=" color: white;  text-align: left;" class="checkbox">
            <input style="margin-left: 5px;" id="remember" name="remember" type="checkbox" value="remember-me">&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;记住密码 &nbsp;&nbsp;&nbsp;&nbsp; <font style="font-family: 幼圆" id="error"
                                                                                    color="red">${error}</font>
        </label>
        <br/>

        <div class="form-group">
            <div class="col-sm-12" STYLE="text-align: center">
                <button type="submit" class="btn btn-default">登录</button>
                <a href="${pageContext.request.contextPath}/register.jsp" style="margin-left: 20px" class="btn btn-default">注册</a>
            </div>
        </div>
    </form>

</div>
</body>
</html>
