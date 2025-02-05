const button=document.getElementById("nav-user");
const menu=document.getElementById("user-menu");
button.addEventListener("click",(event)=>{
	if(menu.style.display=="none"){
		menu.style.display="flex";
	}else{
		menu.style.display="none";
	}
	event.stopPropagation();
});
menu.addEventListener("click",(event)=>{
	event.stopPropagation();
})
document.addEventListener("click",()=>{
	if(menu.style.display=="flex"){
		menu.style.display="none";
	}
});
