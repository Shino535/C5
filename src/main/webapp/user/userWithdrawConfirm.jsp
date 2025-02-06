<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<%--「userWithdrawConfirm」はユーザー退会確認の意味 --%>

<p>アカウントを退会しますか？</p>

<c:if test="${not empty error}">
	<p style="color: red;">${error}</p>
</c:if>

<form action="UserClose.action" method="post">
	<input type="submit" value="退会">
</form>
<a href="/C5/">トップページ</a>
<form action="/C5/">
	<input type="submit" value="キャンセル">
</form>


<%@include file="/header_footer/footer.jsp"%>