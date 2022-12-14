<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<header>

    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                <div class="logo">로고자리</div>
            </a>

            <%--<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
                <li><a href="#" class="nav-link px-2 link-dark">Features</a></li>
                <li><a href="#" class="nav-link px-2 link-dark">Pricing</a></li>
                <li></li>
                <li></li>
            </ul>--%>

            <div class="col-md-3 text-end">
                <%-- 세션 O --%>
                <c:if test="${not empty sessionScope.loginBean}">
                    <a href="/loginselect">LOGOUT</a>
                    <c:if test="${sessionScope.loginBean.authority == '일반'}">
                        <a href="/generalmypage"> <img src="<%=request.getContextPath()%>/images/IconMypage.png"
                                                              width="32" height="32"></a>
                    </c:if>
                    <c:if test="${sessionScope.loginBean.authority == '기업'}">
                        <a href="/companymypage"> <img src="<%=request.getContextPath()%>/images/IconMypage.png"
                                                              width="32" height="32"></a>
                    </c:if>
                </c:if>

                <%-- 세션 X --%>
                <c:if test="${empty sessionScope.loginBean}">
                    <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/loginselect'">
                        Login
                    </button>
                    <%-- 마이페이지 이미지만 걸어두고 로그인으로 보냄--%>
                    <a href="/loginselect"> <img src="<%=request.getContextPath()%>/images/IconMypage.png" width="32"
                                                 height="32"> </a>
                </c:if>
            </div>
        </div>
    </div>

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