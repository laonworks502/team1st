<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>스터디</title>
    <%@ include file="../common/header.jsp" %>
</head>
<body>

<script>
    $(document).ready(function () {
        $.ajax({
            method: 'GET',
            url: "/study/list",
            success: function (data) {
                $.each(data, function (index, item) {
                    var no = item.no;
                    var board_id = item.board_id;
                    var title = item.title;
                    var host_email = item.host_email;
                    var total_members = item.total_members;
                    var member_count = item.member_count;
                    var date = item.date;
                    var deadline = item.deadline;

                    var millis1 = Date.parse(date);
                    var date = new Date(millis1).toLocaleDateString();

                    var millis2 = Date.parse(deadline);
                    var deadline = new Date(millis2).toLocaleDateString();

                    if ('${sessionScope.loginBean.email}' == host_email) {
                        var row = $("<tr/>").append(
                            $("<td onclick='board(" +no + "," + board_id+")'/>").text(title),
                            $("<td/>").text(host_email),
                            $("<td/>").text(member_count +"/" + total_members),
                            $("<td/>").text(date),
                            $("<td/>").text(deadline),
                            $("<td/>").html('<button type="button" class="btn btn-danger" onclick="cancelMyStudy(' + no + ')">매칭 삭제하기</button>'),
                        );
                        $(".tbody1").append(row);
                    }

                    if ('${sessionScope.loginBean.email}' != host_email) {
                        var row = $("<tr/>").append(
                            $("<td onclick='board(" +no + "," + board_id+")'/>").text(title),
                            $("<td/>").text(host_email),
                            $("<td/>").text(member_count +"/" + total_members),
                            $("<td/>").text(date),
                            $("<td/>").text(deadline),
                            $("<td/>").html('<button type="button" class="btn btn-danger" onclick="cancelStudy(' + no + ')">매칭 참여 취소</button>'),
                        );
                        $(".tbody2").append(row);
                    }
                })
            }
            , error: function (e) {
                alert("data error" + e);
            }
        });
    });

    function cancelStudy(no) {
        var result = confirm("해당 스터디에서 탈퇴하시겠습니까?");
        if (result) {
            $.ajax({
                url: "/study/1/" + no,
                method: 'DELETE',
                success: function (result) {
                    if (result == 1) {
                        alert("탈퇴 완료");
                        location.href='/study/listForm';
                    }else{
                        alert("탈퇴 실패. 다시 시도해주세요.");
                    }
                }, error: function (e) {
                    alert("data error" + e);
                }
            })
        }
    }

    function cancelMyStudy(no) {
        var result = confirm("해당 스터디를 삭제 하시겠습니까?");
        if(result){
            var result2 = confirm("스터디 삭제 시 참여 멤버 모두 탈퇴되며, 해당 글이 삭제되고 복구할 수 없습니다. \n \n 정말 삭제하시겠습니까?")
            if (result2) {
                $.ajax({
                    url: "/study/2/" + no,
                    method: 'DELETE',
                    success: function (result) {
                        if (result == 1) {
                            alert("삭제 완료");
                            location.href='/study/listForm';
                        }else{
                            alert("삭제 실패. 다시 시도해주세요.");
                        }
                    }, error: function (e) {
                        alert("data error" + e);
                    }
                })
            }
        }
    }

    function board(no, board_id){
        location.href='/boards/' + board_id + "/" + no;
    }

</script>

<main class="mt-2 pt-2">
    <div class="container-fluid px-4">
        <h1 class="mt-4">내 스터디</h1>
        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>글 제목</th>
                        <th>호스트 이메일</th>
                        <th>매칭 현황</th>
                        <th>매칭 생성일</th>
                        <th>매칭 마감일</th>
                        <th>취소</th>
                    </tr>
                    </thead>
                    <tbody class="tbody1"></tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="container-fluid px-4">
        <h1 class="mt-4">참여 스터디</h1>
        <div class="card mb-4">
            <div class="card-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>글 제목</th>
                        <th>호스트 이메일</th>
                        <th>매칭 현황</th>
                        <th>매칭 생성일</th>
                        <th>매칭 마감일</th>
                        <th>취소</th>
                    </tr>
                    </thead>
                    <tbody class="tbody2"></tbody>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
</html>