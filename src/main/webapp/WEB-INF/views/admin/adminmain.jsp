<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자 메인</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
	<h1>관리자님, 환영합니다.</h1>
	
	<br>
	<button type="button" class="btn text-white" style="background-color: #fff;" onClick="location.href='adminlogout' ">로그아웃</button>
	<h4>회원수 추이</h4>
	<br>
	<div style="width: 400px; height: auto; align: center">
		<canvas id="userJoinChartDate">
				</canvas>
				<br>
				<canvas id="userJoinChartMonth">
				</canvas>
	</div>
	<script>
	
	// 오늘 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartDate"), {
		type: 'line', 
		data: {
			labels: ['7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전', '오늘'], //x축
			datasets: [{
				label: "일별 가입자수 추이", 
				data : [${ago7JoinTotal}, ${ago6JoinTotal}, ${ago5JoinTotal}, ${ago4JoinTotal}, ${ago3JoinTotal}, ${ago2JoinTotal}, ${ago1JoinTotal}, ${todayJoinTotal}],
				fill : true,
				borderColor : '#001aff',
				borderWidth: 2
				
			}]
		}

	})
	
	// 1주전 ~ 4주전 까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartMonth"), {
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

	})
	
	
	
		/* const labels = Utils.days({
			count : 8
		});
		const data = {
			labels : "",
			datasets : [ {
				label : 'userJoinChart',
				data : [${todayJoinTotal}, ${ago1JoinTotal}, ${ago2JoinTotal}, ${ago3JoinTotal}, ${ago4JoinTotal}, ${ago5JoinTotal}, ${ago6JoinTotal}, ${ago7JoinTotal}],
				fill : false,
				borderColor : 'rgb(75, 192, 192)',
				tension : 0.1
			} ]
		}; */
	</script>
</body>		
</html>
