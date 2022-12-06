<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file = "commonlist.jsp" %>
<!DOCTYPE html>
<html>
<body>
    <header>
        <div class = "account">
            <%-- 세션 O --%>
            <c:if test="${!empty sessionScope.LoginBean} ">
                <a href="<%-- 로그아웃 경로 --%>">LOGOUT</a>
                <c:if test="${sessionScope.LoginBean.authority == '개인'}">
                    <a href="<%-- 개인 마이페이지 경로  --%>"> <img src="<%-- 마이페이지 이미지 경로 --%>"></a>
                </c:if>
                <c:if test="${sessionScope.LoginBean.authority == '기업'}">
                    <a href="<%-- 기업 마이페이지 경로 --%>"> <img src="<%-- 마이페이지 이미지 경로 --%>"></a>
                </c:if>
            </c:if>

            <%-- 세션 X --%>
            <c:if test="${empty sessionScope.LoginBean}">
                <a href="<%-- 로그인 메인 페이지 경로 --%>">LOGIN</a>
                <a href="<%-- 회원가입 페이지 경로 --%>">JOIN</a> <%-- 출력 유무 --%>
                <%-- 마이페이지 이미지만 걸어두고 로그인으로 보냄--%>
                <a href="<%-- 로그인 메인 페이지 경로 --%>"> <img src="<%-- 마이페이지 이미지 경로 --%>"> </a>
            </c:if>
        </div>
        <div class = "logo"></div>
        <nav>

        </nav>
    </header>
</body>
</html>