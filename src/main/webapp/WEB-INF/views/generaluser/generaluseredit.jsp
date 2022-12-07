<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <title>회원 수정</title>

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

 <script src="http://code.jquery.com/jquery-latest.js"></script>
 <script src="/js/general_edit_check.js"></script><!-- /js 꼭 붙이기 -->
 <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

</head>
<body>

<form action="generaluseredit_ok" onsubmit="return edit_check()" method="post" align="center"
      name="editform" style="margin-top: 15%;">
 <input type="hidden" name="email" value="${sessionScope.email}"> <!-- sessionScope : 컨트롤러의 세션값을 JSP에서 JSTL로 사용하는 것 -->
 <input type="hidden" name="passwd" id="passwd" value="${gub.passwd}">
 <h2>회원 수정</h2>
 <table align="center">
  <tr>
   <td>이메일</td>
   <td> ${email} </td>
  </tr>

  <tr>
   <td>비밀번호</td>
   <td><input type="password" name="passconfirm" id="passconfirm" class="input_box">
    <div id=passfail style="color: red;"></div>
    <div id=passsucc style="color: blue;"></div></td>
  </tr>

  <tr>
   <td>이름</td>
   <td><input type="text" name="name" id="name" class="input_box" value="${gub.name}"></td>
  </tr>

  <tr>
   <th>우편번호</th>
   <td>
    <input name="postal_code" id="postal_code" size="5" class="input_box"
           readonly onclick="post_search()" value="${gub.postal_code}"/>

    <input type="button" value="우편번호검색" class="input_button"
           onclick="openDaumPostcode()" />
   </td>
  </tr>

  <tr>
   <th>주소</th>
   <td>
    <input name="address1" id="address1" size="50" class="input_box" value="${gub.address1}"
           readonly onclick="post_search()" />
   </td>
  </tr>

  <tr>
   <th>나머지 주소</th>
   <td>
    <input name="address2" id="address2" size="37" class="input_box" value="${gub.address2}"/>
   </td>
  </tr>

  <tr>
   <td>전화번호</td>
   <td>
    <select name="tel1" value="${gub.tel1}">
     <option value="010">010</option>
     <option value="011">011</option>
     <option value="016">016</option>
     <option value="017">017</option>
     <option value="018">018</option>
     <option value="019">019</option>
    </select>
    -<input name="tel2" id="tel2" size="4" maxlength="4" class="input_box" value="${gub.tel2}"/>
    -<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" value="${gub.tel3}"/></td>
  </tr>

  <tr>
   <td colspan="3">
    <input type="submit" class="btn btn-primary" value="수정하기">
    <button type="reset" class="btn btn-secondary">초기화</button></td>
  </tr>
 </table>
</form>

<script>
 //우편번호, 주소 Daum API
 function openDaumPostcode() {
  new daum.Postcode({
   oncomplete : function(data) {
    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    // 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
    document.getElementById('postal_code').value = data.zonecode;
    document.getElementById('address1').value = data.address;
   }
  }).open();
 }
</script>

</body>

</html>
