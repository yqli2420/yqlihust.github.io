<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

    .diary_datas {
        padding: 5px;
    }

    .u {
        list-style-type: none;
    }

    .div_page {
        text-align: center;
    }

</style>

<script>
  function writeDiary() {
      location.href="${pageContext.request.contextPath}/diaryServlet?action=preSave";
  }

</script>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/image/list_icon.png"/>
        日记列表
    </div>

    <div class="diary_datas">
        <ul class="u">
            <c:forEach var="diary" items="${diaries}">

                <li> 『${diary.releaseDate}』&nbsp; <span><a href="${pageContext.request.contextPath}/diaryServlet?action=show&diaryId=${diary.diaryId}">${diary.title}</a></span></li>

            </c:forEach>

        </ul>

    </div>
    <%--分页--%>
    <div class="div_page">

        <nav aria-label="Page navigation">
            <ul class="pagination">

                <c:if test="${totalPage==0}">
                    当前日记列表为空，快去写日记吧！ <button onclick="writeDiary()" class="btn btn-class">写日记</button>
                </c:if>

                <c:if test="${totalPage!=0}">
                    ${pageCode}
                </c:if>

            </ul>
        </nav>

        <c:if test="${totalPage!=0}">
            <p> 共${totalCount}条记录，共${totalPage}页</p>

        </c:if>

    </div>


</div>
