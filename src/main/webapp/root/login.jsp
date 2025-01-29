<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty error}">
	${error}
</c:if>
<form action="Login.action" method="post">
	<p>ID<input type="text" name="id">
	<p>PASS<input type="password" name="pass">
	<input type="submit" value="確定">
</form>

<%@include file="/header_footer/footer.jsp"%>
