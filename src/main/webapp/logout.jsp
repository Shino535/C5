<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<div class="logout-container">
	<c:choose>
		<c:when test="${result}">
			<p>ログアウトしますか？</p>
			<form action="Logout.action" method="post">
				<input type="submit" value="ログアウト">
			</form>
			<a href="/C5/">トップページ</a>
		</c:when>
		<c:otherwise>
			<p>ログアウトしますか？</p>
			<form action="Logout.action" method="post">
				<input type="submit" value="ログアウト">
			</form>
			<a href="/C5/">トップページ</a>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/header_footer/footer.jsp"%>
