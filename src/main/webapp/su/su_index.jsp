<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<head>
	<style>
		/* スクロールする要素を囲むコンテナ */
		.scroll-container {
			overflow: hidden;
			white-space: nowrap;
			width: 100vw;
			background: #f0f0f0;
			position: relative;
		}
		
		/* スクロールするコンテンツ（2倍にしてループを滑らかに） */
		.scroll-content {
			display: flex;
			gap: 20px;
			width: max-content;
			animation: scroll-left 12s linear infinite;
		}
		
		/* 各アイテムのスタイル */
		.scroll-item {
			flex: 0 0 auto;
			min-width: 20vw;
			padding: 20px;
			background: #3498db;
			color: white;
			text-align: center;
			border-radius: 10px;
		}
		
		/* 左へ無限スクロール */
		@keyframes scroll-left {
			from {
				transform: translateX(0);
			}
			to {
				transform: translateX(-50%);
			}
		}
	</style>
</head>
<body>
	<div class="scroll-container">
		<div class="scroll-content">
			<div class="scroll-item">Item 1</div>
			<div class="scroll-item">Item 2</div>
			<div class="scroll-item">Item 3</div>
			<div class="scroll-item">Item 4</div>
			<div class="scroll-item">Item 5</div>
			
			<!-- 途切れを防ぐための複製 -->
			<div class="scroll-item">Item 1</div>
			<div class="scroll-item">Item 2</div>
			<div class="scroll-item">Item 3</div>
			<div class="scroll-item">Item 4</div>
			<div class="scroll-item">Item 5</div>
		</div>
	</div>
<%@include file="/header_footer/footer.jsp"%>