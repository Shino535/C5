<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty sessionScope.dbConError}">
    <div class="error">${sessionScope.dbConError}</div>
    <c:remove var="dbConError" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.logout}">
	<c:out value="${sessionScope.logout}"/>
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
	<img src="img/背景-1.jpg" alt="背景" width="100%" height="450" class="search-img">
	<div class="search-title">さあ、始めよう</div>
	<form action="/C5/Search.action" method="get" class="search-form">
		<lavel for="company">
			会社名<input type="text" name="company" value="${searchList.company}">
		</label>
		<div class="column2 column">
			住所
			<select name="prefecture">
				<option value="">選択してください</option>
			</select>
			<input type="text" name="address" value="${searchList.address}">
		</div>
		<div class="column3 column">
			職種:<input type="text" name="job_type" value="${searchList.jobType}">
			月収:
			<select name="benefit">
				<option value="">選択してください</option>
			</select>
		</div>
		<div class="column4 column">
			</select><br>
			年間休日:
			<select name="holiday">
				<option value="">選択してください</option>
			</select><br>
			雇用形態:
			<select name="employment">
				<option value="">選択してください</option>
			</select>
		</div>
		<input type="hidden" value="1" name="page">
		<div class="button-container">
			<button type="submit">検索</button>
		</div>
	</form>
</div>

<%@include file="/header_footer/footer.jsp"%>
