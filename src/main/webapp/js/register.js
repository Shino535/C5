document.addEventListener("DOMContentLoaded", function () {
	const inputID = document.getElementById("userid");
	const inputNAME = document.getElementById("name");
	const inputPASS = document.getElementById("pass");
	const submitButton = document.getElementById("submitBtn");
	function checkInput() {
		let isValid = true;
		[inputID, inputNAME, inputPASS].forEach(input => {
			if (input.value.trim() === "") {
				input.style.border = "2px solid red";
				isValid = false;
			} else {
				input.style.border = "";
			}
		});
		submitButton.disabled = !isValid;
		if (isValid) {
			submitButton.classList.add("active");
		} else {
			submitButton.classList.remove("active");
		}
	}
	[inputID, inputNAME, inputPASS].forEach(input => {
		input.addEventListener("input", checkInput);
	});
	checkInput();
});
