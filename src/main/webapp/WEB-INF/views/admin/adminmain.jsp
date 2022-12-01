<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h1>관리자님, 환영합니다.</h1>
	<button type="button" class="btn text-white"
		style="background-color: #fff;" onClick="location.href='adminlogout' ">로그아웃</button>

	<button type="button" id="userJoinChartDateButton" value="일별 가입자 수"
		onclick="joinChartDate()">일별 가입자</button>

	<button type="button" id="userJoinChartMonthButton" value="월별 가입자 수"
		onclick="joinChartMonth()">월별 가입자</button>

	<!-- 일별 가입자 수 그래프 나타나는 곳  -->
	<div id="joindate"></div>

	<!-- 월별 가입자 수 그래프 나타나는 곳  -->
	<div id="joinmonth"></div>

	<script>
		function joinChartDate() {
			$('#joindate').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
		}

		function joinChartMonth() {
			$('#joinmonth').load('adminstat2')
		}
	</script>

</body>
</html>