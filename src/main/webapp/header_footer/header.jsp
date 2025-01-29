<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="sql" uri="jakarta.tags.sql"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>C5リクルート</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&" />	
	</head>
	<body>
		<header>
			<!-- トップページ -->
			<a href="/C5/">C5</a>
			
			<!-- roleで分けるヘッダー -->
			<c:choose>
				<c:when test="${sessionScope.root.role=='root'}">
					<a href="/C5/root/Register.action">管理者アカウント登録</a>
					<a href="/C5/root/ExperienceList.action">就活体験談削除</a>
					<a href="/C5/root/JobRegister.action">求人情報登録</a>
					<a href="/C5/root/JobList.action">求人情報管理</a>
					<a href="/C5/Logout.action">ログアウト</a>
				</c:when>
				<c:when test="${sessionScope.user.role=='user'}">
					<a href="/C5/Logout.action">ログアウト</a>
				</c:when>
				<c:otherwise>
					<a href="/C5/root/Login.action">管理者ログイン</a>
					<a href="/C5/user/Login.action">利用者ログイン</a>
					<a href="/C5/user/Register.action">利用者会員登録</a>
				</c:otherwise>
			</c:choose>
			
			<!-- 名前表示 -->
			<c:choose>
				<c:when test="${not empty sessionScope.root.name}">
					<c:out value="${sessionScope.root.name}"/>
				</c:when>
				<c:when test="${not empty sessionScope.user.name}">
					<c:out value="${sessionScope.user.name}"/>
				</c:when>
				<c:otherwise>
					ゲスト
				</c:otherwise>
			</c:choose>
			
			<form action="/C5/Search.action" method="get">
				会社名:<input type="text" name="company" value="${searchList.company}"><br>
				県:
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
				</select><br>
				住所:<input type="text" name="address" value="${searchList.address}"><br>
				職種:<input type="text" name="job_type" value="${searchList.jobType}"><br>
				月収:
				<select name="benefit">
					<option value="">選択してください</option>
					<option value="150000" ${searchList.benefit=='150000'?'selected':''}>15万円以上</option>
					<option value="200000" ${searchList.benefit=='200000'?'selected':''}>20万円以上</option>
					<option value="250000" ${searchList.benefit=='250000'?'selected':''}>25万円以上</option>
					<option value="300000" ${searchList.benefit=='300000'?'selected':''}>30万円以上</option>
				</select><br>
				年間休日:
				<select name="holiday">
					<option value="">選択してください</option>
					<option value="100" ${searchList.holiday == '100' ? 'selected' : ''}>100日以上</option>
					<option value="110" ${searchList.holiday == '110' ? 'selected' : ''}>110日以上</option>
					<option value="120" ${searchList.holiday == '120' ? 'selected' : ''}>120日以上</option>
					<option value="130" ${searchList.holiday == '130' ? 'selected' : ''}>130日以上</option>
				</select><br>
				雇用形態:
				<select name="employment">
					<option value="">選択してください</option>
					<option value="正社員" ${searchList.employment == '正社員' ? 'selected' : ''}>正社員</option>
					<option value="派遣社員" ${searchList.employment == '派遣社員' ? 'selected' : ''}>派遣社員</option>
					<option value="契約社員" ${searchList.employment == '契約社員' ? 'selected' : ''}>契約社員</option>
					<option value="AP" ${searchList.employment == 'AP' ? 'selected' : ''}>アルバイト・パート</option>
					<option value="業務委託" ${searchList.employment == '業務委託' ? 'selected' : ''}>業務委託</option>
				</select><br> 
				<input type="submit" value="検索">
				<input type="hidden" value="1" name="page">
			</form>
		</header>
		<main>
