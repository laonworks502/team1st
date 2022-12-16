<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 정보 입력</title>

<script src="js/checkcompany.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<form action="companyinsert_ok" onsubmit="return check()" method="post" align="center" style="margin-top: 15%;margin-bottom: 15%">
		<h2>기업 정보 입력</h2>
		<table align="center">
			<tr>
				<th>기업명</th>
				<td><input type="text" name="company_name" id="company_name"></td>
			</tr>
			<tr>
				<th>분야</th>
				<td><input type="text" name="field" id="field"></td>
			</tr>
			<tr>
				<th>
					<div>지도</div>
				</th>
				<td>
					<div id="map" style="width: 400px; height: 300px; display: none"></div>
				</td>
			</tr>

			<script
				src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0dbfe54deb9efafeafa4afe96c0d64d8&libraries=services"></script>
			<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div
				mapOption = {
					center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
					level : 5
				// 지도의 확대 레벨
				};

				//지도를 미리 생성
				var map = new daum.maps.Map(mapContainer, mapOption);
				//주소-좌표 변환 객체를 생성
				var geocoder = new daum.maps.services.Geocoder();
				//마커를 미리 생성
				var marker = new daum.maps.Marker({
					position : new daum.maps.LatLng(37.537187, 127.005476),
					map : map
				});
				function addrFind() {
					new daum.Postcode(
							{
								oncomplete : function(data) {
									// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
									// 각 주소의 노출 규칙에 따라 주소를 조합한다.
									// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
									var addr = ''; // 주소 변수
									var extraAddr = ''; // 참고항목 변수

									//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
									if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
										addr = data.roadAddress;
									} else { // 사용자가 지번 주소를 선택했을 경우(J)
										addr = data.jibunAddress;
									}
									// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
									if (data.userSelectedType === 'R') {
										// 법정동명이 있을 경우 추가한다. (법정리는 제외)
										// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
										if (data.bname !== ''
												&& /[동|로|가]$/g.test(data.bname)) {
											extraAddr += data.bname;
										}
										// 건물명이 있고, 공동주택일 경우 추가한다.
										if (data.buildingName !== ''
												&& data.apartment === 'Y') {
											extraAddr += (extraAddr !== '' ? ', '
													+ data.buildingName
													: data.buildingName);
										}
										// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
										if (extraAddr !== '') {
											extraAddr = ' (' + extraAddr + ')';
										}
										// 조합된 참고항목을 해당 필드에 넣는다.
										document.getElementById("tbox4").value = extraAddr;
									} else {
										document.getElementById("tbox4").value = '';
									}
									// 우편번호와 주소 정보를 해당 필드에 넣는다.
									document.getElementById('tbox1').value = data.zonecode;
									document.getElementById("tbox2").value = addr;
									// 커서를 상세주소 필드로 이동한다.
									document.getElementById("tbox3").focus();

									geocoder.addressSearch(
													data.address,
													function(results, status) {
														// 정상적으로 검색이 완료됐으면
														if (status === daum.maps.services.Status.OK) {
															var result = results[0]; //첫번째 결과의 값을 활용
															// 해당 주소에 대한 좌표를 받아서
															var coords = new daum.maps.LatLng(
																	result.y,
																	result.x);
															// 지도를 보여준다.
															mapContainer.style.display = "block";
															map.relayout();
															// 지도 중심을 변경한다.
															map
																	.setCenter(coords);
															// 마커를 결과값으로 받은 위치로 옮긴다.
															marker
																	.setPosition(coords)
														}
													});
								}
							}).open();
				}
			</script>
			<tr>
				<th>주소</th>
				<!-- zib코드 입력-->
				<td><input type="text" id="tbox1" name="postal_code" placeholder="우편번호" class="form-control" style="width: 200px;">
					<input type="button" class="btn btn-primary btn-sm" onclick="addrFind()" value="우편번호 찾기"><br> 
					<input class="form-control" style="width: 200px;" type="text" name="address1" id="tbox2" placeholder="주소" size=28>
					<input class="form-control" style="width: 150px;" type="text" name="tbox4" id="tbox4" placeholder="참고항목" size=28> 
					<input type="text" id="tbox3" name="address2" class="form-control" style="width: 200px;" placeholder="상세주소" > 
				</td>
			<tr>
				<th>설립일</th>
				<td><input type="date" name="foundation_date"id="foundation_date"></td>
			</tr>
			<tr>
				<th >전화번호</th>
				<td ><input name="tel1" id="tel1" size="3"maxlength="3" class="input_box" /> 
					-<input name="tel2" id="tel2"size="4" maxlength="4" class="input_box" /> 
					-<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" /></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" class="btn btn-info"value="저장 후 다음">
					<button type="reset" class="btn btn-secondary">다시 작성</button></td>
			</tr>
		</table>
	</form>


</body>
</html>