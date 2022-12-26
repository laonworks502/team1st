<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<%@ include file = "../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 마이페이지</title>
</head>
<body>
<div class="container">
<h2>기업 마이 페이지</h2> <br>

<ul  style="list-style: none;">
	<li style="margin: 5px;"><button class="btn btn-outline-primary me-2" onclick="location='/'">메인</button><br></li>
	<li style="margin: 5px;"><button class="btn btn-outline-primary me-2" onclick="location='companywritelist'">내가 작성한 글</button><br></li>
	<li style="margin: 5px;"><button class="btn btn-outline-primary me-2" onclick="location.href='apply/jobposts'">구인글 지원자 목록</button><br></li>
	<li style="margin: 5px;"><button class="btn btn-outline-primary me-2" onclick="btnLogout()">로그아웃</button><br></li>
	<li style="margin: 5px;"><button class="btn btn-outline-primary me-2" onclick="location='update'">정보 수정</button><br></li>
	<li style="margin: 5px;"><button class="btn btn-danger" onclick="location='delete'">회원 탈퇴</button><br></li>
</ul>
</div>
</body>

<script type="text/javascript">
function btnLogout() {
	if(confirm("정말 로그아웃하시겠습니까?")== true){ // 확인
		location.href='loginselect';
	}else{	// 취소
		return;
	}
}
</script>

</html>