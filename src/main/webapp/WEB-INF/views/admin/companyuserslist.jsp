<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <h2>기업 회원 목록</h2>
    <div class="panel-heading">전체 회원 수(일반 회원): ${totalCompanyUsers}</div>
    <div class="panel panel-default" style="text-align: right;">
        <button>개인정보 마스킹 해지</button>
        <div class="panel-body">

            <div id="ajaxList"></div>
        </div>
    </div>
</div>
