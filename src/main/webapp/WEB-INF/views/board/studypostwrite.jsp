<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file = "../common/commonlist.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>글작성</title>
</head>

<script>
    function postInsert() {
        let title = $("#title").val()
        let content = $("#content").val()
        let post1 = {'title': title, 'content': content};
            $.ajax({
                url: '/boards/${board_id}/study?page=${page}',
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

    function matchingInsert(no){
        let total_members = $("#total_members").val();
        let deadline = $("#deadline").val();
        let data = {'total_members': total_members, 'deadline': deadline};
        $.ajax({
            url: 'boards/${board_id}/'+no+'/study',
            method: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (result) {
                if(result == 1){
                    alert("매칭 생성 성공");
                    location.href="/boards/"+${board_id};
                }else{
                    alert("매칭 생성 실패");
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
                <form method=post>
                    <div class="container List-container">
                        <div class="row mt-1 header">
                            <div class="col-8">
                                <h5 class="content-title">글작성</h5>
                                <h5 class="content-title">제목</h5>
                                <div style="width: 300px">
                                    <input type="text" id="title" name="title" style="width: 250%" value="" maxlength="50">
                                </div>
                            </div>
                            <h5 class="col-1"></h5>
                            <p class="col-8"></p>
                            <p class="col-2"></p>
                            <p>매칭 인원 수</p>
                                <select class="form-select" id="total_members" aria-label="Default select example">
                                    <option selected>매칭 인원 수를 선택하세요.</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            <p>매칭 기간</p>
                                <select class="form-select" id="deadline" aria-label="Default select example">
                                    <option selected>매칭 기간을 선택하세요.</option>
                                    <option value="1">1일</option>
                                    <option value="2">2일</option>
                                    <option value="3">3일</option>
                                    <option value="4">4일</option>
                                    <option value="5">5일</option>
                                    <option value="6">6일</option>
                                    <option value="7">7일</option>
                                </select>
                        </div>
                        <div class="post-container">
                            <h5 class="content-title">내용</h5>
                            <div class="content">
							<textarea class="form-control" id="content" name="content" rows="3"
                                      style="width:90%; height:600px; resize:none;" maxlength="2000" placeholder = "내용을 작성해주세요" required></textarea>
                            </div>
                        </div>
                        <div class="board-footer">
                            <button type="submit" class="btn btn-outline-primary" onclick="postInsert()">작성</button>
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
