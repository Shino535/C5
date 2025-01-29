<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp" %>

<h2>体験談一覧</h2>

<c:if test="${not empty error}">
    <c:out value="${error}"/>
</c:if>

<form action="ExperienceConfirm.action" method="post">
	<c:forEach var="experience" items="${experienceList}">
		<p>
			<input type="checkbox" name="code" value="${experience.code}">
			会社名[${experience.job_name}] | 
			投稿者[${experience.user_name}] | 
			コメント[${experience.comment}]
		</p>
	</c:forEach>
	<button type="submit">削除</button>
</form>

<%@ include file="/header_footer/footer.jsp" %>