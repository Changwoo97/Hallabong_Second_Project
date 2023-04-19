let header;
let aside;
let movingBorder;
let main;

let isDragging = false;

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
	
	let asideWidth = document.getElementById('asideWidth').getAttribute('value');
	aside.style.width = asideWidth - 30 + 'px';
	
	resizeLayoutWidth();
	resizeLayoutHeight();
	
	// aside와 main의 width 조절
	movingBorder.addEventListener('mousedown', (e) => {
		isDragging = true;
		aside.style.cursor = 'e-resize';
		main.style.cursor = 'e-resize';
	});

	document.addEventListener('mousemove', (e) => {
		if (!isDragging) return;
		aside.style.width = e.clientX - 4 - 30 + 'px';
		resizeLayoutWidth();
	});

	document.addEventListener('mouseup', (e) => {
		isDragging = false;
		aside.style.cursor = '';
		main.style.cursor = '';
	});

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
