<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty sessionScope.dbConError}">
	<div id="dbConError" class="notification error">${sessionScope.dbConError}</div>
	<c:remove var="dbConError" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.logout}">
	<div id="logoutMsg" class="notification success">${sessionScope.logout}</div>
	<c:remove var="logout" scope="session"/>
</c:if>

<c:if test="${sessionScope.result}">
	<div class="login-message">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">ログイン成功、ようこそ<c:out value="${sessionScope.user.name}"/>さん</c:when>
			<c:when test="${not empty sessionScope.root}">ログイン成功、ようこそ<c:out value="${sessionScope.root.name}"/>さん</c:when>
			<c:otherwise>ログインに失敗しています</c:otherwise>
		</c:choose>
	</div>
	<c:remove var="result" scope="session"/>
</c:if>

<div class="search-container">
	<img src="img/背景-空-2.jpg" alt="背景" class="search-img">
	<div class="search-title">さあ、始めよう</div>
		<div class="search-form">
			<form action="/C5/Search.action" method="get">
				<div class="column1 column">
					<div class="search-subtitle">会社名</div>
					<input type="text" name="company" value="${searchList.company}">
				</div>
				<div class="column2 column">
					<div class="search-subtitle">住所</div>
					<select name="prefecture">
						<option value="">選択してください</option>
					</select>
					<input type="text" name="address" value="${searchList.address}">
				</div>
				<div class="column3 column">
					<div class="search-subtitle">職種</div>
					<input type="text" name="job_type" value="${searchList.jobType}">
					<div class="search-subtitle">月収</div>
					<select name="benefit">
						<option value="">選択してください</option>
					</select>
				</div>
				<div class="column4 column">
					<div class="search-subtitle">年間休日</div>
					<select name="holiday">
						<option value="">選択してください</option>
					</select>
					<div class="search-subtitle">雇用形態</div>
					<select name="employment">
						<option value="">選択してください</option>
					</select>
				</div>
				<input type="hidden" value="1" name="page">
				<div class="search-button">
					<button type="submit" class="button-s">検索</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="/C5/js/notification.js"></script>


<%@include file="/header_footer/footer.jsp"%>
