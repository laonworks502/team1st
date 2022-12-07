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
                <c:forEach var="boardlist" items="${boardlist}">
                    <td style="padding-right: 30px">
                        <button class="btn btn-outline-primary btn-lg" type="button"
                                onclick="location.href='/boards/${boardlist.id}'">${boardlist.name} 게시판</button>
                    </td>
                </c:forEach>
            </tr>
        </table>
        <div class="col-2"></div>
    </main>
</body>
<script>
location.href= "adminmain";
</script>
</html>