var check = 0;

function checkemailch(){
	var email = $('#email').val();
	$.ajax({
		url:"/useremailcheck",
		type: 'post',
		data: {email:email},
		success:function(cnt){
			if(cnt != 1 && email.length > 0 ){ // 사용가능 이메일
				$('.email_ok').css("display","inline-block");
				$('.email_already').css("display","none");
				check = 0;

			}else if(cnt == 1 && email.length > 0 ){ // 중복된 이메일
				$('.email_ok').css("display","none");
				$('.email_already').css("display","inline-block");
				check = 1;    // 중복 확인

			}else{ // 이메일에 아무것도 입력하지 않을 경우, 중복검사 문구 모두 안보이게 설정
				$('.email_ok').css("display","none");
				$('.email_already').css("display","none");
			}
				
		},
		error:function(){
			alert("에러입니다");
		}
	});
};

function checkch(){

	 if($.trim($("#email").val())==""){
		 alert("회원 E-mail을 입력하세요!");
		 $("#email").val("").focus();
		 return false;
	 }
	 email = $.trim($("#email").val());

	 var regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	 if(!regEmail.test(email)){
	 	 alert("이메일 형식이 유효하지 않습니다.");
	 	 $("#member_email").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd").val())==""){
		 alert("비밀번호를 입력하세요!");
		 $("#passwd").val("").focus();
		 return false;
	 }else{
		 var pw = $("#passwd").val();
		 var num1 = pw.search(/[0-9]/g);
		 var eng = pw.search(/[a-z]/ig);

		 if(pw.length < 8 || pw.length > 20){

			 alert("8자리 ~ 20자리 이내로 입력해주세요.");
			 return false;
		 }else if(pw.search(/\s/) != -1){
			 alert("비밀번호는 공백 없이 입력해주세요.");
			 return false;
		 }else if(num1 < 0 || eng < 0 ){
			 alert("영문,숫자를 혼합하여 입력해주세요.");
			 return false;
		 }
	 }


	 if($.trim($("#passconfirm").val())==""){
		 alert("비밀번호확인을 입력하세요!");
		 $("#passconfirm").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd").val()) != $.trim($("#passconfirm").val())){
		 //!=같지않다 연산. 비번이 다를 경우
		 alert("비밀번호가 일치하지 않습니다!");
		 $("#passwd").val("");
		 $("#passconfirm").val("");
		 $("#passwd").focus();
		 return false;
	 }



	 if($.trim($("#name").val())==""){
		 alert("회원 이름을 입력하세요!");
		 $("#name").val("").focus();
		 return false;
	 }
	if($.trim($("#postal_code").val())==""){
		alert("우편번호를 입력하세요!");
		$("#postal_code").val("").focus();
		return false;
	}
	if($.trim($("#address1").val())==""){
		alert("주소를 입력하세요!");
		$("#address1").val("").focus();
		return false;
	}
	if($.trim($("#address2").val())==""){
		alert("나머지 주소를 입력하세요!");
		$("#address2").val("").focus();
		return false;
	}

	var regExp = /^[0-9]*$/;
	var tel2 = $.trim($("#tel2").val());
	var tel3 = $.trim($("#tel3").val());

	if(($.trim($("#tel2").val())=="") ){
		alert("전화번호를 입력하세요!");
		$("#tel2").val("").focus();
		return false;
	}
	if(!regExp.test(tel2)){
		alert("전화번호는 숫자로 입력하세요!");
		$("#tel2").focus();
		return false;
	}
	if($.trim($("#tel3").val())==""){
		alert("전화번호를 입력하세요!");
		$("#tel3").val("").focus();
		return false;
	}
	if(!regExp.test(tel3)){
		alert("전화번호는 숫자로 입력하세요!");
		$("#tel3").focus();
		return false;
	}

	function post_search(){
		alert("우편번호 검색 버튼을 클릭하세요!");
	}

	function post_check(){
		window.open("zipcode_find.do","우편번호검색",
			"width=420,height=200,scrollbars=yes");
		//폭이 420이고 높이가 200,스크롤바가 생성되는 새로운 공지창을 만듬
	}

	if(check == 1){
		alert("중복된 E-mail입니다.");
		return false;
	}

	if (confirm("회원가입") == true) { // 확인
		alert("가입되었습니다");
		document.insertform.submit();
	} else { // 취소
		return false;
	}
}
