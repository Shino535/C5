<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<hr>
<c:choose>
	<c:when test="${job != null}">
		<h4>求人情報詳細</h4>
		会社名:${job.company}<br>
		住所:${job.prefecture} ${job.address}<br>
		雇用形態:${job.employment}<br>
		職種:${job.job_type}<br>
		月給:${job.benefit}円<br>
		年間休日:${job.holiday}日<br>
		<c:if test="${job.pdf_path != null}">
			求人詳細（PDF）:<a href="${job.pdf_path}">PDFをダウンロード</a><br>
		</c:if>
		
		<hr>
		
		<c:if test="${sessionScope.user.role=='user'}">
			<form action="/C5/user/JobExperienceRegister.action" method="post">
				<input type="hidden" name="job_code" value="${param.code}">
				<input type="hidden" name="user_code" value="${sessionScope.user.code}">
 				
				就活体験談内容<br>
				<input type="text" name="comment" value="${code}">
				<input type="submit" value="登録">
			</form>
			<c:if test="${not empty error}">
				${error}
			</c:if>
			<hr>
		</c:if>
		
		<h4>他の体験談</h4>
		<c:choose>
			<c:when test="${not empty jobExperience}">
				<c:forEach var="experience" items="${jobExperience}">
					ユーザーネーム: ${experience.user_name}<br>
					コメント: <c:out value="${experience.comment}"/> | 投稿日: ${experience.date}<br>
					<br>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>体験談はまだ投稿されていません。</p>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="field">
			<span>該当する求人情報が見つかりませんでした。</span>
		</div>
	</c:otherwise>
</c:choose>
<hr>

<%@include file="/header_footer/footer.jsp"%>
