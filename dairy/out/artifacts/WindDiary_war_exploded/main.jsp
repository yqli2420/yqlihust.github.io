<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>随风日记本</title>

    <!-- Bootstrap -->
    <link href="./bootStrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="./bootStrap/js/jquery-3.2.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="./bootStrap/js/bootstrap.min.js"></script>
    <!--中文国际化文件-->
    <script src="./validator/local/zh-CN.js"></script>

    <%--CKEditor--%>
    <script src="./js/ckeditor/ckeditor.js"></script>

    <style>
        .data_list {
            border: 1px solid #e5e5e5;
            padding: 5px;
            background-color: #fdfdfd;
            margin-top: 3px;
        }

        .data_list_title {
            font-size: 15px;
            font-weight: bold;
            border-bottom: 1px solid #e5e5e5;
            padding-bottom: 3px;
            padding-top: 3px;
        }

        .datas {
            padding: 5px;
        }

        .datas ul {
            list-style-type: none;
        }

        .datas ul li {
            margin-top: 5px;
        }

        .user_image {
            text-align: center;
            padding: 10px;

        }

        .user_image1 {
            width: 200px;
            height: 250px;
            border-radius: 5px;
            border: 2px solid moccasin;
            box-shadow: 0 0 20px moccasin;
        }

        .nickName {
            text-align: center;
            font-size: medium;
            color: #269abc;

        }

        .userSign {
            text-align: center;
            color: black;
        }


    </style>


    <script>

        $(function () {
           if(${currentUser == null}){
               location.href="login.jsp";
            }

        });

    </script>

</head>
<body>
<%--导航栏--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" style="padding: 0" href="${pageContext.request.contextPath}/mainServlet?all=true">
                <img alt="Brand" src="./image/diary.png">
            </a>

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand " href="${pageContext.request.contextPath}/mainServlet?all=true">我的日记本</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/mainServlet?all=true"> <span
                        class="glyphicon glyphicon-apple" aria-hidden="true"></span>&nbsp;主页</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/diaryServlet?action=preSave"> <span
                        class="glyphicon glyphicon-leaf" aria-hidden="true"></span>&nbsp;写日记</a></li>

                <li><a href="${pageContext.request.contextPath}/diaryTypeServlet?action=list"><span
                        class="glyphicon glyphicon-th-large" aria-hidden="true"></span>&nbsp;日记分类管理</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/userServlet?action=preSave"><span
                        class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;个人中心</a></li>

            </ul>

            <form class="navbar-form navbar-right" method="post"
                  action="${pageContext.request.contextPath}/mainServlet?all=true">
                <div class="form-group">
                    <input id="s_title" name="s_title" type="text" class="form-control" placeholder="随风的往事..."
                           value="${s_title}">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"
                                                                    aria-hidden="true"></span>&nbsp;搜索
                </button>
            </form>
        </div>
    </div>
</nav>


<%--页面布局--%>
<div class="container-fluid">

    <div class="row">

        <%--左边日记列表--%>
        <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9">
            <jsp:include page="${mainPage}"></jsp:include>
        </div>


        <%--右边列表--%>
        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">

            <%--个人中心--%>
            <div class="data_list">
                <div class="data_list_title">
                    <img style="vertical-align: middle" src="./image/user_icon.png">
                    个人中心
                </div>

                <div class="user_image">

                    <c:if test="${not empty currentUser.imageName}">
                        <img class="user_image1" src="userImages/${currentUser.imageName}">

                    </c:if>

                    <%--显示默认头像--%>
                    <c:if test="${ empty currentUser.imageName}">
                        <img class="user_image1" src="image/123.jpg">
                    </c:if>


                </div>

                <div class="nickName">
                    ${currentUser.nickName}
                </div>

                <div class="userSign">
                    ${currentUser.mood}
                </div>


            </div>

            <%--按日记类别--%>
            <div class="data_list">
                <div class="data_list_title">
                    <img style="vertical-align: middle" src="./image/byType_icon.png">
                    按日记类别
                </div>

                <div class="datas">
                    <ul>
                        <c:forEach var="diaryTypeCount" items="${diaryTypeCountList}">

                            <li><span><a
                                    href="${pageContext.request.contextPath}/mainServlet?s_typeId=${diaryTypeCount.diaryTypeId}">${diaryTypeCount.typeName}(${diaryTypeCount.diaryCount})</a>  </span>
                            </li>

                        </c:forEach>

                    </ul>

                </div>


            </div>

            <%--按时间分类--%>
            <div class="data_list">
                <div class="data_list_title">
                    <img style="vertical-align: middle" src="./image/byDate_icon.png">
                    按日记时间
                </div>

                <div class="datas">
                    <ul>
                        <c:forEach var="diaryCount" items="${diaryCountList}">

                            <li><span><a
                                    href="${pageContext.request.contextPath}/mainServlet?s_releaseDate=${diaryCount.releaseDate}">${diaryCount.releaseDate}(${diaryCount.diaryCount})</a>  </span>
                            </li>

                        </c:forEach>

                    </ul>

                </div>


            </div>


        </div>


    </div>
</div>

</body>
</html>