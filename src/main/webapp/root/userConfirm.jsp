<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header_footer/header.jsp"%>

<div class="userdel-container userdel-con">
	<h2>削除確認</h2>
	<div class="experiencedelete-subtitle">
		以下のユーザーを削除しますか？
	</div>
	
	<c:forEach var="name" items="${deleteName}">
		<div class="userdel-row">
			<p>${name}</p>
		</div>
	</c:forEach>
	
	<div class="ex-del-button">
		<form action="UserDelete.action" method="post">
			<c:forEach var="code" items="${deleteCode}">
				<input type="hidden" name="code" value="${code}">
			</c:forEach>
		 	<input type="submit" class="button-sbmt active" value="削除">
		</form>
		<button onclick="history.back()" class="button-s">戻る</button>
	</div>
</div>

<%@ include file="/header_footer/footer.jsp"%>
