window.addEventListener('load', () => {
	let cartButton = document.getElementById('cartButton');
	
	cartButton.addEventListener('click', (e) => {
		if ($('#custLogin').val() == 'false') {
			alert('로그인이 필요합니다.');
			return;
		}
		
		let prod_no = $('#prod_no').val();
		let qnty = $('#qnty').val();
		
		$.ajax({
	         url : $('#root').val() + 'cart/register',
	         type : 'post',
	         data: { 'prod_no': prod_no, 'qnty': qnty },
	         dataType : 'text',
	         success : (result) => {
				let json = JSON.parse(result);

	            if(json.success == true){
	               alert('장바구니에 추가되었습니다.');
	            } else {
	               alert('장바구니 추가에 실패했습니다.');
	            }
	         }
		});
	});
});