<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header_footer/header.jsp"%>


	<h3>更新対象求人</h3>

	<p>会社名：${jobBean.company}></p>
	<p>所在地：${jobBean.address}></p>

	<h3>更新内容入力</h3>

	<form action="JobUpdate.action" method="post" enctype="multipart/form-data">
	
	<input type="hidden" name="code" value="${jobBean.code}"/>
	
		<p>
			会社名<input type="text" name="company" value="${jobBean.company}">
		</p>
		<p>
			所在地<select name="prefecture">
				<option value="">選択してください</option>
				<option value="北海道" selected>北海道</option>
				<option value="青森県">青森県</option>
				<option value="岩手県">岩手県</option>
				<option value="宮城県">宮城県</option>
				<option value="秋田県">秋田県</option>
				<option value="山形県">山形県</option>
				<option value="福島県">福島県</option>
				<option value="茨城県">茨城県</option>
				<option value="栃木県">栃木県</option>
				<option value="群馬県">群馬県</option>
				<option value="埼玉県">埼玉県</option>
				<option value="千葉県">千葉県</option>
				<option value="東京都">東京都</option>
				<option value="神奈川県">神奈川県</option>
				<option value="新潟県">新潟県</option>
				<option value="富山県">富山県</option>
				<option value="石川県">石川県</option>
				<option value="福井県">福井県</option>
				<option value="山梨県">山梨県</option>
				<option value="長野県">長野県</option>
				<option value="岐阜県">岐阜県</option>
				<option value="静岡県">静岡県</option>
				<option value="愛知県">愛知県</option>
				<option value="三重県">三重県</option>
				<option value="滋賀県">滋賀県</option>
				<option value="京都府">京都府</option>
				<option value="大阪府">大阪府</option>
				<option value="兵庫県">兵庫県</option>
				<option value="奈良県">奈良県</option>
				<option value="和歌山県">和歌山県</option>
				<option value="鳥取県">鳥取県</option>
				<option value="島根県">島根県</option>
				<option value="岡山県">岡山県</option>
				<option value="広島県">広島県</option>
				<option value="山口県">山口県</option>
				<option value="徳島県">徳島県</option>
				<option value="香川県">香川県</option>
				<option value="愛媛県">愛媛県</option>
				<option value="高知県">高知県</option>
				<option value="福岡県">福岡県</option>
				<option value="佐賀県">佐賀県</option>
				<option value="長崎県">長崎県</option>
				<option value="熊本県">熊本県</option>
				<option value="大分県">大分県</option>
				<option value="宮崎県">宮崎県</option>
				<option value="鹿児島県">鹿児島県</option>
				<option value="沖縄県">沖縄県</option>
			</select> <input type="text" name="address">
		</p>
		<p>
			職種<input type="text" name="job_type" value="${jobBean.job_type}">
		</p>
		<p>
			月収<input type="text" name="benefit" value="${jobBean.benefit}">円
		</p>
		<p>
			年間休日<input type="text" name="holiday" value="${jobBean.holiday}">日
		</p>
		<p>
			雇用形態<select name="employment">
				<option value="">選択してください</option>
				<option value="正社員">正社員</option>
				<option value="派遣社員">派遣社員</option>
				<option value="契約社員">契約社員</option>
				<option value="AP">アルバイト・パート</option>
				<option value="業務委託">業務委託</option>
			</select>
		</p>
		<p>
			求人票PDF<input type="file" name="pdf" value="${jobBean.pdf_path}">
		</p>
		<p>
			<input type="submit" value="更新">
		</p>

	</form>

	<%@include file="/header_footer/footer.jsp"%>
