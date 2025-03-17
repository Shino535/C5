<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

工事中

<%-- <c:if test="${not empty sessionScope.add-error}">
	<div id="dbConError" class="notification error">${sessionScope.add-error}</div>
	<c:remove var="add-error" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.add-success}">
	<div id="dbConError" class="notification success">${sessionScope.add-success}</div>
	<c:remove var="add-success" scope="session"/>
</c:if>

<c:choose>
	<c:when test="result">
		
	</c:when>
	<c:otherwise>
		<form action="Add.action" method="post" class="add-cont">
			<div class="add-row">
				<div class="add-text">タイトル</div>
				<input type="text" name="title" class="add-title">
			</div>
			<div class="add-row">
				<div class="add-text">タイトル</div>
				<textarea name="blog" class="add-blog">
					<c:if test="${add-result}">
						${sessionScope.}
					</c:if>
				</textarea>
			</div>
			<input type="submit" class="button-sbmt active" value="投稿">
		</form>
	</c:otherwise>
</c:choose> --%>

<%@include file="/header_footer/footer.jsp"%>