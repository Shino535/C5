<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<div class="userwithdraw-container">
	<div class="userwithdraw-title">アカウントを退会しますか？</div>
	
	<c:if test="${not empty error}">
		<div class="userwithdraw-error">${error}</div>
	</c:if>
	
	<form action="UserClose.action" method="post">
		<input type="submit" value="退会" class="button-sbmt active">
	</form>
	<a href="/C5/" class="button-s">トップページ</a>
</div>

<%@include file="/header_footer/footer.jsp"%>