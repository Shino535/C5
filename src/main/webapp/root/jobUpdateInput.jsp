<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>

<div class="jobupdate-container">
	<div class="jobupdate-">
		<h3>更新対象求人</h3>
		<c:out value="会社名：${jobBean.company}"/><br>
		<c:out value="所在地：${jobBean.address}"/>
	</div>
	<form action="JobUpdate.action" method="post" enctype="multipart/form-data">
		<div class="jobupdate-form">
			<h3>更新内容入力</h3>
			<c:if test="${not empty error}">
			   ${error}
			</c:if>
			<div class="column1 column">
				<div class="search-subtitle">会社名</div>
				<input type="text" name="company" value="${jobBean.company}">
			</div>
			<div class="column2 column">
				<div class="search-subtitle">住所</div>
				<select name="prefecture">
					<option value="">選択してください</option>
					<option value="北海道" ${jobBean.prefecture == '北海道' ? 'selected' : ''}>北海道</option>
					<option value="青森県" ${jobBean.prefecture == '青森県' ? 'selected' : ''}>青森県</option>
					<option value="岩手県" ${jobBean.prefecture == '岩手県' ? 'selected' : ''}>岩手県</option>
					<option value="宮城県" ${jobBean.prefecture == '宮城県' ? 'selected' : ''}>宮城県</option>
					<option value="秋田県" ${jobBean.prefecture == '秋田県' ? 'selected' : ''}>秋田県</option>
					<option value="山形県" ${jobBean.prefecture == '山形県' ? 'selected' : ''}>山形県</option>
					<option value="福島県" ${jobBean.prefecture == '福島県' ? 'selected' : ''}>福島県</option>
					<option value="茨城県" ${jobBean.prefecture == '茨城県' ? 'selected' : ''}>茨城県</option>
					<option value="栃木県" ${jobBean.prefecture == '栃木県' ? 'selected' : ''}>栃木県</option>
					<option value="群馬県" ${jobBean.prefecture == '群馬県' ? 'selected' : ''}>群馬県</option>
					<option value="埼玉県" ${jobBean.prefecture == '埼玉県' ? 'selected' : ''}>埼玉県</option>
					<option value="千葉県" ${jobBean.prefecture == '千葉県' ? 'selected' : ''}>千葉県</option>
					<option value="東京都" ${jobBean.prefecture == '東京都' ? 'selected' : ''}>東京都</option>
					<option value="神奈川県" ${jobBean.prefecture == '神奈川県' ? 'selected' : ''}>神奈川県</option>
					<option value="新潟県" ${jobBean.prefecture == '新潟県' ? 'selected' : ''}>新潟県</option>
					<option value="富山県" ${jobBean.prefecture == '富山県' ? 'selected' : ''}>富山県</option>
					<option value="石川県" ${jobBean.prefecture == '石川県' ? 'selected' : ''}>石川県</option>
					<option value="福井県" ${jobBean.prefecture == '福井県' ? 'selected' : ''}>福井県</option>
					<option value="山梨県" ${jobBean.prefecture == '山梨県' ? 'selected' : ''}>山梨県</option>
					<option value="長野県" ${jobBean.prefecture == '長野県' ? 'selected' : ''}>長野県</option>
					<option value="岐阜県" ${jobBean.prefecture == '岐阜県' ? 'selected' : ''}>岐阜県</option>
					<option value="静岡県" ${jobBean.prefecture == '静岡県' ? 'selected' : ''}>静岡県</option>
					<option value="愛知県" ${jobBean.prefecture == '愛知県' ? 'selected' : ''}>愛知県</option>
					<option value="三重県" ${jobBean.prefecture == '三重県' ? 'selected' : ''}>三重県</option>
					<option value="滋賀県" ${jobBean.prefecture == '滋賀県' ? 'selected' : ''}>滋賀県</option>
					<option value="京都府" ${jobBean.prefecture == '京都府' ? 'selected' : ''}>京都府</option>
					<option value="大阪府" ${jobBean.prefecture == '大阪府' ? 'selected' : ''}>大阪府</option>
					<option value="兵庫県" ${jobBean.prefecture == '兵庫県' ? 'selected' : ''}>兵庫県</option>
					<option value="奈良県" ${jobBean.prefecture == '奈良県' ? 'selected' : ''}>奈良県</option>
					<option value="和歌山県" ${jobBean.prefecture == '和歌山県' ? 'selected' : ''}>和歌山県</option>
					<option value="鳥取県" ${jobBean.prefecture == '鳥取県' ? 'selected' : ''}>鳥取県</option>
					<option value="島根県" ${jobBean.prefecture == '島根県' ? 'selected' : ''}>島根県</option>
					<option value="岡山県" ${jobBean.prefecture == '岡山県' ? 'selected' : ''}>岡山県</option>
					<option value="広島県" ${jobBean.prefecture == '広島県' ? 'selected' : ''}>広島県</option>
					<option value="山口県" ${jobBean.prefecture == '山口県' ? 'selected' : ''}>山口県</option>
					<option value="徳島県" ${jobBean.prefecture == '徳島県' ? 'selected' : ''}>徳島県</option>
					<option value="香川県" ${jobBean.prefecture == '香川県' ? 'selected' : ''}>香川県</option>
					<option value="愛媛県" ${jobBean.prefecture == '愛媛県' ? 'selected' : ''}>愛媛県</option>
					<option value="高知県" ${jobBean.prefecture == '高知県' ? 'selected' : ''}>高知県</option>
					<option value="福岡県" ${jobBean.prefecture == '福岡県' ? 'selected' : ''}>福岡県</option>
					<option value="佐賀県" ${jobBean.prefecture == '佐賀県' ? 'selected' : ''}>佐賀県</option>
					<option value="長崎県" ${jobBean.prefecture == '長崎県' ? 'selected' : ''}>長崎県</option>
					<option value="熊本県" ${jobBean.prefecture == '熊本県' ? 'selected' : ''}>熊本県</option>
					<option value="大分県" ${jobBean.prefecture == '大分県' ? 'selected' : ''}>大分県</option>
					<option value="宮崎県" ${jobBean.prefecture == '宮崎県' ? 'selected' : ''}>宮崎県</option>
					<option value="鹿児島県" ${jobBean.prefecture == '鹿児島県' ? 'selected' : ''}>鹿児島県</option>
					<option value="沖縄県" ${jobBean.prefecture == '沖縄県' ? 'selected' : ''}>沖縄県</option>
				</select>
				<input type="text" name="address" value="${jobBean.address}">
			</div>
			<div class="column3 column">
				<div class="search-jobtype">
					<div class="search-subtitle">職種</div>
					<input type="text" name="job_type" value="${jobBean.job_type}">
				</div>
				<div class="search-benefit">
					<div class="search-subtitle">月収</div>
					<input type="text" name="benefit" value="${jobBean.benefit}">
				</div>
			</div>
			<div class="column4 column">
				<div class="search-holiday">
					<div class="search-subtitle">年間休日</div>
					<input type="text" name="holiday" value="${jobBean.holiday}">
				</div>
				<div class="search-employment">
					<div class="search-subtitle">雇用形態</div>
					<select name="employment">
						<option value="">選択してください</option>
						<option value="正社員" ${jobBean.employment == '正社員' ? 'selected' : ''}>正社員</option>
						<option value="派遣社員" ${jobBean.employment == '派遣社員' ? 'selected' : ''}>派遣社員</option>
						<option value="契約社員" ${jobBean.employment == '契約社員' ? 'selected' : ''}>契約社員</option>
						<option value="AP" ${jobBean.employment == 'AP' ? 'selected' : ''}>アルバイト・パート</option>
						<option value="業務委託" ${jobBean.employment == '業務委託' ? 'selected' : ''}>業務委託</option>
					</select>
				</div>
			</div>
			<input type="hidden" name="code" value="${jobBean.code}">
			<div class="jobupdate-pdf">
				<div class="search-subtitle">求人票PDF</div>
				<input type="file" name="pdf">
			</div>
			<div class="jobupdate-button">
				<button type="submit" class="button-s">更新</button>
			</div>
		</div>
	</form>
</div>

<%@include file="/header_footer/footer.jsp"%>
