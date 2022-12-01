<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file = "./common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <main style="margin: 500px;">
        <div class="col-2"></div>
        <table>
            <tr>
                <td style="padding-right: 30px">
                    <button class="btn btn-outline-primary btn-lg" type="button" onclick="location.href='/boards/100'">정규직 구인 게시판</button>
                </td>
                <td style="padding-right: 30px">
                    <button class="btn btn-outline-secondary btn-lg" type="button" onclick="location.href='/boards/200'">계약직 구인 게시판</button>
                </td>
                <td>
                    <button class="btn btn-outline-success btn-lg" type="button" onclick="location.href='/boards/300'">스터디 모임 게시판</button>
                </td>
            </tr>
        </table>
        <div class="col-2"></div>
    </main>
</body>
</html>