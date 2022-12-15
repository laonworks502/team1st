<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>login</title>

<link rel="stylesheet" href="/resources/css/loginForm.css">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

</head>

<body class="text-center">

<script>
	function login_check() {
		
		if($.trim($("#email").val())==""){
			alert("이메일을 입력해주세요.");
			$("#email").val("").focus();
			return false;
		}
		
		if($.trim($("#passwd").val())==""){
			alert("비밀번호를 입력해주세요.");
			$("#passwd").val("").focus();
			return false;	
		}
		
		// 이메일 형식 유효성 검사
		email = $.trim($("#email").val());
		  
		var regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(!regEmail.test(email)){
			alert("이메일 형식이 유효하지 않습니다.");
			$("#member_email").val("").focus();
			return false;
		}
	}		
		
/*[비번 찾기창]*/
	function passwd_find(){
		window.open("pwfind","비밀번호 찾기","width=500, height=500");
		}		
</script>


<main class="form-signin w-100 m-auto">

<form method="post" action="companylogin_ok" onsubmit="return login_check()">

    <h1 class="h3 mb-3 fw-normal">기업 회원 로그인</h1>
    
    <div class="form-floating">
      <input type="email" class="form-control" id="email" name="email" 
      		placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="passwd" name="passwd"
      		placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">

    </div>
    <div class="button-group">
    <input type="button" class="w-45 btn btn-sm btn-outline-secondary" value="기업회원가입" onclick="location='postcompanyuser'">
	<input type="button" class="w-50 btn btn-sm btn-outline-secondary" value="비밀번호 찾기" name="passwd_btn" onclick="passwd_find()" />
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
    
</form>

</main>

</body>
</html>