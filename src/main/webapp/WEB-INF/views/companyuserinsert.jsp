<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 회원 가입</title>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0dbfe54deb9efafeafa4afe96c0d64d8"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>   

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<form action="#">
<table align="center" style="margin-top: 15%;">
	<tr>
		<th>기업명</th>
		<td><input type="text" name="company_name"> </td>
	</tr>
	<tr>
		<th>분야</th>
		<td><input type="text" name="field"> </td>
	</tr>
	<tr>
		<th><div>지도</div></th>
		<td>
			<div id="map" style="width: 400px; height: 300px; display: none"></div>
<script>
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
       mapOption = {
           center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
           level: 3 // 지도의 확대 레벨
       };  
   
   
   $('#searchBtn').click(function(){
      // 버튼을 click했을때
      
      // 지도를 생성합니다    
      var map = new kakao.maps.Map(mapContainer, mapOption); 
      
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      
      // 주소로 좌표를 검색합니다
      geocoder.addressSearch($('#address').val(), function(result, status) {
   
          // 정상적으로 검색이 완료됐으면 
           if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });
   
              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
              });
              infowindow.open(map, marker);
   
              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      });  
   });
     
   </script>
		</td>
	</tr>

	<tr>
		<th>주소</th>
		<!-- zib코드 입력-->
		<td>
			<input type="text" id="tbox1" placeholder="우편번호" class="form-control" style="width: 200px;">
			<input type="button" id="searchBtn"  class="btn btn-primary btn-sm" onc value="우편번호 찾기"><br> 
			<input class="form-control" style="width: 200px;" type="text" name="tbox2" id="tbox2" placeholder="주소" size=28>
			<input class="form-control" style="width: 150px;" type="text" name="tbox4" id="tbox4" placeholder="참고항목" size=28>
			<input type="text" id="tbox3" name="tbox3" class="form-control" style="width: 200px;" placeholder="상세주소" required="required"> 
	</tr>
	<tr>
		<th>전화번호</th>
    	<td>
  			<input name="tel1" id="tel1" size="3" maxlength="3" class="input_box"/>
    		-<input name="tel2" id="tel2" size="4" maxlength="4" class="input_box" />
    		-<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" /></td>
	</tr>
	<tr>
		<th>설립일</th>
		<td><input type="date" name="foundation_date"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><br><br>
		<input type="submit" value="가입하기">
		<input type="reset" value="다시 작성">
		</td>
	</tr>
</table>
</form>


</body>
</html>