<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<h3>更新内容入力</h3>

<c:if test="${not empty error}">
   	<c:out value="${error}"/>
</c:if>

<form action="UserUpdate.action" method="post">
	<input type="hidden" name="id" value="${id}"> 
	<input type="hidden" name="code" value="${code}">
		<p>新しいNAME<input type="text" name="name" value="${name}"></p>
		<p>新しいPASS<input type="password" name="pass" value="${pass}"></p>
		<p><input type="submit" value="更新"></p>
</form>

<%@include file="/header_footer/footer.jsp"%>