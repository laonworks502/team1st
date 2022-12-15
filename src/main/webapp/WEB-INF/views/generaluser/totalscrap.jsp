<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/commonlist.jsp"%>
<html>
<head>
    <title>마이 스크랩 더보기</title>

    <%--<link rel="stylesheet" href="/resources/css/decototalscrap.css" />--%>

    <style>
        *{
            padding: 0;
            margin:0;
        }

        body {
            margin: 0;
            padding: 0;
            display: flex;
            flex-flow: column nowrap;
            justify-content: center;
            align-items: center;
            overflow-x: hidden;
            background-color: antiquewhite;
        }

        main {
            width:1180px;
            min-width: 1180px;
            background-color: antiquewhite;
        }

        ul,li {
            list-style: none;
        }
        .subject{
            margin-bottom: 100px;

        }
    </style>
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
                        $("#hiddenNoScrap"+no).show();
                        $("#hiddenYesScrap"+no).hide();
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
    </script>
</head>
<body>
<%--<%@ include file = "../common/header.jsp" %>--%>
<main>
    <div class="myscrab_detaillist">

        로그인성공

        <input type="text" name="email" value="${gub.email}" style="border: none" readonly>
        <input type="text" name="name" value="${gub.name}" style="border: none" readonly>

<%--        <div class="totalscrap_total_wrap">--%>
<%--            <div class="totalscrap_subject_wrap">--%>
<%--                <ul class="subject" >--%>
<%--                    <h3>전체 스크랩</h3>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--            <div class="myscrap_totallist_wrap">--%>
<%--                <li class="board_subject">--%>
<%--                    <h4>${boardName} 게시판</h4>--%>
<%--                </li>--%>
<%--                <div class="card mb-4">--%>
<%--                    <div class="card-body">--%>
<%--                        <table class="table table-hover table-striped">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th>제목</th>--%>
<%--                                <th>작성일</th>--%>
<%--                                <th>스크랩</th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <c:forEach var="myscrap" items="${myscrap}">--%>
<%--                                <tr>--%>
<%--                                    <td onclick="location.href='/boards/${myscrap.board_id}/${myscrap.no}'">${myscrap.title}</td>--%>
<%--                                    <td>${myscrap.date}</td>--%>
<%--                                    <td>--%>
<%--                                        <div class="scrapIconYesArea" id="scrapIconArea${myscrap.no}">--%>
<%--                                            <input type="image" id="hiddenYesScrap${myscrap.no}"  src="/resources/images/IconYesScrap.png" width=22px height=22px onclick="scrapClick(${myscrap.no})">--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>


<%--        <div class="myscrap_pageination">--%>
<%--            <c:if test="${listcount>0}">--%>
<%--                <!-- 1페이지로 이동 -->--%>
<%--                <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=1" style="text-decoration:none">--%>
<%--                    <i class="bi bi-chevron-double-left"></i>--%>
<%--                </a>--%>

<%--                <!-- 이전 블럭으로 이동 -->--%>
<%--                <c:if test="${startPage > 10}">--%>
<%--                    <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${stratPage-10}">--%>
<%--                        <i class="bi bi-chevron-left"></i>--%>
<%--                    </a>--%>
<%--                </c:if>--%>

<%--                <!-- 각 블럭에 10개의 페이지 출력 -->--%>
<%--                <c:forEach var="i" begin="${startPage}" end="${endPage}">--%>
<%--                    <c:if test="${i==page}">&nbsp;&nbsp;${i}&nbsp;&nbsp;</c:if> <!-- 현재페이지 -->--%>
<%--                    <c:if test="${i!=page}"><a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${i}">--%>
<%--                        &nbsp;&nbsp;${i}&nbsp;&nbsp;</a></c:if> <!-- 현재페이지가 아닌 경우 -->--%>
<%--                </c:forEach>--%>

<%--                <!-- 다음 블럭으로 이동 -->--%>
<%--                <c:if test="${endPage < pageCount}">--%>
<%--                    <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${startPage+10}">--%>
<%--                        <i class="bi bi-chevron-right"></i>--%>
<%--                    </a>--%>
<%--                </c:if>--%>

<%--                <!-- 마지막페이지로 이동 -->--%>
<%--                <a href="<%=request.getContextPath()%>/mypage/mylikeList.do?page=${pageCount}" style="text-decoration:none">--%>
<%--                    <i class="bi bi-chevron-double-right"></i>--%>
<%--                </a>--%>
<%--            </c:if>--%>
<%--        </div>--%>
    </div>
</main>
</body>
</html>
