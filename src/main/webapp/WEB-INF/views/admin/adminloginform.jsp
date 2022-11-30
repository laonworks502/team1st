<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title class="title">관리자 로그인</title>
    <script>

        function check() {
            if ($.trim($("#adminid").val()) == "") {
                alert("아이디를 입력해주세요");
                $("#adminid").val("").focus();
                return false;
            }
            if ($.trim($("#adminpw").val()) == "") {
                alert("비밀번호를 입력해주세요");
                $("#adminpw").val("").focus();
                return false;
            }
        }

    </script>
</head>
<body>
<div class="wrapper" align="center">
    <div class="inner-warpper text-center">
        <h2 class="title">관리자 로그인</h2><br>
    <br>
    <form action="<%=request.getContextPath()%>/adminloginform" method="post" onsubmit="return check()">
        <input type="text" class="form-control" name="adminid" id="adminid" placeholder="관리자 아이디" style="width:400"/>
        <br>
        <input type="password" class="form-control" name="adminpw" id="adminpw" placeholder="관리자 비밀번호"
               style="width:400"/>
        <br><br>
        <button type="submit" class="btn btn-primary" id="login">로그인</button>
    </form>
</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ include file="../common/commonlist.jsp" %>--%>
<%--<!DOCTYPE html>--%>
<%--<head>--%>
<%--    <title>관리자 로그인</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<h2>${errorMessage}</h2>&ndash;%&gt;--%>
<%--<form method="post">--%>
<%--    <input type="text" name="adminid" placeholder="관리자 아이디"/>--%>
<%--    <br><br>--%>
<%--    <input type="password" name="adminpw" placeholder="관리자 비밀번호"/>--%>
<%--    <br><br>--%>
<%--    <button>Submit</button>--%>
<%--</form>--%>
<%--&lt;%&ndash;<form method="post" action="adminloginok" id="form">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <!-- Email input -->&ndash;%&gt;--%>
<%--&lt;%&ndash;    <div class="form-outline mb-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <label class="form-label" for="adminid">관리자 아이디</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="text" id="adminid" class="form-control" style="width:200px;height:30px;"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </div>&ndash;%&gt;--%>

<%--&lt;%&ndash;    <!-- Password input -->&ndash;%&gt;--%>
<%--&lt;%&ndash;    <div class="form-outline mb-4">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <label class="form-label" for="adminpw">관리자 비밀번호</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="password" id="adminpw" class="form-control" style="width:200px;height:30px;"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </div>&ndash;%&gt;--%>

<%--&lt;%&ndash;    <!-- Submit button -->&ndash;%&gt;--%>
<%--&lt;%&ndash;    <button type="submit" class="btn btn-primary btn-block mb-4">로그인</button>&ndash;%&gt;--%>

<%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script>&ndash;%&gt;--%>
<%--&lt;%&ndash;    const form = document.getElementById('form');&ndash;%&gt;--%>
<%--&lt;%&ndash;    const adminId = document.getElementById('adminid');&ndash;%&gt;--%>
<%--&lt;%&ndash;    const adminPw = document.getElementById('adminpw');&ndash;%&gt;--%>

<%--&lt;%&ndash;    form.addEventListener('submit', function(e){&ndash;%&gt;--%>
<%--&lt;%&ndash;        e.preventDefault();&ndash;%&gt;--%>

<%--&lt;%&ndash;        const adminIdValue = adminId.value;&ndash;%&gt;--%>
<%--&lt;%&ndash;        const adminPwValue = adminPw.value;&ndash;%&gt;--%>

<%--&lt;%&ndash;        localStorage.setItem('adminId', adminIdValue);&ndash;%&gt;--%>
<%--&lt;%&ndash;        localStorage.setItem('adminPw', adminPwValue);&ndash;%&gt;--%>
<%--&lt;%&ndash;    })&ndash;%&gt;--%>
<%--&lt;%&ndash;</script>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>
