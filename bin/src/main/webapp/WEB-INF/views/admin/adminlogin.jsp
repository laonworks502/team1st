<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp" %>
<!DOCTYPE html>
<head>
    <title>관리자 로그인</title>
</head>
<body>
<%--<h2>${errorMessage}</h2>--%>
<form method="post">
    <input type="text" name="adminid" placeholder="관리자 아이디"/>
    <br><br>
    <input type="password" name="adminpw" placeholder="관리자 비밀번호"/>
    <br><br>
    <button>Submit</button>
</form>
<%--<form method="post" action="adminloginok" id="form">--%>
<%--    <!-- Email input -->--%>
<%--    <div class="form-outline mb-4">--%>
<%--        <label class="form-label" for="adminid">관리자 아이디</label>--%>
<%--        <input type="text" id="adminid" class="form-control" style="width:200px;height:30px;"/>--%>
<%--    </div>--%>

<%--    <!-- Password input -->--%>
<%--    <div class="form-outline mb-4">--%>
<%--        <label class="form-label" for="adminpw">관리자 비밀번호</label>--%>
<%--        <input type="password" id="adminpw" class="form-control" style="width:200px;height:30px;"/>--%>
<%--    </div>--%>

<%--    <!-- Submit button -->--%>
<%--    <button type="submit" class="btn btn-primary btn-block mb-4">로그인</button>--%>

<%--</form>--%>
<%--<script>--%>
<%--    const form = document.getElementById('form');--%>
<%--    const adminId = document.getElementById('adminid');--%>
<%--    const adminPw = document.getElementById('adminpw');--%>

<%--    form.addEventListener('submit', function(e){--%>
<%--        e.preventDefault();--%>

<%--        const adminIdValue = adminId.value;--%>
<%--        const adminPwValue = adminPw.value;--%>

<%--        localStorage.setItem('adminId', adminIdValue);--%>
<%--        localStorage.setItem('adminPw', adminPwValue);--%>
<%--    })--%>
<%--</script>--%>
</body>
</html>
