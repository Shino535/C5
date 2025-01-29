<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/header_footer/header.jsp"%>


<h2>削除確認</h2>
<p>以下の求人を削除しますか？</p>

会社：${jobBean.company} 所在地：${jobBean.address}

<form action="JobDelete.action" method="post">
		<input type="hidden" name="code" value="${jobBean.code}">
 	<input type="submit" value="削除">
</form>

<%@ include file="/header_footer/footer.jsp"%>