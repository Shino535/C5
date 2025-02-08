document.addEventListener("DOMContentLoaded", function () {
	let icons = document.querySelectorAll('.material-symbols-rounded');
	
	icons.forEach(function(icon) {
		// アイコンのフォントが正しく読み込まれているか確認
		let fontFamily = window.getComputedStyle(icon).fontFamily;
		
		// Material Symbols フォントが読み込まれていない場合
		if (!fontFamily.includes("Material Symbols")) {
			// 代替アイコンとしてローカルSVGのパスを指定
			let svgPath = "/C5/img/square-icon.svg"; // SVGファイルの相対パス
			
			// アイコンのサイズを取得
			let iconSize = window.getComputedStyle(icon).fontSize;
			
			// 代替アイコン用のimg要素を作成
			let img = document.createElement('img');
			img.src = svgPath; // SVGファイルを設定
			img.alt = "代替アイコン";
			img.classList.add('fallback-icon');  // アイコンサイズ調整用クラスを追加
			
			// 代替アイコンに元のアイコンサイズを適用
			img.style.width = iconSize;  // 幅をアイコンのフォントサイズに設定
			img.style.height = iconSize; // 高さをアイコンのフォントサイズに設定
			
			// 元のアイコンを削除し、代替アイコンを挿入
			icon.innerHTML = '';  // 元の内容を削除
			icon.appendChild(img);  // 代替アイコンを追加
			icon.classList.remove("material-symbols-rounded");  // 元のクラスを削除
		}
	});
});
