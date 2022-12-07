/**
 * 기업회원 정보수정 JS
 */

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
	if ($.trim($("#name").val()) == "") {
		alert("회원이름을 입력하세요!");
		$("#name").val("").focus();
		return false;
	}
	if ($.trim($("#tel1").val()) == "") {
		alert("휴대전화번호를 입력하세요!");
		$("#phone2").val("").focus();
		return false;
	}
	if ($.trim($("#tel2").val()) == "") {
		alert("휴대전화번호를 입력하세요!");
		$("#phone2").val("").focus();
		return false;
	}
	if ($.trim($("#tel3").val()) == "") {
		alert("휴대전화번호를 입력하세요!");
		$("#phone3").val("").focus();
		return false;
	}

}

function post_search() {
	alert("우편번호 검색 버튼을 클릭하세요!");
}

function post_check() {
	window.open("zipcode_find.do", "우편번호검색",
			"width=420,height=200,scrollbars=yes");
	// 폭이 420이고 높이가 200,스크롤바가 생성되는 새로운 공지창을 만듬
}
