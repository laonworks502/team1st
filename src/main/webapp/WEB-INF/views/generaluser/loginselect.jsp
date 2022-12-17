<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 선택</title>

    <link rel="stylesheet" href="/resources/css/loginForm.css">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

</head>
<body class="text-center">

<%@ include file = "../common/header.jsp" %>
    <main class="form-signin w-800 m-auto">

        <table>
            <tr>
                <div>
                    <td>
                        <button class="btn btn-primary btn-lg" type="button" style="width: 200px;" onclick="location='general-login-form'">일반</button>
                    </td>

                    <td style="width: 50px;">
                    </td>
                    <td>
                        <button class="btn btn-warning btn-lg" type="button" style="width: 200px;" onclick="location='company-login-form'">기업</button>
                    </td>
                </div>
            </tr>
        </table>
        <%--    class="w-50 btn btn-lg btn-primary"--%>
    </main>

</body>
</html>
