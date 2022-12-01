<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 수정</title>
</head>
<body>
<div class="my-5">
    <main class="mb-4">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <form method=post action="/postedit/${board_id}/${page}/${no}">
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h5 class="content-title">글수정</h5>
                                <h5 class="content-title">제목</h5>
                                <input type="text" name="title" value="${PostBean.title}" required></div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
							<textarea class="form-control" name="content" rows="3"
                                      style="width:90%; height:250px; resize:none;" maxlength="300" required>${PostBean.content}</textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <button type="submit" class="btn btn-outline-primary">수정</button>
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