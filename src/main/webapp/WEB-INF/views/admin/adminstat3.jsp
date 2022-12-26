<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<br>
	<h4>일반회원 월별 회원수 추이</h4>
	<br>
	<div style="width: 800px; height: auto; align: center">
		<canvas id="userJoinChartMonth">
				</canvas>
	</div>
	<script>
	
	// 1개월 전 ~ 12개월 전까지 가입자 수 통계
	new Chart(document.getElementById("userJoinChartMonth"), {
		type: 'line', 
		data: {
			labels: ['12개월 전', '11개월 전', '10개월 전', '9개월 전', '8개월 전', '7개월 전', '6개월 전', '5개월 전', '4개월 전', '3개월 전', '2개월 전', '1개월 전'], //x축
			datasets: [{
						label: "월별 가입자수 추이", 
						data : [${list[0]},${list[1]},${list[2]},${list[3]},${list[4]},${list[5]},${list[6]},${list[7]},${list[8]},${list[9]},${list[10]},${list[11]},${list[12]}],
						fill : true,
						borderColor : '#570328',
						borderWidth: 2
				
			}]
		}
	
	});

	</script>
