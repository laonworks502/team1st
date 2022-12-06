/**
 * 기업정보 등록 JS
 */

function check(){
	
	 if($.trim($("#company_name").val())==""){
		 alert("기업명을 입력하세요!");
		 $("#company_name").val("").focus();
		 return false;
	 }

	 if($.trim($("#field").val())==""){
		 alert("업종 분야를 입력하세요!");
		 $("#field").val("").focus();
		 return false;
	 }
	 if($.trim($("#tbox2").val())==""){
		 alert("주소를 입력하세요!");
		 $("#tbox2").val("").focus();
		 return false;
	 }
	 if($.trim($("#tbox3").val())==""){
		 alert("상세주소를 입력하세요!");
		 $("#tbox3").val("").focus();
		 return false;
	 }
	 
	 if($.trim($("#foundation_date").val())==""){
		 alert("설립일을 입력하세요!");
		 $("#foundation_date").val("").focus();
		 return false;
	 }
	 if($.trim($("#tel1").val())==""){
		 alert("기업 전화번호를 입력하세요!");
		 $("#tel1").val("").focus();
		 return false;
	 }
	 if($.trim($("#tel2").val())==""){
		 alert("기업 전화번호를 입력하세요!");
		 $("#tel2").val("").focus();
		 return false;
	 }
	 if($.trim($("#tel3").val())==""){
		 alert("기업 전화번호를 입력하세요!");
		 $("#tel3").val("").focus();
		 return false;
	 } 	 
}