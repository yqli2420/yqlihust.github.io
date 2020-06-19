<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    function diaryTypeDelete(diaryTypeId) {
        if (confirm("您确定要删除这个日志类别吗？")) {
            window.location = "${pageContext.request.contextPath}/diaryTypeServlet?action=delete&diaryTypeId=" + diaryTypeId;
        }
    }

</script>

<style>
    .diaryType_add{
        float: right;
        margin-right: 20px;
    }

</style>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/image/list_icon.png"/>
        日记类别列表
        <span class="diaryType_add">
			<button class="btn btn-xs btn-success" type="button"
                    onclick="javascript:window.location='${pageContext.request.contextPath}/diaryTypeServlet?action=preSave'">添加日记类别</button>
		</span>
    </div>
    <div>
        <table class="table table-hover table-striped">
            <tr>
                <th>编号</th>
                <th>类别名称</th>
                <th>操作</th>
            </tr>


            <c:if test="${diaryTypeList==null||diaryTypeList.size()==0}">
                <tr>
                    <div style="margin-bottom: 20px;margin-top: 20px">当前日记类别列表为空，快去添加吧！
                        <button class="btn btn-xs btn-success" type="button"
                                onclick="javascript:window.location='${pageContext.request.contextPath}/diaryTypeServlet?action=preSave'">添加日记类别</button>
                    </div>
                </tr>
            </c:if>


            <c:if test="${diaryTypeList!=null}">
                <c:forEach var="diaryType" items="${diaryTypeList }" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>${diaryType.typeName }</td>
                        <td>
                            <button class="btn btn-sm btn-info" type="button"
                                    onclick="javascript:window.location='${pageContext.request.contextPath}/diaryTypeServlet?action=preSave&diaryTypeId=${diaryType.diaryTypeId }'">
                                修改
                            </button>&nbsp;&nbsp;
                            <button class="btn btn-sm btn-danger" type="button"
                              onclick="diaryTypeDelete(${diaryType.diaryTypeId })">删除
                        </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


        </table>
    </div>

    <div align="center"><font color="red">${error }</font></div>

</div>
