<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty sessionScope.roleerror}">
	<div class="notification error">${sessionScope.roleerror}</div>
	<c:remove var="roleerror" scope="session"/>
</c:if>

<div class="login-container">
	<div class="login-title">ログイン</div>
	<c:if test="${not empty error}">
		${error}
	</c:if>
	<form action="Login.action" method="post">
		<div class="login-form">
			<div class="login-id login">
				<div class="login-subtitle">ID</div>
				<input type="text" name="id" id="id" value="${id}">
			</div>
			<div class="login-pass login">
				<div class="login-subtitle">PASS</div>
				<input type="password" id="pass" name="pass" value="${pass}">
				<span class="material-symbols-rounded toggle-password icon" onclick="togglePassword()">
				visibility
				</span>
			</div>
			<div class="login-submit login">
				<input type="submit" id="submitbtn" value="確定" class="button-sbmt" disabled>
			</div>
		</div>
	</form>
</div>

<%@include file="/header_footer/footer.jsp"%>
