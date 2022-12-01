function check() {

	if ($.trim($("#passconfirm").val()) == "") {
		alert("회원 비밀번호를 입력하세요!");
		$("#passconfirm").val("").focus();
		return false;
	}

	if ($.trim($("#passconfirm").val()) != $.trim($("#passwd").val())) {
		// !=같지않다 연산. 비번이 다를 경우
		alert("비번이 다릅니다!");
		$("#passconfirm").val("");
		$("#passconfirm").val("");
		$("#passconfirm").focus();
		return false;
	}
	if ($.trim($("#exit_reason").val()) == "") {
		alert("탈퇴사유를 입력해주세요");
		$("#exit_reason").val("").focus();
		return false;
	}
	if (confirm("정말 탈퇴하시겠습니까?") == true) { // 확인
		alert("탈퇴되었습니다");
		document.deleteform.submit();
	} else { // 취소
		return false;
	}

}