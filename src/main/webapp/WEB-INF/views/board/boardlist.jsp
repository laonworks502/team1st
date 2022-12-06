<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${board.name} 게시판</title>
</head>
<body>
<%@ include file = "../common/header.jsp" %>
<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4">${board.name} 게시판</h1>
        <h5> ${pg.postsTotal}개의 글이 기다리고 있어요!</h5>


        <div class="card mb-4">
            <div class="card-header">
                <button type="button" class="btn btn-primary float-end" 
                	onClick="location.href='/boards/${board.id}/write?page=${pg.page}'">글 작성
                </button>
            </div>
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>스크랩</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${posts}" var="posts">
                        <tr onclick="location.href='/boards/${board.id}/${posts.no}?page=${pg.page}'">
                            <td><button type="button" class="btn btn-outline-warning">스크랩</button></td>
                            <td>${posts.title}</td>
                            <td>${posts.writer}</td>
                            <td>${posts.date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<!-- 페이징 -->
<nav class="center" aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item">
        <c:if test="${pg.page>1}">
            <a class="page-link" href="/boards/${board.id}?page=${pg.page-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </c:if>
        </li>
        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
            <li class="page-item" id="page-item${i}">
                <a class="page-link" href="/boards/${board.id}?page=${i}">${i}</a>
            </li>
                <script>
                    const pageItem=document.getElementById("page-item${pg.page}");
                    pageItem.classList.add('active')
                </script>
        </c:forEach>
        <li class="page-item">
            <c:if test="${pg.page<pg.pagesTotal}">
                <a class="page-link" href="/boards/${board.id}?page=${pg.page+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>
</body>
</html>