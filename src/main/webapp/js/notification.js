document.addEventListener("DOMContentLoaded", function() {
	function fadeOutNotification(element) {
		setTimeout(() => {
			element.style.transition = "opacity 1s ease-out";
			element.style.opacity = "0";
			setTimeout(() => {
				element.style.display = "none";
			}, 1000);
		}, 2000);
	}
	document.querySelectorAll(".notification").forEach(fadeOutNotification);
});
