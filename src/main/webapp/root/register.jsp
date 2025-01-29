<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:choose>
	<c:when test="${result}">
		<p>登録が成功しました。</p>
		<p>ようこそ:管理者<c:out value="${root.name}"/>さん</p>
	</c:when>
	<c:otherwise>
		<c:if test="${not empty error}">
			<p>${error}</p>
		</c:if>
		
		<form action="Register.action" method="post">
			<p>ID<input type="text" name="id" value="${id}">
			<p>NAME<input type="text" name="name" value="${name}">
			<p>PASS<input type="password" name="pass" value="${pass}">
			<p><input type="submit" value="確定">
		</form>
	</c:otherwise>
</c:choose>

<%@include file="/header_footer/footer.jsp"%>
