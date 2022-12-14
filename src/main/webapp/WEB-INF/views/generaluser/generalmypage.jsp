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

<input type="text" name="email" value="${gub.email}" style="border: none" readonly>

<input type="button" value="로그아웃" class="input_button" onclick="location='/loginselect'">
<input type="button" value="회원수정" class="input_button" onclick="location='/generaluseredit'">
<input type="button" value="회원탈퇴" class="input_button" onclick="location='/generaluserdelete'">


<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
<form action="/resumeupload" method="post" enctype="multipart/form-data">

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

<div class="myscrab_minilist_wrap">
    <ul class="subject" >
        <h3>내 스크랩</h3>
    </ul>
    <ul class="myscrap_mini_wrab">
        <li class="board_subject">
            <h4>정규 게시판</h4>
        </li>
        <div class="myscrap_mini_obj">
            <c:forEach var="myminiscrap100" items="${myminiscrap100}">
                ${myminiscrap100.title}
                ${myminiscrap100.content}
                ${myminiscrap100.date}
            </c:forEach>
            <a href="/scrap/listTotalScrap/100" style="text-decoration: none">더보기 ></a>
        </div>
        <li class="board_subject">
            <h4>단기 게시판</h4>
        </li>
        <div class="myscrap_mini_obj">
            <c:forEach var="myminiscrap200" items="${myminiscrap200}">
                ${myminiscrap200.title}
                ${myminiscrap200.content}
                ${myminiscrap200.date}
            </c:forEach>
            <a href="/scrap/listTotalScrap/200" style="text-decoration: none">더보기 ></a>
        </div>
        <li class="board_subject">
            <h4>스터디 게시판</h4>
        </li>
        <div class="myscrap_mini_obj">
            <c:forEach var="myminiscrap300" items="${myminiscrap300}">
                ${myminiscrap300.title}
                ${myminiscrap300.content}
                ${myminiscrap300.date}
            </c:forEach>
            <a href="/scrap/listTotalScrap/300" style="text-decoration: none">더보기 ></a>
        </div>
    </ul>
</div>

</body>
</html>
