<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${boardName} 게시판</title>
</head>
<script>

    function postDelete() {
        var result = confirm('해당 글을 삭제하시겠습니까?');
        if (result) {
            $.ajax({
                url: '/boards/${board_id}/${no}',
                method: 'DELETE',
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result == 1) {
                        alert("삭제 성공");
                        location.href = "/boards/" + ${board_id} +"?page=" + ${page}; // 글 목록으로 이동
                    } else {
                        alert("삭제 실패");
                        return false;
                    }
                },
                error: function (error, status, msg) {
                    alert("상태코드 " + status + "에러메시지" + msg);
                    return false;
                },
            })
        }
    }

    <!--[스크랩 버튼]-->
        $(document).ready(function() {

        <!--[로드되자마자 실행될 ajax]-->
        $.ajax({
            method: 'GET',
            url: "/scrap/" + ${no},
            async :false,
            contentType:'application/json;charset=utf-8',
            success: function (data){
                if(data ==1){
                    $("#hiddenNoScrap").prop('type',"hidden");
                    $("#hiddenYesScrap").prop('type',"image");
                }else{
                    $("#hiddenYesScrap").prop('type',"hidden");
                    $("#hiddenNoScrap").prop('type',"image");
                }
            }
            ,error: function (e) {
                alert("data error" + e);
            }

        });//$.ajax

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
                            $("#hiddenNoScrap" + no).hide();
                            $("#hiddenYesScrap" + no).show();

                            alert("in");
                        } else {        //스크랩 X
                            $("#hiddenYesScrap" + no).show();
                            $("#hiddenNoScrap" + no).hide();

                            alert("out");
                        }
                        location.reload();
                    }
                    , error: function (e) {
                        alert("data error" + e);
                    }

                });//$.ajax
            };
    });

</script>

<body>
<%@ include file="../common/header.jsp" %>
<div class="my-5">
    <main class="mb-4">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h5 class="content-title">글 상세보기</h5>
                                <h5 class="content-title">제목</h5>
                                <div style="width: 300px">
                                    <input type="text" name="title" style="width: 250%" value="${post.title}"
                                           maxlength="50" readonly>
                                </div>
                            </div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                        </div>
                        <div>
                            <h5 class="content-title">작성일</h5>
                            ${post.date}
                            <%--<input type="date" name="date" value="${post.date}" readonly>--%>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
                                <textarea class="form-control" name="content" rows="3"
                                          style="width:90%; height:600px; resize:none;"
                                          readonly>${post.content}</textarea>
                            </div>
                            <!--스크랩 영역-->
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
                        </div>

                        <div class="board-footer">
                            <c:if test="${post.writer == sessionScope.loginBean.email}">
                            <button type="button" class="btn btn-outline-primary"
                                    onclick="location.href='/boards/${board_id}/${no}/edit?page=${page}'">수정
                            </button>
                            <button type="button" class="btn btn-outline-danger"
                                    onclick="postDelete()">삭제
                            </button>
                            </c:if>
                            <button type="button" class="btn btn-outline-secondary"
                                    onclick="history.go(-1);">뒤로가기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
    </main>
</div>
</body>


</html>
