<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<div class="jobsearch-container">
	<form action="/C5/Search.action" method="get">
		<div class="jobsearch-form">
			<div class="column1 column">
				<div class="search-subtitle">会社名</div>
				<input type="text" name="company" value="${searchList.company}">
			</div>
			<div class="column2 column">
				<div class="search-subtitle">住所</div>
				<select name="prefecture">
					<option value="">選択してください</option>
					<option value="北海道" ${searchList.prefecture == '北海道' ? 'selected' : ''}>北海道</option>
					<option value="青森県" ${searchList.prefecture == '青森県' ? 'selected' : ''}>青森県</option>
					<option value="岩手県" ${searchList.prefecture == '岩手県' ? 'selected' : ''}>岩手県</option>
					<option value="宮城県" ${searchList.prefecture == '宮城県' ? 'selected' : ''}>宮城県</option>
					<option value="秋田県" ${searchList.prefecture == '秋田県' ? 'selected' : ''}>秋田県</option>
					<option value="山形県" ${searchList.prefecture == '山形県' ? 'selected' : ''}>山形県</option>
					<option value="福島県" ${searchList.prefecture == '福島県' ? 'selected' : ''}>福島県</option>
					<option value="茨城県" ${searchList.prefecture == '茨城県' ? 'selected' : ''}>茨城県</option>
					<option value="栃木県" ${searchList.prefecture == '栃木県' ? 'selected' : ''}>栃木県</option>
					<option value="群馬県" ${searchList.prefecture == '群馬県' ? 'selected' : ''}>群馬県</option>
					<option value="埼玉県" ${searchList.prefecture == '埼玉県' ? 'selected' : ''}>埼玉県</option>
					<option value="千葉県" ${searchList.prefecture == '千葉県' ? 'selected' : ''}>千葉県</option>
					<option value="東京都" ${searchList.prefecture == '東京都' ? 'selected' : ''}>東京都</option>
					<option value="神奈川県" ${searchList.prefecture == '神奈川県' ? 'selected' : ''}>神奈川県</option>
					<option value="新潟県" ${searchList.prefecture == '新潟県' ? 'selected' : ''}>新潟県</option>
					<option value="富山県" ${searchList.prefecture == '富山県' ? 'selected' : ''}>富山県</option>
					<option value="石川県" ${searchList.prefecture == '石川県' ? 'selected' : ''}>石川県</option>
					<option value="福井県" ${searchList.prefecture == '福井県' ? 'selected' : ''}>福井県</option>
					<option value="山梨県" ${searchList.prefecture == '山梨県' ? 'selected' : ''}>山梨県</option>
					<option value="長野県" ${searchList.prefecture == '長野県' ? 'selected' : ''}>長野県</option>
					<option value="岐阜県" ${searchList.prefecture == '岐阜県' ? 'selected' : ''}>岐阜県</option>
					<option value="静岡県" ${searchList.prefecture == '静岡県' ? 'selected' : ''}>静岡県</option>
					<option value="愛知県" ${searchList.prefecture == '愛知県' ? 'selected' : ''}>愛知県</option>
					<option value="三重県" ${searchList.prefecture == '三重県' ? 'selected' : ''}>三重県</option>
					<option value="滋賀県" ${searchList.prefecture == '滋賀県' ? 'selected' : ''}>滋賀県</option>
					<option value="京都府" ${searchList.prefecture == '京都府' ? 'selected' : ''}>京都府</option>
					<option value="大阪府" ${searchList.prefecture == '大阪府' ? 'selected' : ''}>大阪府</option>
					<option value="兵庫県" ${searchList.prefecture == '兵庫県' ? 'selected' : ''}>兵庫県</option>
					<option value="奈良県" ${searchList.prefecture == '奈良県' ? 'selected' : ''}>奈良県</option>
					<option value="和歌山県" ${searchList.prefecture == '和歌山県' ? 'selected' : ''}>和歌山県</option>
					<option value="鳥取県" ${searchList.prefecture == '鳥取県' ? 'selected' : ''}>鳥取県</option>
					<option value="島根県" ${searchList.prefecture == '島根県' ? 'selected' : ''}>島根県</option>
					<option value="岡山県" ${searchList.prefecture == '岡山県' ? 'selected' : ''}>岡山県</option>
					<option value="広島県" ${searchList.prefecture == '広島県' ? 'selected' : ''}>広島県</option>
					<option value="山口県" ${searchList.prefecture == '山口県' ? 'selected' : ''}>山口県</option>
					<option value="徳島県" ${searchList.prefecture == '徳島県' ? 'selected' : ''}>徳島県</option>
					<option value="香川県" ${searchList.prefecture == '香川県' ? 'selected' : ''}>香川県</option>
					<option value="愛媛県" ${searchList.prefecture == '愛媛県' ? 'selected' : ''}>愛媛県</option>
					<option value="高知県" ${searchList.prefecture == '高知県' ? 'selected' : ''}>高知県</option>
					<option value="福岡県" ${searchList.prefecture == '福岡県' ? 'selected' : ''}>福岡県</option>
					<option value="佐賀県" ${searchList.prefecture == '佐賀県' ? 'selected' : ''}>佐賀県</option>
					<option value="長崎県" ${searchList.prefecture == '長崎県' ? 'selected' : ''}>長崎県</option>
					<option value="熊本県" ${searchList.prefecture == '熊本県' ? 'selected' : ''}>熊本県</option>
					<option value="大分県" ${searchList.prefecture == '大分県' ? 'selected' : ''}>大分県</option>
					<option value="宮崎県" ${searchList.prefecture == '宮崎県' ? 'selected' : ''}>宮崎県</option>
					<option value="鹿児島県" ${searchList.prefecture == '鹿児島県' ? 'selected' : ''}>鹿児島県</option>
					<option value="沖縄県" ${searchList.prefecture == '沖縄県' ? 'selected' : ''}>沖縄県</option>
				</select>
				<input type="text" name="address" value="${searchList.address}">
			</div>
			<div class="column3 column">
				<div class="search-jobtype">
					<div class="search-subtitle">職種</div>
					<input type="text" name="job_type" value="${searchList.jobType}">
				</div>
				<div class="search-benefit">
					<div class="search-subtitle">月収</div>
					<select name="benefit">
						<option value="">選択してください</option>
						<option value="150000" ${searchList.benefit=='150000'?'selected':''}>15万円以上</option>
						<option value="200000" ${searchList.benefit=='200000'?'selected':''}>20万円以上</option>
						<option value="250000" ${searchList.benefit=='250000'?'selected':''}>25万円以上</option>
						<option value="300000" ${searchList.benefit=='300000'?'selected':''}>30万円以上</option>
					</select>
				</div>
			</div>
			<div class="column4 column">
				<div class="search-holiday">
					<div class="search-subtitle">年間休日</div>
					<select name="holiday">
						<option value="">選択してください</option>
						<option value="100" ${searchList.holiday == '100' ? 'selected' : ''}>100日以上</option>
						<option value="110" ${searchList.holiday == '110' ? 'selected' : ''}>110日以上</option>
						<option value="120" ${searchList.holiday == '120' ? 'selected' : ''}>120日以上</option>
						<option value="130" ${searchList.holiday == '130' ? 'selected' : ''}>130日以上</option>
					</select>
				</div>
				<div class="search-employment">
					<div class="search-subtitle">雇用形態</div>
					<select name="employment">
						<option value="">選択してください</option>
						<option value="正社員" ${searchList.employment == '正社員' ? 'selected' : ''}>正社員</option>
						<option value="派遣社員" ${searchList.employment == '派遣社員' ? 'selected' : ''}>派遣社員</option>
						<option value="契約社員" ${searchList.employment == '契約社員' ? 'selected' : ''}>契約社員</option>
						<option value="AP" ${searchList.employment == 'AP' ? 'selected' : ''}>アルバイト・パート</option>
						<option value="業務委託" ${searchList.employment == '業務委託' ? 'selected' : ''}>業務委託</option>
					</select>
				</div>
			</div>
			<input type="hidden" value="1" name="page">
			<div class="search-button">
				<button type="submit" class="button-s">検索</button>
			</div>
		</div>
	</form>
	<div class="jobsearch-title">求人検索結果</div>
	
	<!-- ★ 削除済みの求人をクリックした場合のエラーメッセージ -->
	<c:if test="${not empty sessionScope.jobNotFound}">
		<div class="error-message" style="color: red; font-weight: bold; margin-bottom: 10px;">
			<c:out value="${sessionScope.jobNotFound}"/>
		</div>
		<c:remove var="jobNotFound" scope="session"/>
	</c:if>
	<!-- ★ ここまで -->

	<c:if test="${empty joblist}">
		<div class="jobsearch-none">検索結果0件</div>
	</c:if>
	<div class="jobsearch-list">
		<c:forEach var="job" items="${joblist}">
			<div class="jobsearch-row">
				<div class="jobsearch-company jobsearch-text">${job.company}</div>
				<div class="jobsearch-jobtype jobsearch-text">${job.job_type}</div>
				<div class="jobsearch-row3">
					<div class="jobsearch-prefecture jobsearch-text">${job.prefecture}</div>
					<div class="jobsearch-address jobsearch-text">${job.address}</div>
				</div>
				<div class="jobsearch-row4">
					<div class="jobsearch-benefit jobsearch-text">
						<span class="material-symbols-rounded icon jobsearch-icon">
						universal_currency
						</span>
						月収<fmt:formatNumber value="${job.benefit}" pattern="#,###"/>円
					</div>
					<div class="jobsearch-holiday jobsearch-text">
						<span class="material-symbols-rounded icon jobsearch-icon">
						calendar_month
						</span>
						<c:out value="年間休日${job.holiday}日"/>
					</div>
				</div>
				<a href="Job.action?code=${job.code}">詳細</a>
			</div>
		</c:forEach>
	</div>
	<!-- ページネーションの処理と描画 -->
	<div class="page-container">
		<c:if test="${totalPage>0}">
			<!-- 左矢印 -->
			<c:choose>
				<c:when test="${thisPage>1}">
					<a href="?company=${searchList.company}&prefecture=${searchList.prefecture}&address=${searchList.address}
					&job_type=${searchList.jobType}&benefit=${searchList.benefit}&holiday=${searchList.holiday}&employment=${searchList.employment}
					&page=${thisPage-1}">
					<span class="material-symbols-rounded arrow">keyboard_arrow_left</span>
					</a>
				</c:when>
				<c:otherwise>
					<span class="material-symbols-rounded disabled　arr">keyboard_arrow_left</span>
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
					<span class="material-symbols-rounded arrow">keyboard_arrow_right</span>
					</a>
				</c:when>
				<c:otherwise>
					<span class="material-symbols-rounded disabled arrow">keyboard_arrow_right</span>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</div>

<%@include file="/header_footer/footer.jsp"%>
