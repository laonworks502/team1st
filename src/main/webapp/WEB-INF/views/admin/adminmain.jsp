<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인</title>
</head>
<body>

	<h1>관리자님, 환영합니다.</h1>
	
	<button type="button" class="btn text-black" style="background-color: #fff; border-color: #000;" onClick="location.href='/test">Client side 메인</button>

	<button type="button" class="btn text-black"
		style="background-color: #fff; border-color: #000;"
		onClick="location.href='adminlogout'; alert('로그아웃 되었습니다.');">로그아웃</button>

	<button type="button" class="btn text-black" id="userJoinChartDateButton" value="일반회원 일별 가입자 수" style="background-color: #fff; border-color: #000;"
		onclick="joinChartDate()" >일반회원 일별 가입자</button>    

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

	<button type="button" class="btn text-black" id="generalUsersList" value="일반 회원 목록" style="background-color: #fff; border-color: #000;"
		onclick="generalUsersListPage(); generalUsersList();">일반 회원 목록</button>

	<button type="button" class="btn text-black" id="companyUsersList" value="기업 회원 목록" style="background-color: #fff; border-color: #000;"
			onclick="companyUsersListPage(); companyUsersList();">기업 회원 목록</button>

	<button type="button" class="btn text-black" id="companyList" value="기업 목록" style="background-color: #fff; border-color: #000;"
			onclick="companyListPage(); companyList();">기업 목록</button>

	<button type="button" class="btn text-black" id="fulltimeBoard" value="정규 구인 게시판" style="background-color: #fff; border-color: #000;"
			onclick="fulltimeBoardPage(); fulltimeBoadList();">정규 구인 게시판</button>

	<!-- 그래프 나타나는 곳  -->
	<div id="chart"></div>
	<!-- 목록 나타나는 곳 -->
	<div id="ajaxGeneralList"></div>
	<div id="ajaxCompanyUsersList"></div>
	<div id="ajaxCompanyList"></div>
<!-- 	<table>
	<tbody id="ajaxList"></tbody>
	</table> -->

<!-- <script type="text/javascript" src="/js/admin/adminmainjs.js"></script> -->

<script>


//일반회원 일별 가입자 수 
function joinChartDate() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
} 

//일반회원 주별 가입자 수
function joinChartWeek() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat2')
}

//일반회원 월별 가입자 수
function joinChartMonth() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat3')
}

//기업회원 일별 가입자 수
function companyJoinChartDate() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat4')
}

//기업회원 주별 가입자 수
function companyJoinChartWeek() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat5')
}

//기업회원 월별 가입자 수
function companyJoinChartMonth() {
	$('#ajaxList').hide()
	$('.table').hide()
	$('#chart').load('adminstat6')
}

//전체 일반 회원 목록 페이지
function generalUsersListPage() {
	$('#chart').load('generaluserslistpage')
}

//전체 일반 회원 목록
function generalUsersList() {

	$('#ajaxGeneralList').show()
	$('#ajaxCompanyUsersList').hide()
	$('#ajaxCompanyList').hide()
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
		html += "<td>관리</td>";
		html += "</tr>";

		$.each(result, function(index, obj) {

			html += "<tr>";
			html += "<td>" + obj.name + "</td>";
			html += "<td>" + obj.email + "</td>";
			html += "<td>" + obj.tel1 + "-";
			html += obj.tel2 + "-";
			html += obj.tel3 + "</td>"  ;
			html += "<td>" + obj.register_date + "</td>";
			html += "<td><button type='button' onclick='javascript:adminGeneralDelete(\""+obj.email+"\")'>삭제</button> </td>";
			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxGeneralList").html(html);
	}

}

// 삭제
function adminGeneralDelete(email) {
	var result = confirm('삭제하시겠습니까?');
	if(result) {
		$.ajax({
			url: "/admingeneraluserdelete",
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			},
			data: JSON.stringify({ 'email' : email }),
			success: function(result) {
				if(result == 1) {
					alert('삭제되었습니다.');
				}else {
					alert('삭제 실패');
				}
				console.log(result);
			}
		});
	}
	//alert(email);

}

//onclick="location.href='/admingeneraluserdelete'"

//전체 기업 회원 목록 페이지
function companyUsersListPage() {
	$('#chart').load('companyuserslistpage')
}

//전체 기업 회원 목록
function companyUsersList() {

	$('#ajaxGeneralList').hide()
	$('#ajaxCompanyUsersList').show()
	$('#ajaxCompanyList').hide()
	$.ajax({
		url:"<%=request.getContextPath()%>/companyuserslist",
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
		html += "<td>기업명</td>";
		html += "<td>담당자명</td>";
		html += "<td>이메일</td>";
		html += "<td>전화번호</td>";
		html += "<td>가입일</td>";
		html += "<td>관리</td>";
		html += "</tr>";

		$.each(result, function(index,obj ) {

			html += "<tr>";
			html += "<td>" + obj.company_name + "</td>";
			html += "<td>" + obj.name + "</td>";
			html += "<td>" + obj.email + "</td>";
			html += "<td>" + obj.tel1 + "-";
			html += obj.tel2 + "-";
			html += obj.tel3 + "</td>"  ;
			html += "<td>" + obj.register_date + "</td>";
			html += "<td><button type='button' onclick='javascript:adminCompanyUserDelete(\""+obj.email+"\")'>삭제</button> </td>";
			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxCompanyUsersList").html(html);
	}

}

// 삭제
function adminCompanyUserDelete(email) {
	var result = confirm('삭제하시겠습니까?');
	if(result) {
		$.ajax({
			url: "/admincompanyuserdelete",
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			},
			data: JSON.stringify({ 'email' : email }),
			success: function(result) {
				if(result == 1) {
					alert('삭제되었습니다.');
				}else {
					alert('삭제 실패');
				}
				console.log(result);
			}
		});
	}
	//alert(email);

}


//전체 기업 목록 페이지
function companyListPage() {
	$('#chart').load('companylistpage')
}

//전체 기업 목록
function companyList() {

	$('#ajaxGeneralList').hide()
	$('#ajaxCompanyUsersList').hide()
	$('#ajaxCompanyList').show()
	$.ajax({
		url:"<%=request.getContextPath()%>/companylist",
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
		html += "<td>기업명</td>";
		html += "<td>사업분야</td>";
		html += "<td>전화번호</td>";
		html += "<td>설립일</td>";
		html += "<td>관리</td>";
		html += "</tr>";

		$.each(result, function(index, obj) {

			html += "<tr>";
			html += "<td>" + obj.company_name + "</td>";
			html += "<td>" + obj.field + "</td>";
			html += "<td>" + obj.tel1 + "-";
			html += obj.tel2 + "-";
			html += obj.tel3 + "</td>"  ;
			html += "<td>" + obj.foundation_date + "</td>";
			html += "<td><button type='button' onclick='admindelete'>삭제</button> </td>";
			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxCompanyList").html(html);
	}

}

//정규직 구인구직 게시판 페이지
function fulltimeBoardPage() {
	$('#chart').load('fulltimeboardpage')
}

//정규직 구인구직 게시판 목록
function fulltimeBoadList() {

	$('#ajaxGeneralList').show()
	$('#ajaxCompanyUsersList').hide()
	$('#ajaxCompanyList').hide()
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
		html += "<td>관리</td>";
		html += "</tr>";

		$.each(result, function(index, obj) {

			html += "<tr>";
			html += "<td>" + obj.name + "</td>";
			html += "<td>" + obj.email + "</td>";
			html += "<td>" + obj.tel1 + "-";
			html += obj.tel2 + "-";
			html += obj.tel3 + "</td>"  ;
			html += "<td>" + obj.register_date + "</td>";
			html += "<td><button id='delete' type='button'>삭제</button> </td>";

			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxGeneralList").html(html);

	}

}


</script>

</body>
</html>