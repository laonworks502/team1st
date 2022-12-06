<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자 메인</title>
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
	
	// 1일전 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartDate"), {
		type: 'line', 
		data: {
			labels: ['7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전'], //x축
			datasets: [{
				label: "일별 가입자수 추이", 
				data : 
					  [${list[0]},${list[1]},${list[2]},${list[3]},${list[4]},${list[5]},${list[6]},${list[7]}]
					,
				fill : true,
				borderColor : '#001aff',
				borderWidth: 2
				
			}]
		}

	}); 

	</script>
</body>
</html>
