<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 수정</title>
</head>
<script>
    function postUpdate() {
        var title = $("#title").val()
        var content = $("#content").val()
        var post1 = {'title': title, 'content': content};
        var result = confirm('해당 글을 수정하시겠습니까?');
        if (result) {
            $.ajax({
                url: '/boards/${board_id}/${no}?page=${page}',
                method: 'PUT',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(post1),
                success: function (result) {
                    alert(result);
                    if (result == 1) {
                        alert("수정 성공");
                        location.href = "/boards/" + ${board_id} +"/" + ${no} +"?page=" + ${page};  // 글 상세보기로 이동
                    } else {
                        alert("수정 실패");
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
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <form method=post>
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h5 class="content-title">글수정</h5>
                                <h5 class="content-title">제목</h5>
                                <input type="text" id="title" name="title" value="${PostBean.title}" required></div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
							<textarea class="form-control" id="content"  name="content" rows="3"
                                      style="width:90%; height:250px; resize:none;" maxlength="300"
                                      required>${PostBean.content}</textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <button type="button" class="btn btn-outline-primary" id="edit" onclick="postUpdate()">수정
                            </button>
                            <button type="button" class="btn btn-outline-secondary"
                                    onclick="history.go(-1);">취소
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>

</body>
</html>