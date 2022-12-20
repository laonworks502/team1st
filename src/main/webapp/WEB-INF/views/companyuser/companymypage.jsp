<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 마이페이지</title>
</head>
<body>

<h2>기업 마이 페이지</h2> <br>
<ul>
	<li><button onclick="location='update'">정보 수정</button><br></li>
	<li><button onclick="location='delete'">회원 탈퇴</button><br></li>
	<li><button onclick="btnLogout()">로그아웃</button><br></li>
	<li><button onclick="location='/'">메인</button><br></li>
	<li><button onclick="location='companywritelist'">내가 작성한 글</button><br></li>
	<li><button onclick="location.href='apply/jobposts'">내가 작성한 구인글</button><br></li>
</ul>

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