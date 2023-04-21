let header;
let aside;
let movingBorder;
let main;

function resizeLayoutWidth() {
	main.style.width = window.innerWidth
		- aside.offsetWidth
		- movingBorder.offsetWidth
		- 4 + 'px';
}

function resizeLayoutHeight() {
	let height = window.innerHeight - header.offsetHeight;
	aside.style.height = height - 4 + 'px';
	movingBorder.style.height = height + 'px';
	main.style.height = height - 4 + 'px';
}

window.addEventListener('load', () => {
	header = document.getElementById('header');
	aside = document.getElementById('aside');
	movingBorder = document.getElementById('movingBorder');
	main = document.getElementById('main');
	
	resizeLayoutWidth();
	resizeLayoutHeight();

	// aside와 main의 border 포커스
	let focusedBorder = '2px solid rgb(0, 120, 215)';
	let unfocusedBorder = '2px solid rgb(240, 240, 240)';
	
	aside.addEventListener('click', (e) => {
		aside.style.border = focusedBorder;
		main.style.border = unfocusedBorder;
	});
	
	main.addEventListener('click', (e) => {
		aside.style.border = unfocusedBorder;
		main.style.border = focusedBorder;
	});
});

window.addEventListener('resize', resizeLayoutWidth);
window.addEventListener('resize', resizeLayoutHeight);
