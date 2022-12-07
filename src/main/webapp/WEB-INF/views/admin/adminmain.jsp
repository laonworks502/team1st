<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>관리자님, 환영합니다.</h1>
	
	<button type="button" class="btn text-black" style="background-color: #fff; border-color: #000;" onClick="location.href='#'">Client side 메인</button>

	<button type="button" class="btn text-black"
		style="background-color: #fff; border-color: #000;"
		onClick="location.href='adminlogout'; alert('로그아웃 되었습니다.');">로그아웃</button>

	<button type="button" class="btn text-black" id="userJoinChartDateButton" value="일반회원 일별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="joinChartDate()">일반회원 일별 가입자</button>

	<button type="button" class="btn text-black" id="userJoinChartWeekButton" value="일반회원 주별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="joinChartWeek()">일반회원 주별 가입자</button>

	<button type="button" class="btn text-black" id="userJoinChartMonthButton" value="일반회원 월별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="joinChartMonth()">일반회원 월별 가입자</button>

	<button type="button" class="btn text-black" id="companyJoinChartDateButton" value="기업회원 일별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="companyJoinChartDate()">기업회원 일별 가입자</button>
		
	<button type="button" class="btn text-black" id="companyJoinChartWeekButton" value="기업회원 주별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="companyJoinChartWeek()">기업회원 주별 가입자</button>
		
	<button type="button" class="btn text-black" id="companyJoinChartMonthButton" value="기업회원 월별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="companyJoinChartMonth()">기업회원 월별 가입자</button>

	<button type="button" class="btn text-black" id="usersList" value="전체 회원 목록" style="background-color: #fff; border-color: #000;"
		onclick="generalUsersListPage(); generalUsersList();">전체 회원 목록</button>

	<!-- 그래프 나타나는 곳  -->
	<div id="chart"></div>
	<!-- 목록 나타나는 곳 -->
	<div id="ajaxGeneralUsersList"></div>

<!-- <script type="text/javascript" src="/js/admin/adminmainjs.js"></script> -->

<script>
//일반회원 일별 가입자 수 
function joinChartDate() {
	$('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
	$('#ajaxGeneralUsersList').hide()
}

//일반회원 주별 가입자 수
function joinChartWeek() {
	$('#chart').load('adminstat2')
	$('#ajaxGeneralUsersList').hide()
}

//일반회원 월별 가입자 수
function joinChartMonth() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat3')
}

//기업회원 일별 가입자 수
function companyJoinChartDate() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat4')
}

//기업회원 주별 가입자 수
function companyJoinChartWeek() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat5')
}

//기업회원 월별 가입자 수
function companyJoinChartMonth() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat6')
}

//전체 회원 목록 페이지 
function generalUsersListPage() {
	$('#chart').load('generaluserslistpage')
}

//전체 회원 목록
function generalUsersList() {
	$('#ajaxGeneralUsersList').show()
	$.ajax({
			url:"<%=request.getContextPath()%>/generaluserslist",
			type : "get",
			dataType : "json",
			async : false,
			success : function(data) {
				console.log(data);
				ajaxHtml(data);

			},
			error : function() {
				alert("error");
			}
		});
	
	function ajaxHtml(result) {
		var html = "<table class='table'>";
		html += "<tr>";
		html += "<td>이름</td>";
		html += "<td>이메일</td>";
		html += "<td>전화번호</td>";
		html += "<td>가입일</td>";
		html += "</tr>";

		$.each(result, function(index, obj) {
			html += "<tr>";
			html += "<td>" + obj.name + "</td>";
			html += "<td>" + obj.email + "</td>";
			html += "<td>" + obj.tel1 + obj.tel2 + obj.tel3 + "</td>";
			html += "<td>" + obj.register_date + "</td>";
			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxGeneralUsersList").html(html);
	}

}

</script>

</body>
</html>