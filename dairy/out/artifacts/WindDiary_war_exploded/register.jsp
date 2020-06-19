<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>随风日记本注册页</title>

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
            background-image: url(./image/regist.jpg);
            margin: 0;
            background-size: 100% 100%;
            background-attachment: fixed;
        }

        .div1 {
            color: white;
            padding: 10px;
            padding-top: 13px;
            width: 340px;
            height: 520px;
            background: rgba(0, 0, 0, 0.6);
            border-radius: 5px;
            box-shadow: 0 0 20px #0007f3;

            /*div居中显示*/
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -260px 0 0 -170px

        }

        /*设置placeholder属性*/
        ::-webkit-input-placeholder { /* Chrome/Opera/Safari */
            font-size: 10px;
            color: red;
        }

        ::-moz-placeholder { /* Firefox 19+ */
            font-size: 10px;
            color: red;
        }

        :-ms-input-placeholder { /* IE 10+ */
            font-size: 10px;
            color: red;
        }

        :-moz-placeholder { /* Firefox 18- */
            font-size: 10px;
            color: red;
        }

        input {
            margin-top: 5px;
        }

        label {
            font-size: 12px;
            font-family: 幼圆;
        }

        font {
            font-family: 幼圆;
            border: 1px solid red;
            border-radius: 5px;
            background-color: rgba(255, 69, 0, 0.6);
            color: white;
            padding: 10px;

        }
    </style>
</head>

<body>

<div class="div1">
    <div style="text-align: center">
        <h2 style="font-family: 幼圆">新用户注册</h2>
    </div>
    <form class="form-horizontal" style="margin-top: 15px" method="post" action="${pageContext.request.contextPath}/register"

    >
        <div class="form-group ">
            <label for="username" class="col-sm-3 control-label">用户名</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}"
                       placeholder="请输入用户名(2~10位字符)"
                       data-rule="用户名:required;us;remote[${pageContext.request.contextPath}/findUserServlet]"
                       data-rule-us="[/^\S{2,10}$/, '请检查用户名格式(2~10个字符)']">
            </div>
            <div class="col-sm-offset-3 col-sm-12">
                <span class="msg-box" for="userName"></span>
            </div>
        </div>


        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">密码</label>
            <div class="col-sm-8">
                <input type="password" name="password" value="${user.password}" class="form-control" id="password"
                       placeholder="请输入密码(6~12位字母数字下划线)" data-rule="密码:required;ps"
                       data-rule-ps="[/^[a-zA-Z0-9_]{6,12}$/, '请检查密码格式(6~12位字母数字下划线)']">
            </div>
            <div class="col-sm-offset-3 col-sm-12">
                <span class="msg-box" for="password"></span>
            </div>
        </div>


        <div class="form-group">
            <label for="conf_password" class="col-sm-3 control-label">确认密码</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="conf_password" placeholder="请再次输入密码"
                       data-rule="确认密码:required;match(password)" name="repassword" value="${user.password}">
            </div>
            <div class="col-sm-offset-3 col-sm-12">
                <span class="msg-box" for="conf_password"></span>
            </div>
        </div>

        <%--验证码--%>
        <div class="form-group">

            <label for="conf_password" class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="v_code" placeholder="请输入验证码"
                       data-rule="验证码:required" name="v_code">
            </div>
            <div class="col-sm-offset-3 col-sm-12">
                <span class="msg-box" for="v_code"></span>
            </div>

        </div>


        <div class="col-sm-offset-3 col-sm-12">
            <img src="${pageContext.request.contextPath}/checkCodeServlet" id="img"
                 style="width: 90px;height: 40px ;border-radius: 3px;border: 1px solid #1b6d85"> <a style="color: white"
                                                                                                    href="javascript:void(0)"
                                                                                                    id="a">看不清?换一张</a>
        </div>

        <script>
            var img = document.getElementById("img");
            img.onclick = function () {
                img.src = "${pageContext.request.contextPath}/checkCodeServlet?" + new Date().getTime();
            }

            var a = document.getElementById("a");
            a.onclick = function () {
                img.src = "${pageContext.request.contextPath}/checkCodeServlet?" + new Date().getTime();
            }
        </script>

        <br/>

        <div style="text-align: center">
            <button type="submit" class="btn btn-default" style="margin-top: 10px">确定</button>
        </div>
        <br/>


        <c:choose>
            <c:when test="${feedBack==1}">
                <br>
                <div style="text-align: center">
                    <a href="login.jsp" class="btn btn-default" style="color: blue; text-underline: none;">注册成功，点击登录</a>
                    <br>
                    <p style="color: #0007f3; margin-top: 2px"><span id="time">5</span>秒后自动跳转到登录页...</p>
                </div>

                <script>
                    var ss = 4;
                    var second = document.getElementById("time");

                    function fun() {
                        second.innerHTML = ss-- + '';
                        if (ss < 0) {
                            second.innerHTML = '0';
                            location.href = "login.jsp";
                            interval.clearInterval();
                        }
                    }

                    var interval = setInterval(fun, 1000);

                </script>

            </c:when>


            <c:when test="${feedBack==2}">
                <br>
                <div style="text-align: center">
                    <font>注册失败，请重试</font>
                </div>

            </c:when>


            <c:when test="${feedBack==3}">
                <br>
                <div style="text-align: center">
                    <font>注册失败，用户名已存在</font>
                </div>
            </c:when>

            <c:when test="${feedBack==4}">
                <br>
                <div style="text-align: center">
                    <font>验证码错误，请重新输入</font>
                </div>
            </c:when>


        </c:choose>


    </form>


</div>


</body>


</html>
