<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원 목록</title>
</head>
<body>
	<script>
	function generalUsersList(){
  		$.ajax({
  			url:"/generaluserslist",
  			type:"get",
  			dataType:"json",
  			success: ajaxHtml(result),
  			error:function(){alert("error");}
  		});
  	}
	
	console.log("뷰페이지 들어옴");
	
	function ajaxHtml(result){
  		var html="<table class='table'>";
  		html+="<tr>";
  		html+="<td>이름</td>";
  		html+="<td>이메일</td>";
  		html+="<td>전화번호</td>";
  		html+="<td>가입일</td>";
  		html+="</tr>";
  		
  		$.each(result, (index, obj)=>{ 
  			html+="<tr>";
  	  		html+="<td>"+obj.name+"</td>";
  	  		html+="<td>"+obj.email+"</td>";
  	  		html+="<td>"+obj.tel1+obj.tel2+obj.tel3+"</td>";
  	  		html+="<td>"+obj.register_date+"</td>";
  	  		html+="</tr>";
  		})
  		html+="</table>";
  		
  		$("#ajaxGeneralUsersList").html(html);
  	}
  
	
</script>
	<div class="container">
		<h2>일반 회원 목록</h2>
		<div class="panel panel-default">
			<div class="panel-heading">전체 회원 수(일반 회원): ${totalUsers}</div>
			<div class="panel-body">
				<table class="table table-hover table-bordered">
					<tr>
						<td>이름</td>
						<td>이메일</td>
						<td>전화번호</td>
						<td>가입일</td>
					</tr>

					<c:forEach var="gu" items="${generalUsersList}">
						<tr>
							<td>${gu.name}</td>
							<td>${gu.email}</td>
							<td>${gu.tel1}-${gu.tel2}-${gu.tel3}</td>
							<td>${gu.register_date}</td>
						</tr>
					</c:forEach>

				</table>
				<div id="ajaxGeneralUsersList"></div> <!-- 회원 목록 출력 -->
			</div>
	</div>
	</div>

	<%-- <main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h5>전체 회원 수(일반 회원): ${totalUsers}</h5>

        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>회원명</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                        <th>가입일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${generalUsersList}" var="generalUsersList">
                        <tr onclick="location.href='/generaluserslist/${page}'">
                            <td>${generaluserslist.name}</td>
                            <td>${generaluserslist.email}</td>
                            <td>${generaluserslist.tel1}-${generaluserslist.tel2}-${generaluserslist.tel3}</td>
                            <td>${generaluserslist.register_date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
 --%>
	<!-- 페이징 -->
	<%-- <nav class="center" aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><c:if test="${adminpg.page>1}">
					<a class="page-link" href="/generaluserslist/${adminpg.page-1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a>
				</c:if></li>
			<c:forEach var="i" begin="${adminpg.startPage}"
				end="${adminpg.endPage}">
				<li class="page-item" id="page-item${i}"><a class="page-link"
					href="/generaluserslist/${i}">i</a></li>
				<script>
                    const pageItem=document.getElementById("page-item${adminpg.page}");
                    pageItem.classList.add('active')
                </script>
			</c:forEach>
			<li class="page-item"><c:if
					test="${adminpg.page<adminpg.pagesTotal}">
					<a class="page-link" href="/generaluserslist/${adminpg.page+1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a>
				</c:if></li>
		</ul>
	</nav> --%>
</body>
</html>















<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
</html> --%>