<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:choose>
	<c:when test="${result}">
		<p>登録が成功しました。</p>
		<p>ようこそ:<c:out value="${user.name}"/>さん</p>
	</c:when>
	<c:otherwise>
		<div class="register-container">
			<div class="register-title">会員登録</div>
			<c:if test="${not empty error}">
				<div class="register-error">${error}</div>
			</c:if>
			<form action="Register.action" method="post">
				<div class="register-form">
					<div class="register-id register">
						<div class="register-subtitle">ID</div>
						<input type="text" id="userid" name="id" value="${id}">
					</div>
					<div class="register-name register register-subtitle">
						<div class="register-subtitle">NAME</div>
						<input type="text" id="name" name="name" value="${name}">
					</div>
					<div class="register-pass register register-subtitle">
						<div class="register-subtitle">PASS</div>
						<input type="password" id="pass" name="pass" value="${pass}">
						<span class="material-symbols-rounded toggle-password" onclick="togglePassword()">
						visibility
						</span>
					</div>
					<div class="register-submit register">
						<input type="submit" id="submitBtn" value="登録" class="button-sbmt" disabled>
					</div>
				</div>
			</form>
		</div>
	</c:otherwise>
</c:choose>

<script src="/C5/js/register.js"></script>
<script src="/C5/js/togglepass.js"></script>

<%@include file="/header_footer/footer.jsp"%>