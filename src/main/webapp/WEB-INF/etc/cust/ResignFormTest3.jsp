<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="ResignFormTest_style3.css">
<style>
#container{
  width: 60%;
  margin: 0 auto; /* 가로로 중앙에 배치 */
  padding-top: 30%; 
}
</style>

<title>탈퇴하기</title>
</head>

<body>
	<center>
		<h2>회원탈퇴</h2>
		<hr />
		<table width="700" border="0">
		<tr height="200">
           <td width="200" align="center">회원 탈퇴 안내</td>
           <td colspan="2"><font size="2"><p>고객님께서 회원 탈퇴를 원하신다니 저희 쇼핑몰의 서비스가 많이 부족하고 미흡했나 봅니다.</p>
				<p>불편하셨던 점이나 불만사항을 알려주시면 적극 반영해서 고객님의 불편함을 해결해 드리도록 노력하겠습니다.</p>
				<strong>아울러 회원 탈퇴시 아래 사항을 숙지하시기 바랍니다.</strong>
				<p>회원 탈퇴 시 고객님의 정보는 상품 반품 및 A/S를 위해 전자상거래 등에서의 소비자 보호에 관한 법률에
					의거한 고객정보 보호정책에 따라 관리 됩니다.</p>
				<p>탈퇴 시 고객님께서 보유하셨던 적립금은 모두 삭제 됩니다.</p>
				<p>회원 탈퇴 후 3개월간 재가입이 불가능합니다.</p></font></td>
		 
		 
         <tr height="50">
          <td width="200" align="center">비밀번호 입력</td>
           <td colspan="2">	<input type="text" class="text-field" placeholder="현재 비밀번호를 입력해주세요"></td>
       		</td>
       	  
         
         <tr height="300">
           <td width="200" align="center">무엇이 불편 하였나요?</td>
           <td width="200" class='complain1'>
           <input type='radio' name='complain1' value='complain1' />상담,포장 등 불만<br>
			<input type='radio' name='complain2' value='complain2'  />배송불만<br>
			<input type='radio' name='complain3' value='complain3' />교환/환불/반품 불만 <br>
			<input type='radio' name='complain4' value='complain4' />방문 빈도 낮음<br> 
			<td width="200" class='complain2'>
			<input type='radio' name='complain5' value='complain5' />상품가격 불만 <br>
			<input type='radio' name='complain6' value='complain6' />개인 정보유출 우려<br>
			<input type='radio' name='complain7' value='complain7' />쇼핑몰 신뢰도 불만<br>
			<input type='radio' name='complain8' value='complain8' />쇼핑기능 불만</td>
         </tr>
         </table>
    		<br>

				<input type="button" value="취소" class="button" onclick="history.back()"> &nbsp; 
				<input type="button" value="탈퇴" class="button" onclick="location.href='BoardUpdateCon.do?num=${bean.num }'"> &nbsp; 
			
		
	</center>
</body>
</html>
