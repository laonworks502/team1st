<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp"%>
<html>
<head>
    <title>마이 페이지</title>
</head>
<body>
<header></header>
<main>
    <div class="myscrab_minilist_wrap">
        <ul class="subject">
            <h3>내 스크랩</h3>
        </ul>
        <ul class="myscrab_mini_wrab">
            <li class="board_subject">
                <h4>정규 게시판</h4>
            </li>
            <div class="myscrab_mini_obj">
                <c:forEach var="myminiscrap100" items="${myminiscrap100}">
                    ${myminiscrap100.title}
                </c:forEach>
            </div>
            <li class="board_subject">
                <h4>단기 게시판</h4>
            </li>
            <div class="myscrab_mini_obj">
                <c:forEach var="myminiscrap200" items="${myminiscrap200}">

                </c:forEach>
            </div>
            <li class="board_subject">
                <h4>스터디 게시판</h4>
            </li>
            <div class="myscrab_mini_obj">
                <c:forEach var="myminiscrap300" items="${myminiscrap300}">

                </c:forEach>
            </div>
        </ul>
    </div>
</main>

</body>
</html>

