<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${root}css/UpdateForm_style.css" />
</head>
<body>

	<h4>개인 정보 수정</h4>	
	<hr class="hr1"/>
	<br>
	
	<form id="contact-form">
	<div class="formDiv">
		<table>
			<colgroup>
				<col style="20%"/>
				<col />
			</colgroup>
			<tbody>
			
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" id="login_id" name="login_id" value="id123" readonly />						
					</td>
				</tr>
				
				
				<tr>
					<th>현재 비밀번호</th>
					<td>
						<input type="password" id="now_pwd" name="now_pwd"  placeholder="현재 비밀번호를 입력하세요" value="">
						<input type="hidden" id="now_chk_pwd" name="now_chk_pwd" value="44444">
						<!-- <span id="now_pwd_error" style="display: none;">비밀번호를 입력해주세요</span> -->
					</td>
				</tr>
				
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" id="new_pwd" name="new_pwd" placeholder="새 비밀번호를 10자리 이하로 입력하세요" oninput="handleInputLength(this, 10)"></td>
				</tr>
				
				
				<tr>
					<th>새 비밀번호 확인</th>
					<td><input type="password" id="new_chk_pwd" name="new_chk_pwd" oninput="handleInputLength(this, 10)"></td>
				</tr>
				
				
				<tr>
					<th>이름</th>
					<td><input type="text"></td>
				</tr>
				
				
				<tr>
					<th>이메일</th>
					<td>
						<input type="email">
						<input type="submit" value="중복확인" class="btn-phone">
					</td>
				</tr>
				
				
				
				<tr>
					<th>휴대폰</th>
					<td>
						<input type="text">
						<input type="submit" value="다른번호 인증" class="btn-phone">
					</td>
				</tr>
				
				
				<tr>
					<th>성별</th>
					<td>
						<input type='radio' name='gender' value='female' />여성 
	       				<input type='radio' name='gender' value='male' />남성 
	        			<input type='radio' name='gender' value='nogender' />선택안함
					</td>
				</tr>
				
				<tr class="birth">
					<th>생년월일</th>  
					<td><input type="date"></td>
				</tr>
				
			
				
			
			</tbody>
		</table>
		<div class="btn_wrap">
			<form action="gotoresign">
			<input type="submit" value="탈퇴하기">
			</form>
			<input type="submit" onclick="javascript:fnChangeMemberInfo();" value="회원정보수정" />
		</div>
	</div>
	
	
	
<script>
	function fnValidation(){
		// 현재 비밀번호 체크		
		var now_pwd   = document.getElementById("now_pwd").value;
		var now_chk_pwd = document.getElementById("now_chk_pwd").value;
		if(now_pwd != now_chk_pwd){
			alert("현재 비밀번호가 맞지 않습니다. 다시 입력해주시기 바랍니다.");
			//document.getElementById("now_pwd_error").prop("display","block");
			//document.getElementById("now_pwd_error").style.display = "block";
			return false;
		}
		return;
		
		// 새 비밀번호 동일 입력 체크
		var new_pwd = document.getElementById("new_pwd").value;
		var new_chk_pwd = document.getElementById("new_chk_pwd").value;
		if(new_pwd == "" || new_chk_pwd == ""){
			alert("새 비밀번호 또는 새 비밀번호 확인을 입력해주세요.");
			return false;
		} else if(new_pwd != new_chk_pwd){
			alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
		
		return true;

	}

	function fnChangeMemberInfo(){
		if(fnValidation()){ // 값체크
			alert("정상 데이터!!! 저장실행!");
		}
	}
	
	function handleInputLength(el, max) {
	 	 if(el.value.length > max) {
	    	alert("비밀번호는 10자 이하입니다.");
	  	}
	}

</script>

</body>
</html>