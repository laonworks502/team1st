<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자 메인</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

	<br>
	<h4>일별 회원수 추이</h4>
	<br>
	<div style="width: 400px; height: auto; align: center">
		<canvas id="userJoinChartDate">
				</canvas>

	</div>
	<script>

	var arr = new Array('7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전', '오늘');

	
	// 오늘 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartDate"), {
		type: 'line', 
		data: {
			labels: 
				for(var i = 1; i < arr.length; i++){
					document.write("[" + i + "일전" )
				}
					document.write("]")
			datasets: [{
				label: "일별 가입자수 추이", 
				data :  
					[${ago7JoinTotal}, ${ago6JoinTotal}, ${ago5JoinTotal}, ${ago4JoinTotal}, ${ago3JoinTotal}, ${ago2JoinTotal}, ${ago1JoinTotal}, ${todayJoinTotal}],
				fill : true,
				borderColor : '#001aff',
				borderWidth: 2
				
			}]
		}  배열 : https://hianna.tistory.com/709

	});
	
/* 	// 오늘 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartDate"), {
		type: 'line', 
		data: {
			labels: ['7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전', '오늘'], //x축
			datasets: [{
				label: "일별 가입자수 추이", 
				data :  
					[${ago7JoinTotal}, ${ago6JoinTotal}, ${ago5JoinTotal}, ${ago4JoinTotal}, ${ago3JoinTotal}, ${ago2JoinTotal}, ${ago1JoinTotal}, ${todayJoinTotal}],
				fill : true,
				borderColor : '#001aff',
				borderWidth: 2
				
			}]
		}

	}); */

	</script>
</body>		
</html>
