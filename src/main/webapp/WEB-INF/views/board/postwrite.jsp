<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>글작성</title>
</head>
<script src="/resources/js/boardcheck.js"></script>
<script>
    function postInsert() {
        let result = matchingCheck();
        if(result){
            let title = $("#title").val()
            let content = $("#content").val()
            let post1 = {'title': title, 'content': content};
            $.ajax({
                url: '/study/${board_id}?page=${page}',
                method: 'POST',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(post1),
                success: function (data) {
                    if (data.result == 1) {
                        alert("글 작성 성공");
                        matchingInsert(data.no);
                    } else {
                        alert("글 작성 실패");
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

    function matchingInsert(no){
        let total_members = $("#total_members").val();
        let deadline = $("#deadline").val();
        let data = {'total_members': total_members, 'deadline': deadline};
        $.ajax({
            url: '/study/macthing/${board_id}/'+no,
            method: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (result) {
                if(result == 1){
                    alert("매칭 생성 성공");
                    location.href="/boards/"+${board_id};
                }else{
                    alert("매칭 생성 실패. 다시 시도해주세요.");
                    return false;
                }
            },
            error: function (error, status, msg){
                alert("상태코드 " + status + "에러메시지" + msg);
                return false;
            }
        })
    }

</script>

<body>
<%@ include file = "../common/header.jsp" %>
<div class="my-5">
    <main class="mb-4">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <form action="/boards/${board.id}" onsubmit="return boardCheck()" method=post>
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="writer" value="${sessionScope.email}">
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h3 class="content-title" style="font-weight: bolder">글작성</h3><br>
                                <h5 class="content-title" style="font-weight: bolder">제목</h5>
                                <div>
                                    <input type="text" id="title" name="title" style="width: 136%; height:35px;" value="" maxlength="50">
                                </div><br>
                            </div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                        </div>
                        <c:if test="${sessionScope.loginBean.authority == '일반' && board.id == 300}">
                            <h5 style="font-weight: bolder">매칭 인원수 (최소 인원 : 2명)</h5>
                            <select class="form-select" id="total_members" aria-label="Default select example" style="width: 30%">
                                <option selected value="">매칭 인원수를 선택하세요.</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select><br>
                            <h5 style="font-weight: bolder">매칭 마감일</h5>
                            <input type="date" id="deadline">
                        </c:if>
                        <div class="post-container"><br>
                            <h5 class="content-title" style="font-weight: bolder">내용</h5>
                            <div class="content">
							<textarea class="form-control" id="content" name="content" rows="3"
                                      style="width:90%; height:600px; resize:none;" maxlength="2000" placeholder = "내용을 작성해주세요"></textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <c:if test="${sessionScope.loginBean.authority == '기업' && (board.id == 100 || board.id == 200)}">
                                <button type="submit" class="btn btn-outline-primary">작성</button>
                            </c:if>
                            <c:if test="${sessionScope.loginBean.authority == '일반' && board.id == 300}">
                                <button type="button" class="btn btn-outline-primary" onclick="postInsert()">작성</button>
                            </c:if>
                            <button type="button" class="btn btn-outline-secondary"
                                    onclick="history.go(-1);">취소</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>

</body>
</html>
