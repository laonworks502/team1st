<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp"%>
<html>
<head>
    <title>마이 스크랩 더보기</title>
</head>
<body>
<header></header>
<main>
    <div class="myscrab_detaillist">

        <ul class="subject">
            <h3>내 전체 스크랩</h3>
        </ul>
        <div class="myscrap_obj">

            <li class="board_subject">
                <h4>${boardName} 게시판</h4>
            </li>
            <c:forEach var="myscrap" items="${myscrap}">
                ${myscrap.title}
                ${myscrap.content}
                ${myscrap.date}
            </c:forEach>
        </div>
        <div class="myscrap_pageination">
            <c:if test="${listcount>0}">
                <!-- 1페이지로 이동 -->
                <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=1" style="text-decoration:none">
                    <i class="bi bi-chevron-double-left"></i>
                </a>

                <!-- 이전 블럭으로 이동 -->
                <c:if test="${startPage > 10}">
                    <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${stratPage-10}">
                        <i class="bi bi-chevron-left"></i>
                    </a>
                </c:if>

                <!-- 각 블럭에 10개의 페이지 출력 -->
                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <c:if test="${i==page}">&nbsp;&nbsp;${i}&nbsp;&nbsp;</c:if> <!-- 현재페이지 -->
                    <c:if test="${i!=page}"><a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${i}">
                        &nbsp;&nbsp;${i}&nbsp;&nbsp;</a></c:if> <!-- 현재페이지가 아닌 경우 -->
                </c:forEach>

                <!-- 다음 블럭으로 이동 -->
                <c:if test="${endPage < pageCount}">
                    <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${startPage+10}">
                        <i class="bi bi-chevron-right"></i>
                    </a>
                </c:if>

                <!-- 마지막페이지로 이동 -->
                <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${pageCount}" style="text-decoration:none">
                    <i class="bi bi-chevron-double-right"></i>
                </a>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
