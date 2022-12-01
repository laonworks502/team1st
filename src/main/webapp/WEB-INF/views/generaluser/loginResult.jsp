<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../companyuser/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${result == 1 }">
	<script>
		alert("가입된 아이디 또는 비밀번호가 아닙니다. \n 확인 후 다시 입력해 주세요.");
		history.go(-1);
	</script>
</c:if>

<c:if test="${result == 2 }">
	<script>
		alert("회원 정보가 틀렸습니다. \n 확인 후 다시 입력해주세요.");
		history.go(-1);
	</script>
</c:if>

</body>
</html>