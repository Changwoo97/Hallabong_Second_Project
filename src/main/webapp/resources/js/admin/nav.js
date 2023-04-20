window.addEventListener('load', () => {
	const nav = document.getElementById('nav');
	const trs = nav.getElementsByTagName('tr');
	const spans = nav.getElementsByTagName('span');
	
	const selectedColor = 'rgb(205, 232, 255)';
	const overedColor = 'rgb(229, 243, 255)'
	
	for (let i = 0; i < spans.length; i++) {
		spans[i].addEventListener('mouseover', (e) => {
			if(spans[i].style.backgroundColor == '') {
				spans[i].style.backgroundColor = overedColor;
			}
		});
		
		spans[i].addEventListener('mouseout', (e) => {
			if (spans[i].style.backgroundColor == overedColor) {
				spans[i].style.backgroundColor = '';
			}
		});
		
		// 메뉴 클릭시 배경색 변경
		spans[i].addEventListener('click', (e) => {
			for (let j = 0; j < i; j++) {
				spans[j].style.backgroundColor = '';
			}
			
			spans[i].style.backgroundColor = selectedColor;
			
			for (let j = i + 1; j < spans.length; j++) {
				spans[j].style.backgroundColor = '';
			}
		});
		
		// 더블 클릭
		const group = spans[i].getAttribute('id');
		const foldableBullet = document.getElementById(group + 'Bullet');

		if (group != null) {
			let foldables = [];
			
			for (let j = 0; j < trs.length; j++) {
				if (trs[j].getAttribute('class') == group) {
					foldables[foldables.length] = trs[j];
				}
			}
			
			function foldMenu(e) {
				for (let j = 0; j < foldables.length; j++) {
					if (foldables[j].style.display == '') {
						foldables[j].style.display = 'none';
						foldableBullet.style.transform = 'rotate(0deg)';
						foldableBullet.style.opacity = '0.5';
					} else {
						foldables[j].style.display = '';
						foldableBullet.style.transform = 'rotate(90deg)';
						foldableBullet.style.opacity = '1.0';
					}
				}
			}
			
			spans[i].addEventListener('dblclick', foldMenu);
			foldableBullet.addEventListener('click', foldMenu);
		} else {
			const dataHref = spans[i].getAttribute('data-href');

			if (dataHref != null) {
				// 메뉴 더블 클릭시 이동
				spans[i].addEventListener('dblclick', (e) => {
					location.href = dataHref;
				});
			}
		}
	}
});