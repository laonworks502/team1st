<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file = "./commonlist.jsp" %>
<!DOCTYPE html>
<html>
<header>
    <nav class="nav nav-pills nav-justified">
        <c:forEach var="boardList" items="${sessionScope.boardList}">
            <a class="nav-link" id="${boardList.id}" href="/boards/${boardList.id}">${boardList.name} 게시판</a>
            <c:if test="${boardList.id == board.id}">
                <script>
                    const navActive = document.getElementById("${boardList.id}");
                    navActive.classList.add('active')
                </script>
            </c:if>
        </c:forEach>
    </nav>
</header>

</html>