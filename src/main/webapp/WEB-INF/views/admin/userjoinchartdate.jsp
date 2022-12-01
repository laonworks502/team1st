<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>



<canvas id="userJoinChartDate"></canvas>
	<script>
//오늘 ~ 7일 전까지 가입자 수 통계
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
</script>

</body>
</html>