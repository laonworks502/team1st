<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file = "../common/commonlist.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기 폼</title>

	<%--[외부 파일 ] --%>
	<link rel="stylesheet"
		  href="/resources/css/decopwfind.css">

	<script>
		function check() {
			if($.trim($("#email").val())==""){
				alert("회원 이메일 주소를 입력해주세요.");
				$("#email").val("").focus();
				return false;
			}
			if($.trim($("#name").val())==""){
				alert("회원 이름을 입력해주세요.");
				$("#name").val("").focus();
				return false;
			}
		}
	</script>

</head>
<body>
<div id="pwd_find_wrap">

	<%--[empty인 경우]--%>
	<c:if test="${empty pwdok}">
	<div class="case1">
		<h2 class="pwd_title">비밀 번호 찾기</h2>
		<form method="post" action="pwfind_ok" onsubmit="return check()">
			<table id="member_pwd_find_t">
				<div class="id_find_part">
					<dt>이메일 주소</dt>
					<dd><input type="text" name="email" id="email" size="50" class="input_box" /></dd>
				</div>
				<div class="name_find_part">
					<dt>회원 이름</dt>
					<dd><input type="text" name="name" id="name" size="10" class="input_box" /></dd>
				</div>
			</table>
			<div id="pwd_find_menu">
				<input type="submit" value="비밀 번호 찾기" class="passwd_find_btn" />
				<input type="reset" value="취소 " class="passwd_find_c_btn"
					   onclick="$('#email').focus();"/>
			</div>
			<div id="pwd_find_close">
				<input type="button" value="닫기" class="passwd_close_btn1"
					   onclick="self.close();" />
			</div>
		</form>
	</div>
	</c:if>


	<%--[empty가 아닌 경우  : 메일을 보내고 나면 empty가 아닌 경우가 됨] --%>
	<c:if test="${!empty pwdok}">
	<div class="case2">
		<h2 class="pwd_title2">비밀 번호 찾기 결과</h2>
		<table id="pwd_t2">
			<div class="passwd_result_part">
				<dt>검색한 비밀 번호  :</dt>
				<dd>${pwdok}</dd>
			</div>
		</table>
		<div id="pwd_close2">
			<input type="button" value="닫기" class="passwd_close_btn2"
				   onclick="self.close();" />
		</div>
	</div>
	</c:if>
</body>
</html>