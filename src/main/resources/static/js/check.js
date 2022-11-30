function checkemail(){
	var email = $('#email').val();
	$.ajax({
		url:"/emailcheck",
		type: 'post',
		data: {"email":email},
		success:function(cnt){
			if(cnt != 1 && email.length > 0 ){ // 사용가능 이메일
				$('.email_ok').css("display","inline-block");
				$('.email_already').css("display","none");
			}else if(cnt == 1 && email.length > 0 ){ // 중복된 이메일
				$('.email_ok').css("display","none");
				$('.email_already').css("display","inline-block");

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

function check(){
	 if($.trim($("#email").val())==""){
		 alert("회원 E-mail을 입력하세요!");
		 $("#email").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd").val())==""){
		 alert("비밀번호를 입력하세요!");
		 $("#passwd").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd2").val())==""){
		 alert("비밀번호확인을 입력하세요!");
		 $("#passwd2").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd").val()) != $.trim($("#passwd2").val())){
		 //!=같지않다 연산. 비번이 다를 경우
		 alert("비밀번호가 일치하지 않습니다!");
		 $("#passwd").val("");
		 $("#passwd2").val("");
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
	if($.trim($("#tel2").val())==""){
		alert("전화번호를 입력하세요!");
		$("#tel2").val("").focus();
		return false;
	}
	if($.trim($("#tel3").val())==""){
		alert("전화번호를 입력하세요!");
		$("#tel3").val("").focus();
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
}