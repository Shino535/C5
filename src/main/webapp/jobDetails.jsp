<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<div class="jobdetail-container">
	<c:choose>
		<c:when test="${job != null}">
			<div class="jobdetail-info">
				<div class="jobdetail-company">${job.company}</div>
				<div class="jobdetail-row2">
					<div class="jobdetail-infotitle">勤務地</div>
					<div class="jobdetail-address">
						<span class="material-symbols-rounded icon">
						map
						</span>
						${job.prefecture}${job.address}
					</div>
				</div>
				<div class="jobdetail-row4">
					<div class="jobdetail-infotitle">職種</div>
					<div class="jobdetail-jobtype">
						<span class="material-symbols-rounded icon">
						person
						</span>
						${job.job_type}
					</div>
				</div>
				<div class="jobdetail-row">
					<div class="jobdetail-row3">
						<div class="jobdetail-infotitle">雇用形態</div>
						<div class="jobdetail-employment">
							<span class="material-symbols-rounded icon">
							work
							</span>
							${job.employment}
						</div>
					</div>
					<div class="jobdetail-row5">
					<div class="jobdetail-infotitle">月給</div>
						<div class="jobdetail-benefit">
							<span class="material-symbols-rounded icon">
							universal_currency
							</span>
							${job.benefit}円
						</div>
					</div>
					<div class="jobdetail-row6">
						<div class="jobdetail-infotitle">年間休日</div>
						<div class="jobdetail-holiday">
							<span class="material-symbols-rounded icon">
							calendar_month
							</span>
							${job.holiday}日
						</div>
					</div>
				</div>
				<c:if test="${job.pdf_path != null}">
					<a href="${job.pdf_path}" class="button-s jobdetail-pdf">求人票はこちらから</a>
				</c:if>
			</div>
			<c:if test="${sessionScope.user.role=='user'}">
				<div class="jobdetail-experience-add">
					<div class="jobdetail-subtitle">
						体験談コメント
					</div>
					<form action="/C5/user/JobExperienceRegister.action" method="post">
						<div class="jobdetail-experience-form">
							<input type="hidden" name="job_code" value="${param.code}">
							<input type="hidden" name="user_code" value="${sessionScope.user.code}">
							<textarea name="comment" value="" placeholder="ありがとうございました" class="jobdetail-experience-input"></textarea>
							<button type="submit" class="button jobdetail-experience-button">
								<span class="material-symbols-rounded icon">
								add_circle
								</span>
							</button>
						</div>
					</form>
					<c:if test="${not empty error}">
						${error}
					</c:if>
				</div>
			</c:if>
			<div class="jobdetail-experience">
				<div class="jobdetail-subtitle">体験談</div>
				<c:choose>
					<c:when test="${not empty jobExperience}">
						<c:forEach var="experience" items="${jobExperience}">
							<div class="jobdetail-experience-row">
								<div class="jobdetail-experience-column">
									<div class="jobdetail-experience-name">
										<c:out value="${experience.user_name}"/> さん
									</div>
									<div class="jobdetail-experience-date">
										<c:out value="投稿日 ${experience.date}"/>
									</div>
								</div>
								<div class="jobdetail-experience-comment">
									<c:out value="${experience.comment}"/>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="jobdetail-subtitle">
							体験談はまだ投稿されていません。
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
			<div class="field">
				<span>該当する求人情報が見つかりませんでした。</span>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/header_footer/footer.jsp"%>
