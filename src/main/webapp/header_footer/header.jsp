<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="sql" uri="jakarta.tags.sql"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>C5リクルート</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&" />	
		<link rel="stylesheet" href="/C5/css/style.css">
	</head>
	<body>
		<header>
			<!-- トップページ -->
			<a href="/C5" class="title">
				<img src="/C5/img/hand-black.png" class="title-logo">
				<div class="title-text">
					<div class="title-top">C5-RECRUIT</div>
					<div class="title-bottom">みらいをつなぐ採用</div>
				</div>
			</a>
			
			<!-- ★ 追加: ログイン成功メッセージを表示 -->
			<c:if test="${not empty sessionScope.loginMessage}">
			    <p style="color: green; font-weight: bold; text-align: center;">${sessionScope.loginMessage}</p>
			    <c:remove var="loginMessage" scope="session"/> <%-- メッセージを1回表示したら削除 --%>
			</c:if>
			<!-- ★ 追加ここまで -->
			
			<!-- roleで分けるヘッダー -->
			<div class="nav-bar">
				<c:choose>
					<c:when test="${sessionScope.root.role=='root'}">
						<a href="/C5/root/ExperienceList.action" class="button-icon">
							<span class="material-symbols-rounded">
							contract_delete
							</span>
						</a>
						<a href="/C5/root/JobRegister.action" class="button-icon">
							<span class="material-symbols-rounded">
							add_home_work
							</span>
						</a>
						<a href="/C5/root/JobList.action" class="button-icon">
							<span class="material-symbols-rounded">
							edit_square
							</span>
						</a>
						<a href="/C5/Logout.action" class="button-icon">
							<span class="material-symbols-rounded">
							logout
							</span>
						</a>
					</c:when>
					<c:when test="${sessionScope.user.role=='user'}">
						<a href="/C5/user/userWithdrawConfirm.jsp" class="button-icon">
							<span class="material-symbols-rounded">
							delete
							</span>
						</a>
						<a href="/C5/Logout.action" class="button-icon">
							<span class="material-symbols-rounded">
							logout
							</span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="/C5/root/Login.action" class="nav-button">管理者ログイン</a>
						<a href="/C5/user/Login.action" class="button-icon">
							<span class="material-symbols-rounded">
							login
							</span>
						</a>
						<a href="/C5/user/Register.action" class="button-icon">
							<span class="material-symbols-rounded">
							person_add
							</span>
						</a>
					</c:otherwise>
				</c:choose>
				
				<!-- メニューボタン -->
				<button type="button" id="nav-user" class="button-icon">
					<span class="material-symbols-rounded">
					menu
					</span>
				</button>
			</div>
			<div id="user-menu" class="user-menu" style="display:none;">
				<c:choose>
					<c:when test="${sessionScope.root.role=='root'}">
						<div class="name">
							<c:out value="${sessionScope.root.name}"/>
						</div>
						<a href="/C5/root/Register.action" class="button-s">管理者登録</a>
						<a href="/C5/root/UserDeleteList.action" class="button-s">利用者BAN</a>
					</c:when>
					<c:when test="${sessionScope.user.role=='user'}">
						<div class="name">
							<c:out value="${sessionScope.user.name}"/>
						</div>
						<a href="/C5/user/UserUpdate.action" class="button-s">アカウント情報更新</a>
						<a href="/C5/Logout.action" class="button-s">ログアウト</a>
					</c:when>
					<c:otherwise>
						<div class="name">ゲストさん</div>
						<a class="button-s" href="/C5/user/Login.action">ログイン</a>
						<a class="button-s" href="/C5/user/Register.action">会員登録</a>
					</c:otherwise>
				</c:choose>
			</div>
		</header>
		<main>