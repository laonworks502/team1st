
function boardCheck() {
	if ($('#title').val() == "") {
		alert("제목을 입력하세요.");
		$("#title").focus();
		return false;
	}
	if ($('#content').val() == "") {
		alert("내용을 입력하세요.");
		$("#content").focus();
		return false;
	}
}

function matchingCheck() {
	if ($('#title').val() == "") {
		alert("제목을 입력하세요.");
		$("#title").focus();
		return false;
	}else if ($('#total_members').val() == "") {
		alert("매칭 인원을 선택해주세요.");
		$("#total_members").focus();
		return false;
	} else if ($('#deadline').val() == "") {
		alert("매칭 마감일을 선택해주세요.");
		$("#deadline").focus();
		return false;
	} else if (new Date($('#deadline').val()) <= new Date()) {
		alert("매칭 마감일은 현재 날짜 이후로 선택 가능합니다.");
		$("#deadline").focus();
		return false;
	} else if ($('#content').val() == "") {
		alert("내용을 입력하세요.");
		$("#content").focus();
		return false;
	} else return true;
}