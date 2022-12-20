<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 선택</title>

<%--    <style>--%>
<%--        *{--%>
<%--            padding: 0;--%>
<%--            margin:0;--%>
<%--            padding: 20px;--%>
<%--        }--%>

<%--        body {--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--            display: flex;--%>
<%--            flex-flow: column nowrap;--%>
<%--            justify-content: center;--%>
<%--            align-items: center;--%>
<%--            overflow-x: hidden;--%>
<%--        }--%>

<%--        main {--%>
<%--            width:1180px;--%>
<%--            min-width: 1180px;--%>
<%--            background-color: antiquewhite;--%>
<%--            margin: 0 auto;--%>
<%--        }--%>

<%--        ul,li{--%>
<%--            list-style:none;--%>
<%--        }--%>

<%--    </style>--%>

<%--    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/loginForm.css">--%>
<%--    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">--%>

</head>
<body class="text-center">
<%@ include file = "../common/header.jsp" %>

    <main class="form-signin w-800 m-auto">
<input type="text" name="email" value="${gub.email}" style="border: none" readonly> 로그인성공
        <table>
            <tr>
                <div>
                    <td>
                        <button class="btn btn-primary btn-lg" type="button" style="width: 200px;" onclick="location='/'">메인</button>
                    </td>

                    <td style="width: 50px;">
                    </td>
                    <td>
                        <button class="btn btn-warning btn-lg" type="button" style="width: 200px;" onclick="location='/scrap/generalmypage'">마이페이지</button>
                    </td>
                </div>
            </tr>
        </table>
        <%--    class="w-50 btn btn-lg btn-primary"--%>
    </main>

</body>
</html>
