<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
로그인성공

<input type="button" value="로그아웃" class="input_button" onclick="location='userlogout'">
<input type="button" value="회원수정" class="input_button" onclick="location='generaluseredit'">
<input type="button" value="회원탈퇴" class="input_button" onclick="location='generaluserdelete'">

<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
<form action="resumeupload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="email" value="${sessionScope.email}">

    파일 선택 : <input type="file" name="file" value="${gub.resume}">
    <input type="submit" value="전송">
</form>

</body>
</html>
