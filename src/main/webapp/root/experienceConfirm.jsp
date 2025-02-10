<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp"%>

<div class="experiencedelete-confirm">
	<h2>削除確認</h2>
	<div class="experiencedelete-subtitle">
		以下の体験談を削除しますか？
	</div>
	
	<c:if test="${not empty deleteCode}">
		<c:forEach var="code" items="${deleteCode}">
			<sql:query var="experience" dataSource="${dataSource}">
				SELECT job_name,user_name,comment,date FROM jobexperience WHERE code=?;
				<sql:param value="${code}"/>
			</sql:query>
			<div class="experiencedelete-row">
				<div class="experience-row1">
					<span class="experience-company">
						<c:out value="${experience.rows[0].job_name}"/>
					</span>
					${experience.rows[0].date}
					<span class="experience-name">
						<c:out value="${experience.rows[0].user_name}"/>
					</span>
				</div>
				<span class="experience-comment">
					<c:out value="${experience.rows[0].comment}"/>
				</span>
			</div>
		</c:forEach>
	</c:if>
	
	<div class="ex-del-button">
		<form action="ExperienceDelete.action" method="post">
			<c:forEach var="code" items="${deleteCode}">
				<input type="hidden" name="code" value="${code}">
			</c:forEach>
		 	<input type="submit" value="削除" class="button-sbmt active">
		</form>
		<button onclick="history.back()" class="button-s">戻る</button>
	</div>
</div>

<%@ include file="/header_footer/footer.jsp"%>
