<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<br>
	<h4>기업회원 일별 회원수 추이</h4>
	<br>
	<div style="width: 800px; height: auto; align: center">
		<canvas id="companyJoinChartDate">
				</canvas>

	</div>
	<script>
	
	// 1일전 ~ 7일 전까지 가입자 수 통계
	new Chart(document.getElementById("companyJoinChartDate"), {
		type: 'line', 
		data: {
			labels: ['7일전', '6일전', '5일전', '4일전', '3일전', '2일전', '1일전'], //x축
			datasets: [{
				label: "일별 가입자수 추이(기업)", 
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
