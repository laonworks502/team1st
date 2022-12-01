<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 실패</title>
</head>
<c:if test="${result == 1}">
    <script>
        alert("등록되지 않은 정보입니다.")
        history.go(-1);
    </script>
</c:if>
<c:if test="${result == 2}">
    <script>
        alert("비번이 다름")
        history.go(-1);
    </script>
</c:if>
</html>