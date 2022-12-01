<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${boardName} 게시판</title>
</head>
<body>
<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4">${boardName} 게시판</h1>
        <h5> ${pg.postsTotal}개의 글이 기다리고 있어요!</h5>


        <div class="card mb-4">
            <div class="card-header">
                <button type="button" class="btn btn-primary float-end" onClick="location.href='/postwrite/${board_id}/${pg.page}';"> <!-- <i class="fas fa-table me-1"></i> -->
                    <i class="fas fa-edit"></i>글 작성
                </button>
            </div>
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${postList}" var="postList">
                        <tr onclick="location.href='/posts/${board_id}/${page}/${postList.no}'">
                            <td><button type="button" class="btn btn-outline-warning">스크랩</button></td>
                            <td>${postList.title}</td>
                            <td>${postList.writer}</td>
                            <td>${postList.date}</td>
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
    <ul class="pagination">
        <li class="page-item">
        <c:if test="${pg.page>1}">
            <a class="page-link" href="/boards/${board_id}/${pg.page-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </c:if>
        </li>
        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
            <li class="page-item" id="page-item${i}">
                <a class="page-link" href="/boards/${board_id}/${i}">i</a>
            </li>
                <script>
                    const pageItem=document.getElementById("page-item${pg.page}");
                    pageItem.classList.add('active')
                </script>
        </c:forEach>
        <li class="page-item">
            <c:if test="${pg.page<pg.pagesTotal}">
                <a class="page-link" href="/boards/${board_id}/${pg.page+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>
</body>
</html>