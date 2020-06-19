<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var nickName=document.getElementById("nickName").value;
		if(nickName==null||nickName==""){
			document.getElementById("error").innerHTML="昵称不能为空！";
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/image/user_edit_icon.png"/>
		个人信息设置</div>
	<div class="row" style="padding-top: 20px;">

		<div class="col-lg-4" style="text-align: center">
			<c:if test="${not empty currentUser.imageName}">
				<img class="user_image1" src="userImages/${currentUser.imageName}">
			</c:if>

			<%--显示默认头像--%>
			<c:if test="${ empty currentUser.imageName}">
				<img class="user_image1" src="${pageContext.request.contextPath}/image/123.jpg">
			</c:if>

		</div>

		<div class="col-lg-8" style="margin-top: 10px">
			<form action="${pageContext.request.contextPath}/userServlet?action=save" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
				<table width="100%">
					<tr>
						<td width="20%">个人头像：</td>
						<td><input type="file" id="imagePath" name="imagePath"/></td>
					</tr>

					<tr style="height: 10px"></tr>

					<tr>
						<td>个人昵称：</td>
						<td><input type="text" id="nickName" name="nickName" value="${currentUser.nickName }" style="margin-top:5px;height:30px;"/></td>
					</tr>

					<tr style="height: 10px"></tr>

					<tr>
						<td valign="top">个人心情：</td>
						<td>
							<textarea id="mood" name="mood" rows="10" style="width: 100%">${currentUser.mood }</textarea>
						</td>
					</tr>
					<tr style="height: 10px"></tr>
					<tr>
						<td><button class="btn btn-primary" type="submit">保存</button></td>
						<td><button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;<font id="error" color="red">${error }</font></td>
					</tr>
				</table>
			</form>
		</div>


	</div>
</div>