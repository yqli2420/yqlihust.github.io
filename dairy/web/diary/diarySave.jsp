<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
    function checkForm(){
        var title=document.getElementById("title").value;
        var content=CKEDITOR.instances.content.getData();
        var typeId=document.getElementById("typeId").value;
        if(title==null||title==""){
            document.getElementById("error").innerHTML="标题不能为空！";
            return false;
        }
        if(content==null||content==""){
            document.getElementById("error").innerHTML="内容不能为空！";
            return false;
        }
        if(typeId==null||typeId==""){
            document.getElementById("error").innerHTML="请选择日志类别！";
            return false;
        }
        return true;
    }
</script>

<style>
    .diary_title{
        text-align: center;
    }

    .diary_type{
        margin-top: 20px;

    }

    .sel{
        text-align: center;
        padding: 5px;
    }

    .div_btn{
        margin-top: 20px;
    }
</style>

<div class="data_list">
<div class="data_list_title">
<c:choose>
    <c:when test="${diary.diaryId!=null }">
        <img src="${pageContext.request.contextPath}/image/diary_type_edit_icon.png"/>
        修改日记</div>
    </c:when>

    <c:otherwise>
        <img src="${pageContext.request.contextPath}/image/diary_add_icon.png"/>
        写日记</div>
    </c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/diaryServlet?action=save" method="post" onsubmit="return checkForm()">
    <div>
        <div class="diary_title"><input type="text" id="title"  name="title" value="${diary.title }" class="input-xlarge"  style="margin-top:10px;height:40px;width: 80%"  placeholder="日志标题..."/></div>
        <div style="margin-top: 20px">
            <textarea class="ckeditor " id="content" name="content">${diary.content }</textarea>
        </div>

        <div class="diary_type">

                <select class="sel" id="typeId" name="typeId">
                    <option value="">请选择日志类别...</option>
                    <c:forEach var="diaryTypeCount" items="${diaryTypeCountList }">
                        <option value="${diaryTypeCount.diaryTypeId }" ${diaryTypeCount.diaryTypeId==diary.typeId?'selected':'' }>${diaryTypeCount.typeName }</option>
                    </c:forEach>
                </select>

        </div>


        <div class="div_btn">
            <input type="hidden" id="diaryId" name="diaryId" value="${diary.diaryId }"/>

            <input type="submit" class="btn btn-default" value="保存"/>

            <button class="btn btn-default" type="button" onclick="javascript:history.back()">返回</button>
            <font id="error" color="red">${error }</font>
        </div>



    </div>
</form>
</div>