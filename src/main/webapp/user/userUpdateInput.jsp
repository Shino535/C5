<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<!-- エラーメッセージの表示 -->
<c:if test="${not empty errorMessages}">
	<ul style="color: red;">
		<c:forEach var="errorMessage" items="${errorMessages}">
			<li>
				<c:out value="${errorMessage}"/>
			</li>
		</c:forEach>
	</ul>
</c:if>

<h3>更新内容入力</h3>

<form action="UserUpdate.action" method="post">
	<p>
		ID<input type="text" name="id" value="${id}">
	</p>
	<p>
		NAME<input type="text" name="name" value="${name}">
	</p>
	<p>
		PASS<input type="password" name="pass" value="${pass}">
	</p>
	<input type="hidden" name="code" value="${UserBean.code}">
	<p>
		<input type="submit" value="更新">
	</p>
</form>

<%@include file="/header_footer/footer.jsp"%>
