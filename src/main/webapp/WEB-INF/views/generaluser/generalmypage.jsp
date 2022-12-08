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

<input type="button" value="로그아웃" class="input_button" onclick="location='loginselect'">
<input type="button" value="회원수정" class="input_button" onclick="location='generaluseredit'">
<input type="button" value="회원탈퇴" class="input_button" onclick="location='generaluserdelete'">

<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
<form action="resumeupload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="email" value="${sessionScope.email}">

    <br>
    <br>
    <br>이력서 업로드 : 파일 선택 후 업로드 버튼을 누르세요.
    <br>
    이력서 선택 : <input type="file" name="file">
    <input type="submit" value="업로드하기">

    <br>

    <%-- <a href="download.do?fname=<%=request.getContextPath()%>/upload/${fileName }"> --%>

    <br>
    등록된 이력서 :
    <a href="/download?resume=${gub.resume}">
        ${gub.resume}
    </a>
    <br>
</form>

</body>
</html>
