document.addEventListener("DOMContentLoaded", function() {
	function fadeOutNotification(id) {
		let notification = document.getElementById(id);
		if (notification) {
			setTimeout(() => {
				notification.style.transition = "opacity 1s ease-out";
				notification.style.opacity = "0";
				setTimeout(() => {
					notification.style.display = "none";
				}, 1000);
			}, 2000);
		}
	}
	fadeOutNotification("dbConError");
	fadeOutNotification("logoutMsg");
});
