<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 회원 수정</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>   
<script src="/resources/js/companyuser_update.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<input type="hidden" name="passwd"  id="passwd" value="${cub.passwd}">
<input type="hidden" name="salt"  id="salt" value="${cub.salt}">	
	<form action="companyuserupdate_ok" onsubmit="return check()" method="post" align="center" style="margin-top: 15%;">
		<h2>기업 회원 수정</h2>
			<table align="center">
				<tr>
					<th>기업명</th>
					<td><input type="text" name="company_name" value="${cub.company_name}" readonly="readonly"style="border:none"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td> <input type="text" name="email" id="email" value="${cub.email}" readonly="readonly"style="border:none"/></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="passconfirm" id="passconfirm"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" id="name" value="${cub.name}"></td>
				</tr>
				<tr>
					<td>담당자 연락처</td>
    				 <td>
     				<input name="tel1" id="tel1" size="3" maxlength="3" class="input_box" value="${cub.tel1}"/>
    				 -<input name="tel2" id="tel2" size="4" maxlength="4" class="input_box" value="${cub.tel2}"/>
    				 -<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" value="${cub.tel3}"/></td>
				</tr>
				<tr>
					<td colspan="3"><br><br>
						<input type="submit" class="btn btn-info" value="수정">
						<button type="reset" class="btn btn-secondary">초기화</button></td>
				</tr>
			</table>
	</form>

</body>


</html>