<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${boardName} 게시판</title>
</head>
<body>
<div class="my-5">
    <main class="mb-4">
        <form method="post" action="/posteditform/${board_id}/${page}/${no}">
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
                                    <input type="submit" class="btn btn-outline-primary" value="수정">
                                    <button type="button" class="btn btn-outline-danger"
                                            onclick="deletepost()">삭제</button>
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
<script>
    function deletepost() {
        var result = confirm("해당 글을 삭제하시겠습니까?");

            if(result == true){
                location.href="/postdelete/${board_id}/${page}/${no}";
            }else{
                return false;
            }
    }
</script>

</html>
