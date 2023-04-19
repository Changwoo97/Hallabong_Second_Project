window.addEventListener('load', () => {
	const checkAll = document.getElementById('checkAll');
	const checks = document.getElementsByName('checks');

	checkAll.addEventListener('click', (e) => {
		for (let i = 0; i < checks.length; i++) {
			checks[i].checked = checkAll.checked;
		}
	});
});