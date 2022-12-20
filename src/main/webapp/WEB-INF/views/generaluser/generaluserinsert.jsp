<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/resources/js/checkgeneral.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

</head>

<body>

<form action="general-user-insert-ok" onsubmit="return checkch()" method="post" align="center" name="insertform" style="margin-top: 15%;">
    <h2>일반 회원 가입</h2>
    <table align="center">
        <tr>
            <td>이메일</td>
            <td> <input type="text" name="email" id="email" oninput="checkemailch()" /></td>

            <td>
                <span class="email_ok" id="email_ok" style="color:green; display:none;">사용 가능</span>
                <span class="email_already" id="email_already" style="color:red; display:none;">사용 불가능</span>
            </td>
        </tr>

        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="passwd" id="passwd">
                <div id=passfail style="color: red;"></div>
                <div id=passsucc style="color: blue;"></div>
            </td>
        </tr>
        <tr>
            <td>비밀번호 확인</td>
            <td><input type="password" name="passconfirm" id="passconfirm" ></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" name="name" id="name" ></td>
        </tr>

        <tr>
            <th>우편번호</th>
            <td>
                <input name="postal_code" id="postal_code" size="5" class="input_box"
                       readonly onclick="post_search()" />
                <!-- -<input name="join_zip2" id="join_zip2" size="3" class="input_box" readonly
                        onclick="post_search()"/> -->
                <input type="button" value="우편번호검색" class="input_button"
                       onclick="openDaumPostcode()" />
            </td>
        </tr>

        <tr>
            <th>주소</th>
            <td>
                <input name="address1" id="address1" size="50" class="input_box"
                       readonly onclick="post_search()" />
            </td>
        </tr>

        <tr>
            <th>나머지 주소</th>
            <td>
                <input name="address2" id="address2" size="37" class="input_box" />
            </td>
        </tr>

        <tr>
            <td>전화번호</td>
            <td>
                <select name="tel1">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                </select>
                -<input name="tel2" id="tel2" size="4" maxlength="4" class="input_box" />
                -<input name="tel3" id="tel3" size="4" maxlength="4" class="input_box" /></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" class="btn btn-primary" value="가입">
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
