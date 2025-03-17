<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp"%>

<div class="userdel-container">
	<h2>利用者一覧</h2>
	<c:if test="${not empty error}">
		<c:out value="${error}" />
	</c:if>

	<form action="UserDeleteConfirm.action" method="post" class="userdel-list">
		<c:forEach var="user" items="${UserList}">
			<label>
				<div class="userdel-row">
					<input type="checkbox" name="code" value="${user.code}">
					<input type="hidden" name="name_${user.code}" value="${user.name}">
					<div class="userdel-name">利用者名:<c:out value="${user.name}"/></div>
				</div>
			</label>
		</c:forEach>
		<button type="submit" class="button-sbmt active">削除</button>
	</form>
</div>

<%@ include file="/header_footer/footer.jsp"%>