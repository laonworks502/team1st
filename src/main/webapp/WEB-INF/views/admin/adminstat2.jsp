<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
	<h4>월별 회원수 추이</h4>
	<br>
	<div style="width: 400px; height: auto; align: center">
				<canvas id="userJoinChartWeek">
				</canvas>
	</div>
	<script>
	
	// 1주전 ~ 4주전 까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartWeek"), {
		type: 'line', 
		data: {
			labels: ['4주전', '3주전', '2주전', '1주전'], //x축
			datasets: [{
				label: "주별 가입자수 추이", 
				data : [${ago4wJoinTotal}, ${ago3wJoinTotal}, ${ago2wJoinTotal}, ${ago1wJoinTotal}],
				fill : true,
				borderColor : '#8c00ff',
				borderWidth: 2
				
			}]
		}

	});
	
	
	</script>
</body>		
</html>
