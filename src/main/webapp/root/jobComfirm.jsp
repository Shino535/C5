<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header_footer/header.jsp"%>

<div class="jobdelete">
	<h2>削除確認</h2>
	<div class="jobdelete-subtitle">以下の求人を削除しますか？</div>
	<div class="jobdelete-info">
		<div class="jobdelete-text">会社名：<c:out value="${jobBean.company}"/></div>
		<div class="jobdelete-text">所在地：<c:out value="${jobBean.address}"/></div>
	</div>
	<div class="jobdelete-button">
		<form action="JobDelete.action" method="post">
			<input type="hidden" name="code" value="${jobBean.code}">
		 	<input type="submit" value="削除" class="button-sbmt active">
		</form>
		<button onclick="window.history.back()" class="button-s">戻る</button>
	</div>
</div>

<%@ include file="/header_footer/footer.jsp"%>