<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/commonlist.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 메인</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/adminmain">IGU 관리자</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" onClick="location.href='/adminlogout'; alert('로그아웃 되었습니다.');">로그아웃</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/main">Client-Side 메인</a>
                </li>

                <!-- 가입자 통계 : 일반 회원-->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown1" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        일반 회원 통계
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <button class="dropdown-item" id="userJoinChartDateButton" onclick="joinChartDate()">일별
                                가입자
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="userJoinChartWeekButton" onclick="joinChartWeek()">주별
                                가입자
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="userJoinChartMonthButton" onclick="joinChartMonth()">월별
                                가입자
                            </button>
                        </li>
                    </ul>
                </li>

                <!-- 가입자 통계 : 기업 회원-->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown2" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        기업 회원 통계
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <button class="dropdown-item" id="companyJoinChartDateButton"
                                    onclick="companyJoinChartDate()">일별
                                가입자
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="companyJoinChartWeekButton"
                                    onclick="companyJoinChartWeek()">주별
                                가입자
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="companyJoinChartMonthButton"
                                    onclick="companyJoinChartMonth()">월별
                                가입자
                            </button>
                        </li>
                    </ul>
                </li>

                <!-- 회원 관리 -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown3" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        회원 관리
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <button class="dropdown-item" id="generalUsersList"
                                    onclick="generalUsersListPage(); generalUsersList();">일반회원
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="companyUsersList"
                                    onclick="companyUsersListPage(); companyUsersList();">기업회원
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="companyList" onclick="companyListPage(); companyList();">
                                기업
                            </button>
                        </li>
                    </ul>
                </li>

                <!-- 게시글 관리 -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown4" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        게시글 관리
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <button class="dropdown-item" id="fulltimeBoard"
                                    onclick="fulltimeBoardPage(); fulltimeBoadList();">정규직 게시판
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="parttimeBoard"
                                    onclick="parttimeBoardPage(); parttimeBoadList();">파트타임 게시판
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" id="studiesList" onclick="studiesPage(); studyList();">스터디목록
                            </button>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<main style="margin-top:20px;">
    <!-- 그래프 나타나는 곳  -->
    <div id="chart"></div>
    <!-- 목록 나타나는 곳 -->
    <div id="ajaxGeneralList"></div>
    <div id="ajaxCompanyUsersList"></div>
    <div id="ajaxCompanyList"></div>
</main>

<script>

    //일반회원 일별 가입자 수
    function joinChartDate() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat1') // load('컨트롤러 안에 "" 이름쓰기"')
    }

    //일반회원 주별 가입자 수
    function joinChartWeek() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat2')
    }

    //일반회원 월별 가입자 수
    function joinChartMonth() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat3')
    }

    //기업회원 일별 가입자 수
    function companyJoinChartDate() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat4')
    }

    //기업회원 주별 가입자 수
    function companyJoinChartWeek() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat5')
    }

    //기업회원 월별 가입자 수
    function companyJoinChartMonth() {
        $('#ajaxList').hide()
        $('.table').hide()
        $('#chart').load('adminstat6')
    }

    //전체 일반 회원 목록 페이지
    function generalUsersListPage() {
        $('#chart').load('generaluserslistpage')
    }

    //전체 일반 회원 목록
    function generalUsersList() {

        $('#ajaxGeneralList').show()
        $('#ajaxCompanyUsersList').hide()
        $('#ajaxCompanyList').hide()
        $.ajax({
            url: "/generaluserslist",
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });


        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>이름</td>";
            html += "<td>이메일</td>";
            html += "<td>전화번호</td>";
            html += "<td>가입일</td>";
            html += "<td>관리</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td>" + obj.name.substring(0, 1) + "***" + "</td>";
                html += "<td>" + obj.email.substring(0, 1) + "***" + obj.email.substring(obj.email.indexOf("@")) + "</td>";
                html += "<td>" + obj.tel1 + "-" + obj.tel2.substring(0, 0) + "****" + "-" + obj.tel3 + "</td>";
                html += "<td>" + obj.register_date + "</td>";
                html += "<td><button type='button' onclick='javascript:adminGeneralDelete(\"" + obj.email + "\")'>삭제</button> </td>";
                html += "</tr>";
            })
            html += "</table>";

            $("#ajaxGeneralList").html(html);
        }

    }

    //일반 회원 삭제
    function adminGeneralDelete(email) {
        var result = confirm('삭제하시겠습니까?');
        if (result) {
            $.ajax({
                url: "/admingeneraluserdelete",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({'email': email}),
                success: function (result) {
                    if (result == 1) {
                        alert('삭제되었습니다.');
                        generalUsersListPage();
                        generalUsersList();
                    } else {
                        alert('삭제 실패');
                    }
                    console.log(result);
                }
            });
        }
        //alert(email);

    }

    //전체 기업 회원 목록 페이지
    function companyUsersListPage() {
        $('#chart').load('companyuserslistpage')
    }

    //전체 기업 회원 목록
    function companyUsersList() {

        $('#ajaxGeneralList').hide()
        $('#ajaxCompanyUsersList').show()
        $('#ajaxCompanyList').hide()
        $.ajax({
            url: "/companyuserslist",
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });

        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>기업명</td>";
            html += "<td>담당자명</td>";
            html += "<td>이메일</td>";
            html += "<td>전화번호</td>";
            html += "<td>가입일</td>";
            html += "<td>관리</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td>" + obj.company_name + "</td>";
                html += "<td>" + obj.name.substring(0, 1) + "***" + "</td>";
                html += "<td>" + obj.email.substring(0, 1) + "***" + obj.email.substring(obj.email.indexOf("@")) + "</td>";
                html += "<td>" + obj.tel1 + "-" + obj.tel2.substring(0, 0) + "****" + "-" + obj.tel3 + "</td>";
                html += "<td>" + obj.register_date + "</td>";
                html += "<td><button type='button' onclick='javascript:adminCompanyUserDelete(\"" + obj.email + "\")'>삭제</button> </td>";
                html += "</tr>";
            })
            html += "</table>";

            $("#ajaxCompanyUsersList").html(html);
        }

    }

    //기업 회원 삭제
    function adminCompanyUserDelete(email) {
        var result = confirm('삭제하시겠습니까?');
        if (result) {
            $.ajax({
                url: "/admincompanyuserdelete",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({'email': email}),
                success: function (result) {
                    if (result == 1) {
                        alert('삭제되었습니다.');
                        companyUsersListPage();
                        companyUsersList();
                    } else {
                        alert('삭제 실패');
                    }
                    console.log(result);
                }
            });
        }
        //alert(email);

    }

    //전체 기업 목록 페이지
    function companyListPage() {
        $('#chart').load('companylistpage')
    }

    //전체 기업 목록
    function companyList() {

        $('#ajaxGeneralList').hide()
        $('#ajaxCompanyUsersList').hide()
        $('#ajaxCompanyList').show()
        $.ajax({
            url: "/companylist",
            type: "get",
            dataType: "json",
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });

        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>기업명</td>";
            html += "<td>사업분야</td>";
            html += "<td>전화번호</td>";
            html += "<td>설립일</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td>" + obj.company_name + "</td>";
                html += "<td>" + obj.field + "</td>";
                html += "<td>" + obj.tel1 + "-";
                html += obj.tel2 + "-";
                html += obj.tel3 + "</td>";
                html += "<td>" + obj.foundation_date + "</td>";
                html += "</tr>";
            })
            html += "</table>";

            $("#ajaxCompanyList").html(html);
        }

    }

    //정규직 구인구직 게시판 페이지
    function fulltimeBoardPage() {
        $('#chart').load('fulltimeboardpage')
    }

    //정규직 구인구직 게시판 목록
    function fulltimeBoadList() {

        $('#ajaxGeneralList').hide()
        $('#ajaxCompanyUsersList').hide()
        $('#ajaxCompanyList').show()
        $.ajax({
            url: '/fulltimeboard',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });

        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>제목</td>";
            html += "<td>작성자</td>";
            html += "<td>작성일</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td hidden>" + obj.no + "</td>";
                html += "<td>" + obj.title + "</td>";
                html += "<td>" + obj.writerName + "</td>";
                html += "<td>" + obj.date + "</td>";
                html += "<td><button type='button' onclick='javascript:fulltimePostDelete(\"" + obj.no + "\")'>삭제</button> </td>";
                html += "</tr>";

            })
            html += "</table>";

            $("#ajaxCompanyList").html(html);

        }

    }

    // 정규직 게시글 삭제
    function fulltimePostDelete(no) {
        var result = confirm('해당 게시물을 삭제하시겠습니까? 삭제 후 복구는 불가능합니다.');
        if (result) {
            $.ajax({
                url: "/fulltimepostdelete",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({'no': no}),
                success: function (result) {
                    if (result == 1) {
                        alert('게시물이 삭제되었습니다.');
                        fulltimeBoardPage();
                        fulltimeBoadList();
                    } else {
                        alert('게시물 삭제 실패');
                    }
                    console.log(result);
                }
            });
        }
    }

    //파트타임 구인구직 게시판 페이지
    function parttimeBoardPage() {
        $('#chart').load('parttimeboardpage')
    }

    //파트타임 구인구직 게시판 목록
    function parttimeBoadList() {

        $('#ajaxGeneralList').hide()
        $('#ajaxCompanyUsersList').hide()
        $('#ajaxCompanyList').show()
        $.ajax({
            url: '/parttimeboard',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });

        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>제목</td>";
            html += "<td>작성자</td>";
            html += "<td>작성일</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td hidden>" + obj.no + "</td>";
                html += "<td>" + obj.title + "</td>";
                html += "<td>" + obj.writerName + "</td>";
                html += "<td>" + obj.date + "</td>";
                html += "<td><button type='button' onclick='javascript:parttimePostDelete(\"" + obj.no + "\")'>삭제</button> </td>";
                html += "</tr>";

            })
            html += "</table>";

            $("#ajaxCompanyList").html(html);

        }

    }

    //파트타임 게시글 삭제
    function parttimePostDelete(no) {
        var result = confirm('해당 게시물을 삭제하시겠습니까? 삭제 후 복구는 불가능합니다.');
        if (result) {
            $.ajax({
                url: "/parttimepostdelete",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({'no': no}),
                success: function (result) {
                    if (result == 1) {
                        alert('게시물이 삭제되었습니다.');
                        parttimeBoardPage();
                        parttimeBoadList();
                    } else {
                        alert('게시물 삭제 실패');
                    }
                    console.log(result);
                }
            });
        }
    }

    //스터디 게시판 페이지
    function studiesPage() {
        $('#chart').load('studiespage')
    }

    //스터디 목록
    function studyList() {

        $('#ajaxGeneralList').hide()
        $('#ajaxCompanyUsersList').hide()
        $('#ajaxCompanyList').show()
        $.ajax({
            url: '/studylist',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (data) {
                console.log(data);
                ajaxHtml(data);

            },
            error: function () {
                alert("error");
            }
        });

        function ajaxHtml(result) {
            var html = "<table class='table'>";
            html += "<tr>";
            html += "<td>제목</td>";
            html += "<td>작성자</td>";
            html += "<td>작성일</td>";
            html += "</tr>";

            $.each(result, function (index, obj) {

                html += "<tr>";
                html += "<td hidden>" + obj.no + "</td>";
                html += "<td>" + obj.title + "</td>";
                html += "<td>" + obj.writerName + "</td>";
                html += "<td>" + obj.date + "</td>";
                html += "<td><button type='button' onclick='javascript:studyDelete(\"" + obj.no + "\")'>삭제</button> </td>";
                html += "</tr>";

            })
            html += "</table>";

            $("#ajaxCompanyList").html(html);

        }

    }

    //스터디 삭제
    function studyDelete(no) {
        var result = confirm('해당 게시물을 삭제하시겠습니까? 삭제 후 복구는 불가능합니다.');
        if (result) {
            $.ajax({
                url: "/studydelete",
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({'no': no}),
                success: function (result) {
                    if (result == 1) {
                        alert('게시물이 삭제되었습니다.');
                        studiesPage();
                        studyList();
                    } else {
                        alert('게시물 삭제 실패');
                    }
                    console.log(result);
                }
            });
        }
    }

</script>


</body>
</html>