<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<form action="AccountDeleteRootIn.action">
	<input type="text" name="keyword">
	<input type="submit" value="検索">
	<input type="hidden" name="page" value="1">
</form>
<c:choose>
	<c:when test="${empty rootList}">
		<p>該当者がありません</p>
	</c:when>
	<c:otherwise>
		<c:if test="${param.confirm!=null}">
			<c:choose>
				<c:when test="${param.confirm=='yes'}">
					<c:forEach var="root" items="${rootList}">
						<c:if test="${not empty param.selectId&&param.selectId.contains(root.id)}">
							<p>削除対象:ID:${root.id},名前:${root.name}</p>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>本当に削除しますか?</p>
					<form action="AccountDeleteRootOut.action" method="post">
						<input type="hidden" name="confirm" value="yes">
						<input type="submit" value="yes">
					</form>
					<form action="AccountDeleteRootIn.action" method="post">
						<input type="hidden" name="confirm" value="no">
						<input type="submit" value="no">
					</form>
				</c:otherwise>
			</c:choose>
		</c:if>
		<form action="AccountDeleteRootIn.action">
			<c:forEach var="root" items="${rootList}">
				<p>
					ID:${root.id},名前${root.name}
					<input type="checkbox" name="selectId" value="${root.id}">
				</p>
			</c:forEach>
			<input type="hidden" name="page" value="1">
			<input type="submit" value="削除">
		</form>
	</c:otherwise>
</c:choose>

<c:if test="${totalPage>1}">
	<c:if test="${page>1}">
		<a href="AccountDeleteRootIn.action?page=${page-1}&keyword=${param.keyword}">前へ</a>
	</c:if>
	<c:forEach var="i" begin="1" end="${totalPage}">
		<c:choose>
			<c:when test="${i==page}">
				<span>${i}</span>
			</c:when>
			<c:otherwise>
				<a href="AccountDeleteRootIn.action?page=${i}&keyword=${param.keyword}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${page<totalPage}">
		<a href="AccountDeleteRootIn.action?page=${page+1}&keyword=${param.keyword}">次へ</a>
	</c:if>
</c:if>
<%@include file="/header_footer/footer.jsp"%>
