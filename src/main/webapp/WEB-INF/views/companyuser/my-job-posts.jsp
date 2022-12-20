<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내가 작성한 구인글</title>
</head>
<body>

<%@ include file = "../common/header.jsp" %>
<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4">내가 작성한 구인글</h1>

        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>지원자 수</th>
                        <th>작성시간</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach  var="posts" items="${posts}">
                        <tr onclick="location.href='/apply/jobposts/${posts.no}'">
                            <td style="width: 1000px">${posts.title}</td>
                            <td style="width: 200px">${posts.applicants}</td>
                            <td style="width: 150px">
                                <fmt:formatDate value="${posts.date}" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
</html>