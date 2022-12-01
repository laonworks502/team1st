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
		<canvas id="userJoinChartMonth">
				</canvas>

	</div>
	<script>
	
	// 오늘 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartMonth"), {
		type: 'line', 
		data: {
			labels: ['12개월 전', '11개월 전', '10개월 전', '9개월 전', '8개월 전', '7개월 전', '6개월 전', '5개월 전', '4개월 전', '3개월 전', '2개월 전', '1개월 전'], //x축
			datasets: [{
						label: "월별 가입자수 추이", 
						data : [${ago12mJoinTotal}, ${ago11mJoinTotal}, ${ago10mJoinTotal}, ${ago9mJoinTotal}, ${ago8mJoinTotal}, ${ago7mJoinTotal}, ${ago6mJoinTotal}, ${ago5mJoinTotal}, ${ago4mJoinTotal}, ${ago3mJoinTotal}, ${ago2mJoinTotal}, ${ago1mJoinTotal}],
						fill : true,
						borderColor : '#570328',
						borderWidth: 2
				
			}]
		}
	
	});

	</script>
</body>		
</html>
