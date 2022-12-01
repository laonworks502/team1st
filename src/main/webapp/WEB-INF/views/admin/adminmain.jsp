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
	<button type="button" class="btn text-white" style="background-color: #5e17eb;" onClick="location.href='adminlogout' ">로그아웃</button>
	<h4>회원수 추이</h4>
	<br>
	<div style="width: 400px; height: auto; align: center">
		<canvas id="userJoinChart">
				</canvas>
	</div>
	<script>
	
	new Chart(document.getElementById("userJoinChart"), {
		type: 'line', 
		data: {
			labels: ['7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전', '오늘'], //x축
			datasets: [{
				label: "일별 가입자수", 
				data : [${ago7JoinTotal}, ${ago6JoinTotal}, ${ago5JoinTotal}, ${ago4JoinTotal}, ${ago3JoinTotal}, ${ago2JoinTotal}, ${ago1JoinTotal}, ${todayJoinTotal}],
				fill : true,
				borderColor : '#001aff',
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
