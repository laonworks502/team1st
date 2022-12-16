<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>글작성</title>
</head>
<body>
<%@ include file = "../common/header.jsp" %>
<div class="my-5">
    <main class="mb-4">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <form action="/boards/${board.id}" method=post>
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="writer" value="${sessionScope.email}">
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h5 class="content-title">글작성</h5>
                                <h5 class="content-title">제목</h5>
                                <div style="width: 300px">
                                    <input type="text" name="title" style="width: 250%" value="" maxlength="50">
                                </div>
                            </div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
							<textarea class="form-control" name="content" rows="3"
                                      style="width:90%; height:600px; resize:none;" maxlength="2000" placeholder = "내용을 작성해주세요" required></textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <button type="submit" class="btn btn-outline-primary">작성</button>
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
