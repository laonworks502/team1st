<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 목록</title>
</head>

<h5>전체 회원 수 : ${totalUsers}</h5>
<!-- 회원 목록 테이블 -->
	<table class="table table-hover w-auto">
		<thread class="table-light">
		<tr>
			<th style="width: 240px">이름</th>
			<th style="width: 170px">가입일</th>
			<th style="width: 110px" align="center"></th>
		</tr>
		</thread>
		<tbody>
			<!-- 가입한 회원이 없을 때 -->
			<c:if test="${empty nowmemberList }">
				<tr style="text-align: center">
					<td colspan=6><br>회원이 존재하지 않습니다.<br>
					<br></td>
				</tr>
			</c:if>

			<!-- 가입한 회원이 존재할 때 -->
			<c:if test="${not empty nowmemberList }">
				<c:forEach var="n" items="${nowmemberList }">

					<!-- 탈퇴한 회원 목록에 줄을 그어줌 -->
					<tr>
						<td>${n.member_email }</td>
						<td>${n.member_nickname }</td>
						<td><fmt:formatDate value="${n.member_reg_date }"
								pattern="yyyy-MM-dd" /></td>
						<td>
							<!-- 탈퇴한 회원일 경우 탈퇴일자를 표시 --> <c:if
								test="${ n.member_del_yn == 'Y' }"> ${n.member_del_date }</c:if>
							<c:if test="${ n.member_del_yn == 'N' }"></c:if>
						</td>
						<td>
							<button class="btn btn-warning" text-align="center"
								onClick="location.href='manager_deleteForm.do?member_email=${n.member_email}&page=${page }' ">
								상세정보</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	</body>
</html>