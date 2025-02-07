<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:choose>
	<c:when test="${not empty sessionScope.jobdelete_t}">
		<div class="notification success">${sessionScope.jobdelete_t}</div>
		<c:remove var="jobdelete_t" scope="session"/>
	</c:when>
	<c:when test="${not empty sessionScope.jobdelete_f}">
		<div class="notification error">${sessionScope.jobdelete_f}</div>
		<c:remove var="jobdelete_f" scope="session"/>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<div class="joblist-container">
	<div class="joblist-title">求人情報一覧</div>
	<form action="JobList.action" method="post">
		<div class="joblist-search">
			<input type="text" name="company">
			<input type="submit" value="会社名で絞り込み" class="button-s joblist-button">
		</div>
	</form>
	<c:forEach var="p" items="${list}">
		<div class="joblist-row">
			<div class="joblist-row-text">
				<div class="joblist-column">
					会社名 <c:out value="${p.company}"/>
				</div>
				<div class="joblist-column">
					所在地 <c:out value="${p.address}"/>
				</div>
			</div>
			<div class="joblist-row-icon">
				<form action="UpdateInput.action" method="post">
					<input type="hidden" name="code" value="${p.code}"/>
					<button type="submit" class="button">
						<span class="material-symbols-rounded icon">
						cached
						</span>
					</button>
				</form>
				<form action="JobDeleteSelect.action" method="post">
					<input type="hidden" name="code" value="${p.code}"/>
					<button type="submit" class="button">
						<span class="material-symbols-rounded icon">
						delete
						</span>
					</button>
				</form>
			</div>
		</div>
	</c:forEach>
</div>

<%@include file="/header_footer/footer.jsp"%>