<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 탈퇴</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/companyuser_delete.js"></script>

</head>
<body>
	<div align="center" style="margin-top: 10%;">
	<form method="post" action="generaluserdelete_ok" onsubmit="return check()" name="deleteform">
	<input type="hidden" name="email" value="${sessionScope.email}">
	<input type="hidden" name="passwd"  id="passwd" value="${gub.passwd}">
			<p>
				<a style="font-size: 15pt; font-weight: bold;">'${gub.email}'</a>
				계정을 정말 탈퇴하시겠습니까?<br>
				<a style="font-size: 9pt; font-weight:bold; color: red;">* 탈퇴 후에는 되돌릴 수 없습니다.</a>
			</p>
			<table>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="passconfirm" id="passconfirm"></td>
			</tr>
			<tr>
				<th>탈퇴사유</th>
				<td>(필수) 향후 더 나은 서비스 제공을 위해 탈퇴사유를 입력해주세요.<br>
				<textarea name="exit_reason" id="exit_reason" style="width:95%;" rows="4"></textarea></td>
			</tr>
			</table>
			<div >
				<input type="submit" class="btn btn-danger" value="탈퇴">
				<input type="button" class="btn btn-outline-primary btn-sm" value="돌아가기" onclick="history.go(-1)">
				
			</div>
		</form>
	</div>


</body>
</html>