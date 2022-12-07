//일반회원 일별 가입자 수 
function joinChartDate() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
}

//일반회원 주별 가입자 수
function joinChartWeek() {
	$('#ajaxGeneralUsersList').hide()
	$('#chart').load('adminstat2')
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
		html += "<td>관리</td>";
		html += "</tr>";

		$.each(result, function(index, obj) {
			html += "<tr>";
			html += "<td>" + obj.name + "</td>";
			html += "<td>" + obj.email + "</td>";
			html += "<td>" + obj.tel1 + "-"+ obj.tel2 +"-"+ obj.tel3 + "</td>";
			html += "<td>" + obj.register_date + "</td>";
			html += "<td> 회원 삭제 </td>";
			html += "</tr>";
		})
		html += "</table>";

		$("#ajaxGeneralUsersList").html(html);
	}

}
