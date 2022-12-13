<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${board.name} 게시판</title>

    <!--[스크랩 버튼]-->
    <script>
        function scrapClick(no){
            alert(no);
            <!--[클릭 ajax]-->
            $.ajax({
                method: 'POST',
                url: "/scrap/" + no, //@PathVariable로 받음
                //data: no1,          //@RequestBody로 받음
                //data: JSON.stringify(no1),
                contentType:'application/json;charset=utf-8',
                success: function (data) {
                    alert(data);
                    if(data == 1){	//스크랩 O
                        $("#hiddenNoScrap"+no).hide();
                        $("#hiddenYesScrap"+no).show();

                        alert("in");
                    }else{        //스크랩 X
                        $("#hiddenYesScrap"+no).show();
                        $("#hiddenNoScrap"+no).hide();

                        alert("out");
                    }
                    location.reload();
                }
                ,error: function (e) {
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
        <h1 class="mt-4">${board.name} 게시판</h1>
        <h5> ${pg.postsTotal}개의 글이 기다리고 있어요!</h5>


        <div class="card mb-4">
            <div class="card-header">
                <button type="button" class="btn btn-primary float-end"
                	onClick="location.href='/boards/${board_id}/write-study?page=${pg.page}'">글 작성
                </button>
            </div>
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>스크랩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${posts}" var="posts">
                        <tr>
                            <td onclick="location.href='/boards/${board.id}/${posts.no}?page=${pg.page}'">${posts.title}</td>
                            <td>${posts.writerName}</td>
                            <td>${posts.date}</td>
                            <td>
                                <div class="scrapIconYesArea" id="scrapIconArea${posts.no}">
                                    <c:if test="${posts.scrapResult == 1}">
                                        <input type="image" id="hiddenYesScrap${posts.no}" value=${posts.scrapResult} src="<%=request.getContextPath()%>/images/IconNoScrap.png" width=22px height=22px onclick="scrapClick(${posts.no})">
                                    </c:if>
                                </div>
                                <div class="scrapIconNoArea" id="scrapIconArea${posts.no}">
                                    <c:if test="${posts.scrapResult == 0}">
                                        <input type="image" id="hiddenNoScrap${posts.no}" value=${posts.scrapResult} src="<%=request.getContextPath()%>/images/IconYesScrap.png" width=25px height=26px onclick="scrapClick(${posts.no})">
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
        <c:if test="${pg.page>1}">
            <a class="page-link" href="/boards/${board.id}?page=${pg.page-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </c:if>
        </li>
        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
            <li class="page-item" id="page-item${i}">
                <a class="page-link" href="/boards/${board.id}?page=${i}">${i}</a>
            </li>
                <script>
                    const pageItem=document.getElementById("page-item${pg.page}");
                    pageItem.classList.add('active')
                </script>
        </c:forEach>
        <li class="page-item">
            <c:if test="${pg.page < pg.pagesTotal}">
                <a class="page-link" href="/boards/${board.id}?page=${pg.page+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
</nav>
</body>
</html>