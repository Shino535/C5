<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

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
			会社名：${p.company}&nbsp;&nbsp;所在地：${p.address}
			<form action="UpdateInput.action" method="post">
				<input type="hidden" name="code" value="${p.code}"/>
				<input type="submit" value="更新">
			</form>
			<form action="JobDeleteSelect.action" method="post">
				<input type="hidden" name="code" value="${p.code}"/>
				<input type="submit" value="削除">
			</form>
		</div>
	</c:forEach>
</div>

<%@include file="/header_footer/footer.jsp"%>