/**
 * 기업회원 탈퇴 JS
 */

function check() {

	if ($.trim($("#passconfirm").val()) == "") {
		alert("회원 비밀번호를 입력하세요!");
		$("#passconfirm").val("").focus();
		return false;
	}

	if ($.trim($("#exit_reason").val()) == "") {
		alert("탈퇴사유를 입력해주세요");
		$("#exit_reason").val("").focus();
		return false;
	}

}