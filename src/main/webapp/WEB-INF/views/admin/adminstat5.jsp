<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<br>
	<h4>기업회원 주별 회원수 추이</h4>
	<br>
	<div style="width: 800px; height: auto; align: center">
				<canvas id="companyJoinChartWeek">
				</canvas>
	</div>
	<script>
	
	// 1주전 ~ 4주전 까지 가입자 수 통계
	new Chart(document.getElementById("companyJoinChartWeek"), {
		type: 'line', 
		data: {
			labels: ['4주전', '3주전', '2주전', '1주전'], //x축
			datasets: [{
				label: "주별 가입자수 추이(기업)", 
				data : [${list[0]},${list[1]},${list[2]},${list[3]}],
				fill : true,
				borderColor : '#8c00ff',
				borderWidth: 2
				
			}]
		}

	});
	</script>
