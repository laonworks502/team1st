<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${board.name} 게시판</title>

    <!--[스크랩 버튼]-->
    <script>
        // 스크랩 버튼 클릭 시 ajax
        function scrapClick(no) {
            alert(no);
            <!--[클릭 ajax]-->
            $.ajax({
                method: 'POST',
                url: "/scrap/" + no, //@PathVariable로 받음
                //data: no1,          //@RequestBody로 받음
                //data: JSON.stringify(no1),
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    alert(data);

                    if (data == 1) {	//스크랩 O
                        $("#scrap_" + no).attr("value", 1);
                        $("#scrap_" + no).attr("src", "/resources/images/IconYesScrap.png");
                        alert("in");
                    } else {        //스크랩 X

                        $("#scrap_" + no).attr("value", 0);
                        $("#scrap_" + no).attr("src", "/resources/images/IconNoScrap.png");

                        alert("out");
                    }
                }
                , error: function (e) {
                    alert("data error" + e);
                }

            });//$.ajax
        }

        <%--$(document).ready(function() {--%>
        <%--    for(var i=0; i< ${posts.size()}; i++) {--%>

        <%--        i= i+${pg.startPostNo};--%>
        <%--        console.log("i=",i);--%>
        <%--        console.log($("#hiddenNoScrap"+i).val());--%>
        <%--        if ($("#hiddenNoScrap")+i.val() == 1 ){--%>
        <%--                $(("#hiddenNoScrap"+i)).prop('type',"hidden");--%>
        <%--                $(("#hiddenYesScrap"+i)).prop('type',"image");--%>
        <%--        }else{--%>
        <%--            $(("#hiddenYesScrap"+i)).prop('type',"hidden");--%>
        <%--            $(("#hiddenNoScrap"+i)).prop('type',"image");--%>
        <%--        }--%>
        <%--    }--%>

        <%--    $("#scrapIconArea").click(function(){--%>


        <%--    });--%>
        <%--});--%>

    </script>
</head>
<body>

<%@ include file = "../common/header.jsp" %>
<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4" style="font-weight: bolder">${board.name} 게시판</h1>
        <h5 style="margin-bottom: 50px"> ${pg.postsTotal}개의 글이 기다리고 있어요!</h5>


        <div class="card mb-4">
            <div class="card-header">
                <c:if test="${sessionScope.loginBean.authority == '기업' && (board.id != '300')}">
                    <button type="button" class="btn btn-primary float-end"
                            onClick="location.href='/boards/${board.id}/write?page=${page}'">글 작성
                    </button>
                </c:if>
                <c:if test="${sessionScope.loginBean.authority == '일반' && board_id == '300'}">
                    <button type="button" class="btn btn-primary float-end"
                            onClick="location.href='/boards/${board.id}/write?page=${page}'">글 작성
                    </button>
                </c:if>
            </div>
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <c:if test="${sessionScope.loginBean.authority == '일반'}" >
                            <th >스크랩</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${posts}" var="posts">
                        <tr>
                            <td
                                onclick="location.href='/boards/${board.id}/${posts.no}?page=${pg.page}'">${posts.title}</td>
                            <td
                                onclick="location.href='/boards/${board.id}/${posts.no}?page=${pg.page}'">${posts.writerName}</td>
                            <td
                                onclick="location.href='/boards/${board.id}/${posts.no}?page=${pg.page}'">
                                <fmt:formatDate value="${posts.date}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>
                                <!--[스크랩 버튼]-->
                                <div class="scrap_wrap">
                                    <c:if test="${sessionScope.loginBean.authority == '일반'}" >
                                        <c:choose>
                                            <c:when test="${posts.scrapResult == 1}"><c:set var="img_name" value="IconYesScrap.png" /> </c:when>
                                            <c:when test="${posts.scrapResult == 0}"><c:set var="img_name" value="IconNoScrap.png" /></c:when>
                                        </c:choose>
                                        <img src="/resources/images/${img_name}" value="${result}" id="scrap_${posts.no}" style="width:30px; height:30px;" onclick="scrapClick(${posts.no});">
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<!-- 페이징 -->
<nav class="center" aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item">
        <c:if test="${page>1}">
            <a class="page-link" href="/boards/${board.id}?page=${page-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </c:if>
        </li>
        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
            <li class="page-item" id="page-item${i}">
                <a class="page-link" href="/boards/${board.id}?page=${i}">${i}</a>
            </li>
        </c:forEach>
            <script>
                const pageItem=document.getElementById("page-item${page}");
                pageItem.classList.add('active')
            </script>
        <li class="page-item">
            <c:if test="${page < pg.pagesTotal}">
                <a class="page-link" href="/boards/${board.id}?page=${page+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>
</body>
</html>
