<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 목록</title>
<%-- <script src="<%=request.getContextPath() %>/admin/masking.js"></script> --%>
</head>
<body>
	<script>
	<%-- function generalUsersList(){
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
		}

		console.log("뷰페이지 들어옴");

		function ajaxHtml(result) {
			console.log(result);
			var html = "<table class='table'>";
			html += "<tr>";
			html += "<td>이름</td>";
			html += "<td>이메일</td>";
			html += "<td>전화번호</td>";
			html += "<td>가입일</td>";
			html += "</tr>";

			$.each(result, function(index, obj) {
				html += "<tr>";
				html += "<td>" + maskingFunc.name(obj.name); + "</td>";
				html += "<td>" + maskingFunc.email(obj.email); + "</td>";
				html += "<td>" + maskingFunc.phone(obj.tel1 + obj.tel2 + obj.tel3); + "</td>";
				html += "<td>" + obj.register_date + "</td>";
				html += "</tr>";
			})
			html += "</table>";

			$("#ajaxGeneralUsersList").html(html);
		} --%>
	
	</script>
	<div class="container">
		<h2>일반 회원 목록</h2>
		<div class="panel panel-default">
			<div class="panel-heading">전체 회원 수(일반 회원): ${totalUsers}</div>
			<div class="panel-body">

				<div id="ajaxGeneralUsersList"></div>
				<!-- 회원 목록 출력 -->
				<!-- <button type="button" onclick="generalUsersList()"></button> -->
			</div>
		</div>
	</div>
</body>
</html>
