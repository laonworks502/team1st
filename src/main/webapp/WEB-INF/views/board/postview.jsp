<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${board.name} 게시판</title>
</head>
<script>

    // [로드 되자마자 실행될 ajax]
    $(document).ready(function () {

        <!--[스크랩 버튼]-->
/*        $.ajax({
            method: 'GET',
            url: "/scrap/" + ${no},
            async: false,
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                if (data == 1) {
                    $("#hiddenNoScrap").prop('type', "hidden");
                    $("#hiddenYesScrap").prop('type', "image");
                } else {
                    $("#hiddenYesScrap").prop('type', "hidden");
                    $("#hiddenNoScrap").prop('type', "image");
                }
            }
            , error: function (e) {
                alert("data error" + e);
            }

        });//$.ajax*/

        // 지원 유무 판별 ajax
        $.ajax({
            url: '/apply/${no}',
            method: 'GET',
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result == 1) {
                    $('#apply').attr('disabled', true);
                } else {
                    $('#apply').attr('disabled', false);
                }
            },
            error: function (error, status, msg) {
                alert("상태코드 " + status + "에러메시지" + msg);
                return false;
            },
        })

        // 스터디 참여 가능 여부 판별 ajax
        $.ajax({
            url: '/study/${no}',
            method: 'GET',
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result == 1) {
                    $('#joinStudy').attr('disabled', true);
                } else {
                    $('#joinStudy').attr('disabled', false);
                }
            },
            error: function (error, status, msg) {
                alert("상태코드 " + status + "에러메시지" + msg);
                return false;
            },
        })

    });

    // 지원 ajax
    function apply() {
        $.ajax({
            url: '/apply/${no}',
            method: 'POST',
            contentType: 'application/json;charset=utf-8',
            success: function (result) {
                if (result == 1) {
                    alert("지원 완료");
                    $('#apply').attr('disabled', true);
                } else {
                    alert("지원 실패. 다시 시도해주세요.");
                    return false;
                }
            },
            error: function (error, status, msg) {
                alert("상태코드 " + status + "에러메시지" + msg);
                return false;
            },
        })
    }

    function postDelete() {
        var result = confirm('해당 글을 삭제하시겠습니까?');
        if (result) {
            // 글 삭제 ajax
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
                    $("#hiddenNoScrap" + no).show();
                    $("#hiddenYesScrap" + no).hide();

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
    }

    JSON.stringify({"email" : '${post.writer}'});
    alert(JSON.stringify({"email" : '${post.writer}'}));

    function joinStudy(){
        $.ajax({
            method: 'POST',
            url: "/study/matching/${no}",
            data:'${post.writer}',
            contentType:'application/json;charset=utf-8',
            success: function (data){
                if(data == 1){
                    alert("참여 완료");
                    $('#joinStudy').attr('disabled', true);
                }else{
                    alert("참여 실패. 다시 시도해주세요.")
                    return false;
                }
            }, error: function (){

            }
        });
    }


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
                                <input type="text" name="title" style="width: 250%" value="${posts.title}"
                                       maxlength="50" readonly>
                            </div>
                        </div>
                        <h5 class="col-1"></h5>
                        <p class="col-8"></p>
                        <p class="col-2"></p>
                    </div>

                    <%-- 매칭 관련 내용--%>
                    <c:if test="${posts.board_id == 300}">
                        <p>총 매칭 인원</p>
                        ${sgb.total_members}
                        <p>매칭 가능 인원</p>
                        ${sgb.total_members - studyCount}
                        <p>매칭 가능일</p>
                        <fmt:formatDate value="${sgb.date}" pattern="yyyy. MM. dd"/> - <fmt:formatDate value="${sgb.deadline}" pattern="yyyy. MM. dd"/>
                        <br>
                        <button type="button" class="btn btn-success" id="joinStudy" onclick="joinStudy()">참여하기</button>
                    </c:if>

                    <div>
                        <h5 class="content-title">작성일</h5>
                        <fmt:formatDate value="${posts.date}" pattern="yyyy-MM-dd HH:mm"/>
                        <%--<input type="date" name="date" value="${post.date}" readonly>--%>
                    </div>
                    <div class="post-container">
                        <h5 class="content-title">내용</h5>
                        <div class="content">
                                <textarea class="form-control" name="content" rows="3"
                                          style="width:90%; height:600px; resize:none;"
                                          readonly>${posts.content}</textarea>
                            </div>
                    </div>

                    <div class="board-footer">
                        <!--[스크랩 버튼]-->
                        <div class="scrap_wrap">
                            <c:if test="${sessionScope.loginBean.authority == '일반'}" >
                                <div class="scrapIconYesArea" id="scrapIconArea${posts.no}">
                                    <c:if test="${result == 1}">
                                        <input type="image" id="hiddenYesScrap${posts.no}"
                                               value=${result} src="/resources/images/IconYesScrap.png"
                                               width=30px height=30px onclick="scrapClick(${posts.no})">
                                    </c:if>
                                </div>
                                <div class="scrapIconNoArea" id="scrapIconArea${posts.no}">
                                    <c:if test="${result == 0}">
                                        <input type="image" id="hiddenNoScrap${posts.no}"
                                               value=${result} src="/resources/images/IconNoScrap.png"
                                               width=33px height=34px onclick="scrapClick(${posts.no})">
                                    </c:if>
                                </div>
                            </c:if>
                        </div>

                        <c:if test="${posts.writer == sessionScope.loginBean.email}">
                            <button type="button" class="btn btn-outline-primary"
                                    onclick="location.href='/boards/${board_id}/${no}/edit?page=${page}'">수정
                            </button>
                            <button type="button" class="btn btn-outline-danger"
                                    onclick="postDelete()">삭제
                            </button>
                        </c:if>

                        <c:if test="${sessionScope.loginBean.authority == '일반' && (board.id == '100' || board.id == '200')}">
                            <button type="button" class="btn btn-success" id="apply"
                                    onclick="apply()">지원하기
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
