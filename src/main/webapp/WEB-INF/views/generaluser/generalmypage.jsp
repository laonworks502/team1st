<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../common/commonlist.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>마이 페이지</title>

    <link rel="stylesheet" href="/resources/css/decomypage.css" />

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
                        location.href="/scrap/generalmypage"

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
    <%@ include file = "../common/header.jsp" %>
<!-- 여기부터
    <nav class="nav nav-pills nav-justified">
        <c:forEach var="boardList" items="${sessionScope.boardList}">
            <a class="nav-link" id="${boardList.id}" href="/boards/${boardList.id}">${boardList.name} 게시판</a>
            <c:if test="${boardList.id == board.id}">
                <script>
                    const navActive = document.getElementById("${boardList.id}");
                    navActive.classList.add('active')
                </script>
            </c:if>
        </c:forEach>
    </nav>

    <input type="button" value="나의 글목록" class="input_button" onclick="location='/boards/${pb.board_id}/${pb.no}'">
    <button onclick="location='/generalmyboardlist'">내가 작성한 글</button>
    여기까지 -->

    <main>
        <div class="mypage_list">
            로그인성공

            <input type="text" name="email" value="${gub.email}" style="border: none" readonly>

            <input type="button" value="로그아웃" class="input_button" onclick="location='/loginselect'">
            <input type="button" value="회원수정" class="input_button" onclick="location='/general-user-edit'">
            <input type="button" value="회원탈퇴" class="input_button" onclick="location='/general-user-delete'">

            <%--<input type="button" value="스크랩목록" class="input_button" onclick="location='/totalscrap'">--%>
            <br><br>
            <input type="button" value="내 지원내역" class="input_button" onclick="location.href='/apply/applies'">
            <input type="button" value="스터디" class="input_button" onclick="location.href='/study/applies'">

            <!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
            <form action="/resume-upload" method="post" enctype="multipart/form-data">

                <br>
                <br>
                <br>이력서 업로드 : 파일 선택 후 업로드 버튼을 누르세요.
                <br>
                이력서 선택 : <input type="file" name="file">
                <input type="submit" value="업로드하기">

                <br>

                <%-- <a href="download.do?fname=<%=request.getContextPath()%>/upload/${fileName }"> --%>

                <br>
                등록된 이력서 :
                <a href="/download?resume=${gub.resume}">
                    ${gub.resume}
                </a>
                <br>
            </form>
        </div>

        <!--스크랩 영역-->
        <div class="myscrap_total_wrap">
            <div class="myscrap_subject_wrap">
                <strong><h3>내 스크랩</h3></strong>
            </div>
            <div class="myscrap_minilist_wrap">
                <li class="board_subject">
                    <h4>정규 게시판</h4>
                </li>
                <div class="card mb-4">
                    <div class="card-header">
                        <button type="button" class="btn btn-primary float-end"
                                onClick="location.href='/scrap/listTotalScrap/100'">더보기
                        </button>
                    </div>
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
                            <c:forEach var="myminiscrap100" items="${myminiscrap100}">
                                <tr>
                                    <td onclick="location.href='/boards/${myminiscrap100.board_id}/${myminiscrap100.no}'">${myminiscrap100.title}</td>
                                    <td>${myminiscrap100.date}</td>
                                    <td>
                                        <div class="scrapIconYesArea" id="scrapIconArea${myminiscrap100.no}">
                                            <img src="/resources/images/IconYesScrap.png" value="${result}" id="scrap_${myminiscrap100.no}" style="width:22px; height:22px;" onclick="scrapClick(${myminiscrap100.no});">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="myscrap_minilist_wrap">
                <li class="board_subject">
                    <h4>단기 게시판</h4>
                </li>
                <div class="card mb-4">
                    <div class="card-header">
                        <button type="button" class="btn btn-primary float-end"
                                onClick="location.href='/scrap/listTotalScrap/200'">더보기
                        </button>
                    </div>
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
                                <c:forEach var="myminiscrap200" items="${myminiscrap200}">
                                    <tr>
                                        <td onclick="location.href='/boards/${myminiscrap200.board_id}/${myminiscrap200.no}'">${myminiscrap200.title}</td>
                                        <td>${myminiscrap200.date}</td>
                                        <td>
                                            <div class="scrapIconYesArea" id="scrapIconArea${myminiscrap200.no}">
                                                <img src="/resources/images/IconYesScrap.png" value="${result}" id="scrap_${myminiscrap200.no}" style="width:22px; height:22px;" onclick="scrapClick(${myminiscrap200.no});">
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="myscrap_minilist_wrap">
                <li class="board_subject">
                    <h4>스터디 게시판</h4>
                </li>
                <div class="card mb-4">
                    <div class="card-header">
                        <button type="button" class="btn btn-primary float-end"
                                onClick="location.href='/scrap/listTotalScrap/300'">더보기
                        </button>
                    </div>
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
                                <c:forEach var="myminiscrap300" items="${myminiscrap300}">
                                    <tr>
                                        <td onclick="location.href='/boards/${myminiscrap300.board_id}/${myminiscrap300.no}'">${myminiscrap300.title}</td>
                                        <td>${myminiscrap300.date}</td>
                                        <td>
                                            <div class="scrapIconYesArea" id="scrapIconArea${myminiscrap300.no}">
                                                <img src="/resources/images/IconYesScrap.png" value="${result}" id="scrap_${myminiscrap300.no}" style="width:22px; height:22px;" onclick="scrapClick(${myminiscrap300.no});">
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
    </main>


</body>
</html>
