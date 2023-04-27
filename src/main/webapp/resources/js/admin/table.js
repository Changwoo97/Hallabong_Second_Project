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
	
	const pageButtons = document.getElementsByName('pageButton');
	const searchForm = document.getElementsByName('searchForm')[0];
	
	for (let i = 0; i < pageButtons.length; i++) {
		pageButtons[i].addEventListener('click', (e) => {
			const selectedPageNum = pageButtons[i].getAttribute('value');
			
			const hidden = document.createElement('input');
			hidden.setAttribute('type', 'hidden');
			hidden.setAttribute('name', 'selectedPageNum');
			hidden.setAttribute('value', selectedPageNum);

			searchForm.appendChild(hidden);
			searchForm.submit();
		});
	}
	
	const searchKeyAndValues = document.getElementsByClassName('searchKeyAndValue');
	for (let i = 0; i < searchKeyAndValues.length; i++) {
		const key = searchKeyAndValues[i].getAttribute('data-name');
		const value = searchKeyAndValues[i].getAttribute('data-value');
		
		const injection = document.getElementsByName(key);
		if (injection[0].tagName == 'select') {
			const opions = injection[0].getElementsByTagName('option');
			
			for (let i = 0; i < opions.length; i++) {
				if (opions[i].value == value) {
					opions[i].setAttribute("selected", "selected");
					break;
				}
			}
		} else {
			injection[0].setAttribute('value', value); 
			injection[0].value = value;
		}
	}
});
