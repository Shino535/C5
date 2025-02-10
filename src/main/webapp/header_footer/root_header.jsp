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
	<!--
	              きさま！見ているなッ！
	-->
	</head>
	<body>
		<sql:setDataSource var="dataSource" dataSource="jdbc/c5_db"/>
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
				<div class="nav-icon">
					<a href="/C5/root/Login.action" class="nav-button">
						<span class="material-symbols-rounded icon">
						login
						</span>
					</a>
					<div class="icon-text">管理者ログイン</div>
				</div>
			</div>
		</header>
		<main>