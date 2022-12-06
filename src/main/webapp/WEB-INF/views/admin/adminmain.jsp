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
	
	<button type="button" class="btn text-black" style="background-color: #fff;" onClick="location.href='#'">Client side 메인</button>

	<button type="button" class="btn text-black"
		style="background-color: #fff;"
		onClick="location.href='adminlogout'; alert('로그아웃 되었습니다.');">로그아웃</button>

	<button type="button" id="userJoinChartDateButton" value="일반회원 일별 가입자 수"
		onclick="joinChartDate()">일반회원 일별 가입자</button>

	<button type="button" id="userJoinChartWeekButton" value="일반회원 주별 가입자 수"
		onclick="joinChartWeek()">일반회원 주별 가입자</button>

	<button type="button" id="userJoinChartMonthButton" value="일반회원 월별 가입자 수"
		onclick="joinChartMonth()">일반회원 월별 가입자</button>

	<button type="button" id="companyJoinChartDateButton" value="기업회원 일별 가입자 수"
		onclick="companyJoinChartDate()">기업회원 일별 가입자</button>
		
	<button type="button" id="companyJoinChartWeekButton" value="기업회원 주별 가입자 수"
		onclick="companyJoinChartWeek()">기업회원 주별 가입자</button>
		
	<button type="button" id="companyJoinChartMonthButton" value="기업회원 월별 가입자 수"
		onclick="companyJoinChartMonth()">기업회원 월별 가입자</button>

	<button type="button" id="usersList" value="전체 회원 목록"
		onclick="generalUsersList()">전체 회원 목록</button>
		
	<a href="<%=request.getContextPath() %>/generaluserslist">a태그 회원목록</a>

	<button type="button" id="deletedUsersList" value="전체 회원 목록"
		onclick="deletedUsersList()">탈퇴한 회원 목록</button>

	<!-- 그래프 나타나는 곳  -->
	<div id="chart"></div>
	<!-- 목록 나타나는 곳 -->
	<div id="ajaxGeneralUsersList"></div>


	<script>
		//일반회원 일별 가입자 수 
		function joinChartDate() {
			$('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
		}

		//일반회원 주별 가입자 수
		function joinChartWeek() {
			$('#chart').load('adminstat2')
		}

		//일반회원 월별 가입자 수
		function joinChartMonth() {
			$('#chart').load('adminstat3')
		}
		
		//기업회원 일별 가입자 수
		function companyJoinChartDate() {
			$('#chart').load('adminstat4')
		}
		
		//기업회원 주별 가입자 수
		function companyJoinChartWeek() {
			$('#chart').load('adminstat5')
		}

		//기업회원 월별 가입자 수
		function companyJoinChartMonth() {
			$('#chart').load('adminstat6')
		}
		
		//전체 회원 목록
		function generalUsersList() {
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

		//탈퇴 회원 목록
		function deletedUsersList() {
			$('#chart').load('/deleteduserslist')
		}
	</script>

</body>
</html>