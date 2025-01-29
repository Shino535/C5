<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<h1>求人検索結果</h1>

<c:forEach var="job" items="${joblist}">
	<p><a href="Job.action?code=${job.code}">詳細</a>
	${job.company}|所在地:${job.prefecture} ${job.address}|職種:${job.job_type}|月収:${job.benefit}|年間休日:${job.holiday}日</p>
</c:forEach>

<!-- ページネーションの処理と描画 -->
<div class="page-container">
	<!-- 左矢印 -->
	<c:choose>
		<c:when test="${thisPage>1}">
			<a href="?company=${searchList.company}&prefecture=${searchList.prefecture}&address=${searchList.address}
			&job_type=${searchList.jobType}&benefit=${searchList.benefit}&holiday=${searchList.holiday}&employment=${searchList.employment}
			&page=${thisPage-1}">
			<span class="material-symbols-rounded">keyboard_arrow_left</span>
			</a>
		</c:when>
		<c:otherwise>
			<span class="material-symbols-rounded disabled">keyboard_arrow_left</span>
		</c:otherwise>
	</c:choose>
	
	<!-- ページ番号 -->
	<c:forEach var="i" begin="1" end="${totalPage}">
		<c:choose>
			<c:when test="${i==thisPage}">
				${i}
			</c:when>
			<c:otherwise>
				<a href="?company=${searchList.company}&prefecture=${searchList.prefecture}&address=${searchList.address}
				&job_type=${searchList.jobType}&benefit=${searchList.benefit}&holiday=${searchList.holiday}&employment=${searchList.employment}
				&page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<!-- 右矢印 -->
	<c:choose>
		<c:when test="${thisPage<totalPage}">
			<a href="?company=${searchList.company}&prefecture=${searchList.prefecture}&address=${searchList.address}
			&job_type=${searchList.jobType}&benefit=${searchList.benefit}&holiday=${searchList.holiday}&employment=${searchList.employment}
			&page=${thisPage+1}">
			<span class="material-symbols-rounded">keyboard_arrow_right</span>
			</a>
		</c:when>
		<c:otherwise>
			<span class="material-symbols-rounded disabled">keyboard_arrow_right</span>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/header_footer/footer.jsp"%>
