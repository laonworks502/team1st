<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내가 작성한 글 목록</title>
</head>
<body>

<div class="card-body" style="margin: 10%;">
    <h2>내가 작성한 글 목록</h2>
    <button class="btn btn-warning" type="button" style="float: right" onclick="location='/scrap/generalmypage'">마이페이지</button>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th>게시판</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="posts">
            <tr
                    onclick="location.href='/boards/${posts.board_id}/${posts.no}?page=${pg.page}'">
                <td>
                    <c:set var="board_id" value="${posts.board_id}" />

                    <c:if test="${board_id eq '300'}">
                    <a>스터디 게시판</a>
                    </c:if>

                </td>
                <td>${posts.title}</td>
                <td>${posts.writer}</td>
                <td>${posts.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- 페이징 -->
<nav class="center" aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item"><c:if test="${pg.page>1}">
            <a class="page-link" href="/general-boardlist?page=${pg.page-1}"
               aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
            </a>
        </c:if></li>
        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
            <li class="page-item" id="page-item${i}"><a class="page-link"
                                                        href="/general-boardlist?page=${i}">${i}</a></li>
        </c:forEach>
        <script>
            const pageItem = document.getElementById("page-item${pg.page}");
            pageItem.classList.add('active')
        </script>
        <li class="page-item"><c:if test="${pg.page < pg.pagesTotal}">
            <a class="page-link" href="/general-boardlist?page=${pg.page+1}"
               aria-label="Next"> <span aria-hidden="true">&raquo;</span>
            </a>
        </c:if></li>
    </ul>
</nav>
</body>
</html>