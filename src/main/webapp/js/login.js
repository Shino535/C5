document.addEventListener("DOMContentLoaded", function () {
	const inputID = document.getElementById("id");
	const inputPASS = document.getElementById("pass");
	const submitButton = document.getElementById("submitbtn");
	const togglePasswordIcon = document.querySelector(".toggle-password");
	function checkInput() {
		let isValid = true;
		[inputID, inputPASS].forEach(input => {
			if (input.value.trim() === "") {
				input.style.border = "2px solid red";
				isValid = false;
			} else {
			    input.style.border = "";
			}
		});
		submitButton.disabled = !isValid;
		if (isValid) {
			submitButton.classList.add("active"); // ボタンが有効なら色を変更
		} else {
			submitButton.classList.remove("active"); // ボタンが無効なら色を戻す
		}
	}
	[inputID, inputPASS].forEach(input => {
	    input.addEventListener("input", checkInput); // 入力時にチェックを実行
	});
	checkInput();
});
