<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="container">
		<h2>일반 회원 목록</h2>
		<div class="panel panel-default">
			<div class="panel-heading">전체 회원 수(일반 회원): ${totalUsers}</div>
			<div class="panel-body">
				<div id="ajaxGeneralUsersList"></div>
				<!-- 회원 목록 출력 -->
				<!-- <button type="button" onclick="generalUsersList()"></button> -->
			</div>
		</div>
	</div>
