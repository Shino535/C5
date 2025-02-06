document.addEventListener("DOMContentLoaded", function() {
	const elements=document.querySelectorAll(".text-width");
	elements.forEach(function(element){let text=element.textContent.trim();
		if(text.length<4){
			text=text.padEnd(4,' ')
		}
		element.textContent=text;
	});
});
