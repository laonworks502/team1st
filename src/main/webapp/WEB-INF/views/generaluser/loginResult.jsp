<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!--[JSTL]-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- [springframework] -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- [JQuery] -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>

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
		alert("비밀번호가 틀렸습니다. \n 확인 후 다시 입력해주세요.");
		history.go(-1);
	</script>
</c:if>

<c:if test="${result == 3 }">
	<script>
		alert("수정이 완료되었습니다.");
		location.href="/scrap/generalmypage";
	</script>
</c:if>

<c:if test="${result == 4 }">
	<script>
		alert("탈퇴가 완료되었습니다.");
		location.href="/";
	</script>
</c:if>

<c:if test="${result == 4 }">
    <script>
        alert("탈퇴가 완료되었습니다.");
        location.href="/";
    </script>
</c:if>


</body>
</html>