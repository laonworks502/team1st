
function edit_check(){

    if($.trim($("#passconfirm").val())==""){
        alert("비밀번호를 입력하세요!");
        $("#passconfirm").val("").focus();
        return false;
    }
    // if($.trim($("#passwd").val()) != $.trim($("#passconfirm").val())){
    //     //!=같지않다 연산. 비번이 다를 경우
    //     alert("비밀번호가 일치하지 않습니다!");
    //     $("#passconfirm").val("");
    //     $("#passconfirm").focus();
    //     return false;
    // }
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

    // if (confirm("수정하시겠습니까?") == true) { // 확인
    //     alert("수정되었습니다");
    //     document.editform.submit();
    // } else { // 취소
    //     return false;
    // }
}