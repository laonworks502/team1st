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
    <c:if test="${sessionScope.loginBean.email != null && posts.board_id != 300}">
        applyCheck();
    </c:if>
    <c:if test="${sessionScope.loginBean.email != null && posts.board_id == 300}">
        studyCheck();
    </c:if>
    /*$(document).ready(function () {*/

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

    function applyCheck(){
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
    }

        function studyCheck(){
        // 스터디 참여 가능 여부 판별 ajax
            $.ajax({
                url: '/study/check/${no}',
                method: 'GET',
                 data: {email: '${posts.writer}'},
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result == 0) {
                        $('#joinStudy').attr('disabled', false);
                    } else if(result == 1) {
                        $('#joinStudy').attr('disabled', true);
                        $('#joinStudy').text('참여 완료');
                    } else if(result == 2){
                        $('#joinStudy').attr('disabled', true);
                        $('#joinStudy').text('기간이 지난 매칭입니다.');
                    }else if(result == 3){
                        $('#joinStudy').attr('disabled', true);
                        $('#joinStudy').text('매칭이 종료되었습니다.');
                    }
                },
                error: function (error, status, msg) {
                    alert("상태코드 " + status + "에러메시지" + msg);
                    return false;
                },
            })
        }

   /* });*/

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


    function joinStudy(){
        $.ajax({
            method: 'POST',
            url: "/study/matching/${no}",
            data:'${posts.writer}',
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
                            <%--<h5 class="content-title">제목</h5>--%>
                            <br>
                            <div style="width: 300px">
                                <h5 name="title" style="width: 250%" >${posts.title}</h5>
                            </div>
                        </div>
                        <h5 class="col-1"></h5>
                        <p class="col-8"></p>
                        <p class="col-2"></p>
                    </div>

                    <%-- 매칭 관련 내용--%>
                    <c:if test="${posts.board_id == 300}">
                        <p>총 매칭 인원</p>
                        <p>${sgb.total_members}명</p>
                        <p>매칭 가능 인원</p>
                        <p>${sgb.total_members - studyCount}명<p>
                        <p>매칭 가능일</p>
                        <p><fmt:formatDate value="${sgb.date}" pattern="yyyy. MM. dd"/> - <fmt:formatDate value="${sgb.deadline}" pattern="yyyy. MM. dd"/></p>
                        <br>
                        <c:if test="${sessionScope.loginBean.authority == '일반'}">
                            <button type="button" class="btn btn-success" id="joinStudy" onclick="joinStudy()">참여하기</button>
                        </c:if>
                    </c:if>

                    <div>
                        <h5 class="content-title">작성일</h5>
                        <p><fmt:formatDate value="${posts.date}" pattern="yyyy-MM-dd HH:mm"/></p>

                    </div>
                    <div class="post-container">
                        <h5 class="content-title">내용</h5>
                        <div class="content">
                            <textarea class="form-control" name="content" rows="3"
                                      style="width:100%; height:600px; resize:none;"
                                      readonly>${posts.content}</textarea>

                            </div>
                    </div>

                    <div class="board-footer">
                        <!--[스크랩 버튼]-->
                        <div class="scrap_wrap">
                            <c:if test="${sessionScope.loginBean.authority == '일반'}" >
                                <c:choose>
                                    <c:when test="${result == 1}"><c:set var="img_name" value="IconYesScrap.png" /> </c:when>
                                    <c:when test="${result == 0}"><c:set var="img_name" value="IconNoScrap.png" /></c:when>
                                </c:choose>
                                <img src="/resources/images/${img_name}" value="${result}" id="scrap_${posts.no}" style="width:30px; height:30px;" onclick="scrapClick(${posts.no});">
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

                        <c:if test="${sessionScope.loginBean.authority == '일반' && (board.id != '300')}">
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
