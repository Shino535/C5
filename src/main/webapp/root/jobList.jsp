<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<h1>求人情報一覧</h1>

<form action="JobList.action" method="post">
	<input type="text" name="company"> <input type="submit"
		value="会社名で絞り込み">
</form>
<hr>

<table style="border-collapse: separate; border-spacing: 10px;">
	<c:forEach var="p" items="${list}">
		<tr>
			<form action="UpdateInput.action" method="post">
				<td><input type="hidden" name="code" value="${p.code}" />会社名：${p.company}所在地：${p.address}</td>
				<td><input type="submit" value="更新"></td>
			</form>
			<form action="JobDeleteSelect.action" method="post">
				<td><input type="hidden" name="code" value="${p.code}" /></td>
				<td><input type="submit" value="削除"></td>
			</form>
		</tr>
	</c:forEach>
</table>

<%@include file="/header_footer/footer.jsp"%>