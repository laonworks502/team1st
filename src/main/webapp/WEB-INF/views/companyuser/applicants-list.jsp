<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지원자 목록</title>
</head>
<body>

<%@ include file = "../common/header.jsp" %>
<script>
    function getResume(email){
        $(function () {
            $.ajax({
                type:"get",
                url:"/apply/resume",
                data: { "email" : email },
                dataType:"json",
                success:function (result) {
                    alert("다운로드 성공")
                },
                error:function () {
                    alert("다운로드 실패");
                }

            });
        });

    }

</script>
<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4">${applicants.no}번 글의 지원자 목록</h1>

        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>지원자명</th>
                        <th>지원시간</th>
                        <th>이력서 다운로드</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach  var="applicants" items="${applicants}">
                        <tr>
                            <td style="width: 1000px">${applicants.name}</td>
                            <td style="width: 150px">${applicants.date}</td>
                            <td style="width: 200px"><button type="button" class="btn btn-primary"
                                onclick="getResume(${applicants.email})">다운로드</button></td>
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