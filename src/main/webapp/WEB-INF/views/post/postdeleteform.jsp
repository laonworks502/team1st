<%--
  Created by IntelliJ IDEA.
  User: migang
  Date: 2022-11-30
  Time: 오후 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 상세 페이지 내에 추가 --%>
<script>
  function deletepost() {
    var result = confirm("해당 글을 삭제하시겠습니까?");

    if(result == true){
        location.href="";
    }else{
        return false;
    }
  }

</script>

</body>
</html>
