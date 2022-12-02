<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자님, 환영합니다.</h1>
	
	<button type="button" class="btn text-black"
		style="background-color: #fff;"
		onClick="location.href='adminlogout'; alert('로그아웃 되었습니다.');">로그아웃</button>

	<button type="button" id="userJoinChartDateButton" value="일별 가입자 수"
		onclick="joinChartDate()">일별 가입자</button>

	<button type="button" id="userJoinChartWeekButton" value="주별 가입자 수"
		onclick="joinChartWeek()">주별 가입자</button>

	<button type="button" id="userJoinChartMonthButton" value="월별 가입자 수"
		onclick="joinChartMonth()">월별 가입자</button>
		
	<button type="button" id="usersList" value="전체 회원 목록"
		onclick="usersList()">전체 회원 목록</button>
		
	<button type="button" id="deletedUsersList" value="전체 회원 목록"
		onclick="deletedUsersList()">탈퇴한 회원 목록</button>	

	<!-- 그래프 나타나는 곳  -->
	<div id="chart"></div>


	<script>
		//일별 가입자 수 
		function joinChartDate() {
			$('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
		}
		
		//주별 가입자 수
		function joinChartWeek() {
			$('#chart').load('adminstat2')
		}
		
		//월별 가입자 수
		function joinChartMonth() {
			$('#chart').load('adminstat3')
		}
		
		//전체 회원 목록
		function usersList() {
			$('#chart').load('userslist')
		}
		
		//탈퇴 회원 목록
		function deletedUsersList() {
			$('#chart').load('deleteduserslist')
		}
	</script>

</body>
</html>