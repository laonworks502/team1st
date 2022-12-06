<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
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
                        location.href = "/boards/" + ${board_id} + "?page=" + ${page}; // 글 목록으로 이동
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

</script>

<body>
<div class="my-5">
    <main class="mb-4">
        <form method=post>
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                        <div class="container List-container">
                            <div class="row mt-1 header">
                                <div class="col-8">
                                    <h5 class="content-title">글 상세보기</h5>
                                    <h5 class="content-title">제목</h5>
                                    <div style="width: 300px">
                                        <input type="text" name="title" style="width: 250%" value="${post.title}" maxlength="50" readonly>
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
                                          style="width:90%; height:600px; resize:none;" readonly>${post.content}</textarea>
                                </div>
                                <button type="button" class="btn btn-outline-dark">스크랩</button>
                            </div>

                             <div class="board-footer">
                               <%--<c:if test="${post.writer == }">--%>
                                    <input type="submit" class="btn btn-outline-primary" value="수정"
                                        onclick="location.href='/boards/${board_id}/${no}/edit?page=${page}'">
                                    <button type="button" class="btn btn-outline-danger"
                                            onclick="postDelete()">삭제</button>
                                <%--</c:if>--%>
                                <button type="button" class="btn btn-outline-secondary"
                                        onclick="history.go(-1);">뒤로가기</button>
                             </div>
                        </div>
                </div>
            </div>
        </form>
    </main>
</div>
</body>


</html>
