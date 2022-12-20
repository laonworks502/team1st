<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자 로그인</title>

<style>
	.title{
		margin-top: 150px;
	}
	.form-control{
		margin: 0 auto;
		text-align: center;
	}
</style>

<script>
	 function check() {
		if ($.trim($("#id").val()) == "") {
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}
		if ($.trim($("#passwd").val()) == "") {
			alert("비밀번호를 입력해주세요");
			$("#passwd").focus();
			return false;
		}
	} 
</script>
</head>
<body>
	<div class="wrapper">
		<div class="inner-warpper text-center">
			<h2 class="title">관리자 로그인</h2>
			<br> <br>
			<form action="<%=request.getContextPath()%>/adminlogin" method="post" onsubmit="return check()">
				<input type="text" class="form-control" name="id" id="id"
					placeholder="관리자 아이디" style="width: 400" /> <br> <input
					type="password" class="form-control" name="passwd" id="passwd"
					placeholder="관리자 비밀번호" style="width: 400" /> <br>
				<br>
				<button type="submit" class="btn btn-primary" id="login">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>