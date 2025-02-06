<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty sessionScope.dbConError}">
	<div id="dbConError" class="notification error">${sessionScope.dbConError}</div>
	<c:remove var="dbConError" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.logout}">
	<div id="logoutMsg" class="notification success">${sessionScope.logout}</div>
	<c:remove var="logout" scope="session"/>
</c:if>

<c:if test="${sessionScope.result}">
	<div class="login-message">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<div class="notification success">
					ログイン成功、ようこそ<c:out value="${sessionScope.user.name}"/>さん
				</div>
			</c:when>
			<c:when test="${not empty sessionScope.root}">
				<div class="notification success">
					ログイン成功、ようこそ<c:out value="${sessionScope.root.name}"/>さん
				</div>
			</c:when>
			<c:otherwise>
				<div class="notification success">
					ログインに失敗しています
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<c:remove var="result" scope="session"/>
</c:if>

<div class="search-container">
	<img src="img/背景-空-2.jpg" alt="背景" class="search-img">
	<div class="search-title">さあ、始めよう</div>
		<div class="search-form">
			<form action="/C5/Search.action" method="get">
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
					<div class="search-subtitle">職種</div>
					<input type="text" name="job_type" value="${searchList.jobType}">
					<div class="search-subtitle">月収</div>
					<select name="benefit">
						<option value="">選択してください</option>
						<option value="150000" ${searchList.benefit=='150000'?'selected':''}>15万円以上</option>
						<option value="200000" ${searchList.benefit=='200000'?'selected':''}>20万円以上</option>
						<option value="250000" ${searchList.benefit=='250000'?'selected':''}>25万円以上</option>
						<option value="300000" ${searchList.benefit=='300000'?'selected':''}>30万円以上</option>
					</select>
				</div>
				<div class="column4 column">
					<div class="search-subtitle">年間休日</div>
					<select name="holiday">
						<option value="">選択してください</option>
						<option value="100" ${searchList.holiday == '100' ? 'selected' : ''}>100日以上</option>
						<option value="110" ${searchList.holiday == '110' ? 'selected' : ''}>110日以上</option>
						<option value="120" ${searchList.holiday == '120' ? 'selected' : ''}>120日以上</option>
						<option value="130" ${searchList.holiday == '130' ? 'selected' : ''}>130日以上</option>
					</select>
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
				<input type="hidden" value="1" name="page">
				<div class="search-button">
					<button type="submit" class="button-s">検索</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="/C5/js/notification.js"></script>


<%@include file="/header_footer/footer.jsp"%>
