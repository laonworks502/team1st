<%--
  Created by IntelliJ IDEA.
  User: jcbgm
  Date: 11/28/2022
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="/common/commonlist.jsp"%>--%>
<!DOCTYPE html>
<head>
<%--   --%>
    <title>관리자 로그인</title>
</head>
<body>
<form>
    <!-- Email input -->
    <div class="form-outline mb-4">
        <label class="form-label" for="form2Example1">관리자 아이디</label>
        <input type="email" id="form2Example1" class="form-control" style="width:200px;height:30px;"/>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
        <label class="form-label" for="form2Example2">관리자 비밀번호</label>
        <input type="password" id="form2Example2" class="form-control" style="width:200px;height:30px;"/>
    </div>

    <!-- Submit button -->
    <button type="button" class="btn btn-primary btn-block mb-4">로그인</button>

</form>
</body>
</html>
