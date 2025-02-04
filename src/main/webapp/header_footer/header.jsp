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
		<link rel="stylesheet" href="/C5/css/style.css">
	</head>
	<body>
		<header>
			<!-- トップページ -->
			<div class="title-container">
				<a href="/C5" class="title">
					<h1>C5</h1>RECRUIT
				</a>
			</div>
			
			<!-- roleで分けるヘッダー -->
			<div>
				<c:choose>
					<c:when test="${sessionScope.root.role=='root'}">
						<a href="/C5/root/Register.action">管理者アカウント登録</a>
						<a href="/C5/root/ExperienceList.action">就活体験談削除</a>
						<a href="/C5/root/JobRegister.action">求人情報登録</a>
						<a href="/C5/root/JobList.action">求人情報管理</a>
						<a href="/C5/Logout.action">ログアウト</a>
					</c:when>
					<c:when test="${sessionScope.user.role=='user'}">
						<a href="/C5/Logout.action" class="nav-icon">ログアウト</a>
					</c:when>
					<c:otherwise>
						<a href="/C5/root/Login.action" class="nav-button">管理者ログイン</a>
						<a href="/C5/user/Login.action" class="nav-button">利用者ログイン</a>
						<a href="/C5/user/Register.action" class="nav-button">利用者会員登録</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 名前表示 -->
				<button>
					<c:choose>
						<c:when test="${not empty sessionScope.root.name}">
							<c:out value="${sessionScope.root.name}"/>
						</c:when>
						<c:when test="${not empty sessionScope.user.name}">
							<c:out value="${sessionScope.user.name}"/>
						</c:when>
						<c:otherwise>
							<c:out value="ゲストさん"/>
						</c:otherwise>
					</c:choose>
				</button>
			</div>
		</header>
		<main>