<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp"%>


<h2>削除確認</h2>
<p>以下の利用者を削除しますか？</p>

<c:forEach var="name" items="${deleteName}">
	<p>${name}</p>
</c:forEach>

<form action="UserDelete.action" method="post">
	<c:forEach var="code" items="${deleteCode}">
		<input type="hidden" name="code" value="${code}">
	</c:forEach>
 	<input type="submit" value="削除">
</form>


<%@ include file="/header_footer/footer.jsp"%>
