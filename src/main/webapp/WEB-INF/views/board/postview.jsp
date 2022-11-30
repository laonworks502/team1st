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
                            <input type="date" name="date" value="${post.date}" readonly>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
							<textarea class="form-control" name="content" rows="3"
                                      style="width:90%; height:600px; resize:none;" readonly>${post.content}</textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <button type="button" class="btn btn-info update-btn"
                                    onclick="location.href='#'">수정</button>
                            <button type="button" class="btn btn-warning delete-btn"
                                    onclick="location.href='#'">삭제</button>
                            <button type="button" class="btn btn-primary list-btn"
                                    onclick="history.go(-1);">뒤로가기</button>
                        </div>
                    </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>