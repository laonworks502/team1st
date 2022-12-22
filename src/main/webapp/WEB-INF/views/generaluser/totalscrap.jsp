<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp"%>
<html>
<head>
    <title>마이 스크랩 더보기</title>

    <link rel="stylesheet" href="/resources/css/decototalscrap.css" />

    <!--[스크랩 버튼]-->
    <script>
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
                        location.href="/scrap/listTotalScrap/"+ ${board_id}
                        alert("out");
                    }
                }
                , error: function (e) {
                    alert("data error" + e);
                }

            });//$.ajax

        }
    </script>
</head>
<body>
<header>
    <%@ include file = "../common/header.jsp" %>
</header>
<main>
    <div class="myscrab_detaillist">
        <div class="totalscrap_total_wrap">
            <div class="totalscrap_subject_wrap">
                <strong><h3>전체 스크랩</h3></strong>
            </div>
            <div class="myscrap_totallist_wrap">
                <li class="board_subject">
                    <h4>${boardName} 게시판</h4>
                </li>
                <div class="card mb-4">
                    <div class="card-body">
                        <table class="table table-hover table-striped">
                            <thead>
                            <tr>
                                <th>제목</th>
                                <th>작성일</th>
                                <th>스크랩</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="myscrap" items="${myscrap}">
                                <tr>
                                    <td onclick="location.href='/boards/${myscrap.board_id}/${myscrap.no}'">${myscrap.title}</td>
                                    <td>${myscrap.date}</td>
                                    <td>
                                        <div class="scrapIconYesArea" id="scrapIconArea${myscrap.no}">
                                            <img src="/resources/images/IconYesScrap.png" value="${result}" id="scrap_${myscrap.no}" style="width:22px; height:22px;" onclick="scrapClick(${myscrap.no});">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>


        <div class="myscrap_pageination">
                <!-- 페이징 -->
                <nav class="center" aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <c:if test="${page>1}">
                                <a class="page-link" href="/boards/${board_id}?page=${page-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>
                        <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
                            <li class="page-item" id="page-item${i}">
                                <a class="page-link" href="/boards/${board_id}?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <script>
                            const pageItem=document.getElementById("page-item${page}");
                            pageItem.classList.add('active')
                        </script>
                        <li class="page-item">
                            <c:if test="${page < pg.pagesTotal}">
                                <a class="page-link" href="/boards/${board_id}?page=${page+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </ul>
                </nav>
        </div>
    </div>
</main>
</body>
</html>
