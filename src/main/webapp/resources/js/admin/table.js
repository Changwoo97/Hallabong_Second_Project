window.addEventListener('load', () => {
	const tableMenubar = document.getElementById('tableMenubar');
	const searchView = tableMenubar.getElementsByClassName('searchView');
	const tableSearch = document.getElementById('tableSearch');
	const table = document.getElementById('table');
	const thead = table.getElementsByTagName('thead');
	
	tableSearch.style.display = 'none';
	
	for (let i = 0; i < searchView.length; i++) {
		searchView[i].addEventListener('click', (e) => {
			if (tableSearch.style.display == '') {
				tableSearch.style.display = 'none';
				thead[0].style.top = 80 + 'px';
			} else {
				tableSearch.style.display = '';
				thead[0].style.top = 80 + tableSearch.offsetHeight + 'px';
			}
		});
	}
});
