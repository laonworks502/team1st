<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<button onclick="location='update'">정보 수정</button>
<button onclick="location='delete'">회원 탈퇴</button>
<button onclick="btnLogout()">로그아웃</button>
<button onclick="location='/'">게시판</button>
<button onclick="location='companywritelist'">내가 작성한 글</button>



</body>

<script type="text/javascript">
function btnLogout() {
	if(confirm("정말 로그아웃하시겠습니까?")== true){ // 확인
		location.href="logout"
	}else{	// 취소
		return;
	}
}
</script>

</html>