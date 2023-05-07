let select = false;

function selectAll() {
	select = !select;
	
	let cartList = document.getElementsByName('cartList');
	
	for (let i = 0; i < cartList.length; i++) {
		cartList[i].checked = select;
	}
}

function deleteSelected() {
	let form = document.getElementById('form');
	let root = document .getElementById('root');
	
	form.setAttribute('action', root.getAttribute('value') + 'mypage/delete');
	form.submit();
}

function ordersSelected() {
	let form = document.getElementById('form');
	let root = document .getElementById('root');
	
	form.setAttribute('action', root.getAttribute('value') + 'order/order');
	form.submit();
}