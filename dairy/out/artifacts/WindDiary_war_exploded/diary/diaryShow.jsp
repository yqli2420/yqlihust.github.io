<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .diary_title {
        text-align: center;
        margin-top: 10px;
    }

    .diary_info {
        text-align: center;

    }

    .diary_content {
        margin-top: 20px;

    }

    .diary_action {

        margin-top: 20px;
    }


</style>

<script type="text/javascript">
    function diaryDelete(diaryId) {
        if (confirm("您确定要删除这个日记吗？")) {
            window.location = "${pageContext.request.contextPath}/diaryServlet?action=delete&diaryId=" + diaryId;
        }
    }
</script>
<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/image/diary_show_icon.png"/>
        日记信息
    </div>
    <div>
        <div class="diary_title"><h3>${diary.title }</h3></div>
        <div class="diary_info">
            发布时间：『${diary.releaseDate}』&nbsp;&nbsp;日记类别：${diary.typeName}
        </div>
        <div class="diary_content">
            ${diary.content}
        </div>
        <div class="diary_action">
            <button class="btn btn-default" type="button"
                    onclick="javascript:window.location='${pageContext.request.contextPath}/diaryServlet?action=preSave&diaryId=${diary.diaryId}'">修改日记
            </button>
            <button class="btn btn-default" type="button" onclick="javascript:history.back()">返回</button>
            <button class="btn btn-danger" type="button" onclick="diaryDelete(${diary.diaryId})">删除日记</button>
        </div>
    </div>
</div>