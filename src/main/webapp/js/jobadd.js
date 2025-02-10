document.addEventListener("DOMContentLoaded", function() {
	// 現在のURLのパスを取得
	const currentPath = window.location.pathname;
	
	// 除外するパスを設定
	const excludedPaths = ["/C5/", "/C5/Search.action"];
	
	// 除外パスに一致する場合は処理を中断
	if (excludedPaths.includes(currentPath)) {
		return;
	}
	
	const inputCompany = document.querySelector("[name='company']");
	const inputPrefecture = document.querySelector("[name='prefecture']");
	const inputAddress = document.querySelector("[name='address']");
	const inputJobType = document.querySelector("[name='job_type']");
	const inputBenefit = document.querySelector("[name='benefit']");
	const inputHoliday = document.querySelector("[name='holiday']");
	const inputEmployment = document.querySelector("[name='employment']");
	const inputPdf = document.querySelector("[name='pdf']");
	const submitButton = document.querySelector(".jobadd-submit");
	
	function checkInput() {
		let isValid = true;
		const inputs = [
			{ element: inputCompany, isFile: false },
			{ element: inputPrefecture, isFile: false },
			{ element: inputAddress, isFile: false },
			{ element: inputJobType, isFile: false },
			{ element: inputBenefit, isFile: false },
			{ element: inputHoliday, isFile: false },
			{ element: inputEmployment, isFile: false },
			{ element: inputPdf, isFile: true }
		];
		inputs.forEach(input => {
			const element = input.element;
			if (input.isFile) {
				if (!element.files.length) {
					element.style.border = "2px solid red";
					isValid = false;
				} else {
					element.style.border = "";
				}
			} else {
				if (element.value.trim() === "") {
					element.style.border = "2px solid red";
					isValid = false;
				} else {
					element.style.border = "";
				}
			}
		});
		submitButton.disabled = !isValid;
		if (isValid) {
			submitButton.classList.add("active");
		} else {
			submitButton.classList.remove("active");
		}
	}
	[inputCompany, inputPrefecture, inputAddress, inputJobType, inputBenefit, inputHoliday, inputEmployment, inputPdf].forEach(input => {
		input.addEventListener("input", checkInput);
	});
	checkInput();
});
