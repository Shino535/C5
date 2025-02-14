<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:choose>
	<c:when test="${result}">
		<div class="success-container">
			管理者:<c:out value="${name}"/> さんの登録が成功しました
			<a href="/C5/" class="button-fit">トップページ</a>
		</div>
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
						<span class="material-symbols-rounded toggle-password icon" onclick="togglePassword()">
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

<%@include file="/header_footer/footer.jsp"%>
