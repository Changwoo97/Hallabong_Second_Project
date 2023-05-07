window.addEventListener('load', () => {
	$('#recv_check').click(() => {
		let recv_check = document.getElementById('recv_check');
		if (recv_check.checked) {
			let ordr_name = $('#ordr_name').val();
			let ordr_tel = $('#ordr_tel').val();
			let ordr_addr = $('#ordr_addr').val();
			
			$('#recv_name').val(ordr_name);
			$('#recv_tel').val(ordr_tel);
			$('#recv_addr').val(ordr_addr);
			
			$('#recv_name').attr('readonly', true);
			$('#recv_tel').attr('readonly', true);
			$('#recv_addr').attr('readonly', true);
		} else {
			$('#recv_name').val('');
			$('#recv_tel').val('');
			$('#recv_addr').val('');
			
			$('#recv_name').attr('readonly', false);
			$('#recv_tel').attr('readonly', false);
			$('#recv_addr').attr('readonly', false);
		}
	});
});
