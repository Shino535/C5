<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp"%>


<h2>削除確認</h2>
<p>以下の体験談を削除しますか？</p>

<c:forEach var="code" items="${deleteCode}">
	<p>${code}</p>
</c:forEach>

<form action="ExperienceDelete.action" method="post">
	<c:forEach var="code" items="${deleteCode}">
		<input type="hidden" name="code" value="${code}">
	</c:forEach>
 	<input type="submit" value="削除">
</form>


<%@ include file="/header_footer/footer.jsp"%>
