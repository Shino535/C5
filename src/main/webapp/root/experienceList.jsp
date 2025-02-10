<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp" %>

<div class="experience-container">
	<h2>体験談一覧</h2>
	<c:if test="${not empty error}">
		<h4>
			<c:out value="${error}"/>
		</h4>
	</c:if>
	
	<form action="ExperienceConfirm.action" method="post" class="experience-list">
		<c:forEach var="experience" items="${experienceList}">
			<label>
				<div class="experience-row">
					<div class="experience-check">
						<input type="checkbox" name="code" value="${experience.code}">
					</div>
					<div class="experience-info">
						<div class="experience-row-1">
							<span class="experience-company">会社名:${experience.job_name}</span>
							${experience.date}
							<span class="experience-name">投稿者:${experience.user_name}</span>
						</div>
						<div class="experience-comment">コメント:${experience.comment}</div>
					</div>
				</div>
			</label>
		</c:forEach>
		<input type="submit" value="削除" class="experience-delete">
	</form>
</div>

<%@ include file="/header_footer/footer.jsp" %>