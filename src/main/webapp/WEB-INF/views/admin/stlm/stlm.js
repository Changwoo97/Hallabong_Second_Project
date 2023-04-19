window.addEventListener('load', function() {
	let check_all = document.getElementById('checkAll');
	let checkboxs = document.getElementsByClassName('check');

	check_all.addEventListener('click', (e) => {
		for (let i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = checkAll.checked;
		}
	});
});
