<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<div class="logout-container">
	<div class="logout-title">ログアウトしますか？</div>
	<form action="Logout.action" method="post">
		<input type="submit" class="button-sbmt active logout-button" value="ログアウト">
	</form>
	<a href="/C5/" class="button-s">トップページ</a>
</div>

<%@include file="/header_footer/footer.jsp"%>
