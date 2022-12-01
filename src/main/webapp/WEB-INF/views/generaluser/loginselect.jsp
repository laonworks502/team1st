<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../companyuser/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 선택</title>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/loginForm.css">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

</head>
<body class="text-center">

<main class="form-signin w-800 m-auto">


    <button class="w-300 btn btn-lg btn-primary" type="submit" onclick="location='generalloginForm'">일반</button>
    <button class="w-300 btn btn-lg btn-primary" type="submit" onclick="location='companyuser/loginForm'">기업</button>

</main>

</body>
</html>
