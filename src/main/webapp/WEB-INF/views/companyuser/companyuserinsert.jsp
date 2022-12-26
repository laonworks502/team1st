<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 회원 가입</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>   
<script src="/resources/js/check.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
.rr{
	padding-left:15px;
	padding-bottom:15px; 
	padding-top:15px
}


</style>

</head>
<body>

	<form action="companyuserinsert_ok" onsubmit="return checkch()" method="post" align="center" style="margin-top: 15%;">

		<h2>기업 회원 가입</h2>
			<table align="center">
				<tr>
					<th class="rr">기업명</th>
					<td class="rr"><input type="text" name="company_name" value="${company_name}" readonly="readonly" style="border:none"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td class="rr"> <input type="text" name="email" id="email" oninput="checkemail()" />
						<br><a style="font-size: 9pt; font-weight:bold; color: red;">* 이메일 형식으로 입력해주세요.</a></td>
           			<td class="rr"> <span class="email_ok" style="color:green; display:none;">사용 가능</span>
           				 <span class="email_already" style="color:red; display:none;">사용 불가능</span>
           			</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td class="rr"><input type="password" name="passwd" id="passwd"></td>
					<td class="rr"><a style="font-size: 9pt; font-weight:bold; color: red;">* 비밀번호 입력 조건<br></a>
						<a style="font-size: 9pt; font-weight:bold; color: black;">8~15자 영문/숫자 조합 </a>
					</td>
					
				</tr>
				<tr>
					<th class="rr">비밀번호 확인</th>
					<td class="rr"><input type="password" name="passconfirm" id="passconfirm" ></td>
				</tr>
				<tr>
					<th class="rr">이름</th>
					<td class="rr"><input type="text" name="name" id="name" ></td>
				</tr>
				<tr>
					<th class="rr">담당자 연락처</th>
    				 <td class="rr">
     				<input name="tel1" id="tel1" size="3" maxlength="3" class="input_box"/>
    				 -<input name="tel2" id="tel2" size="4" maxlength="4" class="input_box" />
    				 -<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" /></td>
				</tr>
				<tr>
					<td colspan="3" class="rr">
						<input type="submit" class="btn btn-info" value="가입">
						<button type="reset" class="btn btn-secondary">다시 작성</button></td>
				</tr>
			</table>
	</form>

</body>


</html>