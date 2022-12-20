<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 선택</title>

    <link rel="stylesheet" href="/resources/css/loginForm.css">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">



</head>
<body class="text-center">

<%@ include file = "../common/header.jsp" %>
    <main class="form-signin w-800 m-auto">

        <table>
            <tr>
                <div>
                    <td>
                        <button class="btn btn-primary btn-lg" type="button" style="width: 200px;" onclick="location='general-login-form'">일반로그인</button>
                    </td>

                    <td style="width: 50px;">
                    </td>

                    <td>
                        <button class="btn btn-warning btn-lg" type="button" style="width: 200px;" onclick="location='company-login-form'">기업로그인</button>
                    </td>



                </div>
            </tr>

            <tr class="nav-scroller">
                <td>
                    <br>
                    <br>
                    * 회원가입 시에는 유형을 선택하세요
                    <form method="post">
                        <label>
                            <input type="radio" id="general" name="userselect" value="일반">
                            일반
                        </label>
                        <label>
                            <input type="radio" id="company" name="userselect" value="기업">
                            기업
                        </label>

                        <input type="button" value="회원가입하기" onclick="userinsert()">
                    </form>
                    <div id="result"></div>
                </td>
            </tr>
        </table>
        <%--    class="w-50 btn btn-lg btn-primary"--%>
    </main>

<script>
    function userinsert() {
        // jQuery식 표현
        var userselect = $('input[type=radio][name=userselect]:checked').val();
        console.log(userselect);

        if(userselect == "일반"){
            location='/general-user-insert';

        }else if(userselect == "기업"){
            location='/postcompanyuser';

        }else if(userselect == null){
            alert("회원가입 유형을 선택하세요!");
        }
    }

    function userinsert2() {
        // 자바스크립트로 표현

        // <input>의 type=radio일 때, name을 호출하여 value를 불러오면
        // 같은 name을 가진 radio타입 <input>의 value들을 모두 불러와서 배열 형식 취급.
        // radios가 불러온 name인 "userselect"는 radio타입
        var radios = document.getElementsByName("userselect");  // radios = 배열 형식
        var userselected = Array.from(radios).find(radio => radio.checked); // find속 radio는 배열 속 원소들을 칭하는 임의의 변수
        console.log(userselected.value);
        alert(userselected.value);

        if(userselected.value == "일반"){
            location='/general-user-insert';
        }else if(userselected.value == "기업"){
            location='/postcompanyuser';
        }else{
            alert("회원가입 유형을 선택하세요!");
        }

    }
</script>

</body>
</html>
