<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${root}css/UpdateLogin_style.css" />

</head>
<body>	
	<h4>개인 정보 수정</h4>
	<p>비밀번호 재확인
	회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</p>
	
	
	<hr class="hr1"/>
	
 	<div class="login-form">
 	<%-- <form:form action="${root }user/login_pro" method="post" modelAttribute="tempLoginUserBean">
     <label>  아이디 <input type="text" name="id" id="id" class="text-field" value="id123" placeholder="아이디" readonly><br></label>      
     <label>  비밀번호 <input type="password" name="now_pwd" id="now_pwd" value="44444" class="text-field" placeholder="현재 비밀번호를 입력하세요"><br></label>
					<input type="hidden" name="now_chk_pwd" id="now_chk_pwd"  value="44444">
     
      <input type="submit" onclick="javascript:fnGotoUpdate();" value="확인" class="submit-btn">
      
    </form> --%>
    
    <form action="${root }cust/login/check" method="post" modelAttribute="paramLoginCustBean">
         <label>  아이디 <input type="text" name="id" id="id" class="text-field" value="id123" placeholder="아이디" readonly><br></label>      
     	 <label>  비밀번호 <input type="password" name="pw" id="pw" value="44444" class="text-field" placeholder="현재 비밀번호를 입력하세요"><br></label>
		 <input type="hidden" name="now_chk_pwd" id="now_chk_pwd"  value="44444"><br>    
		 <input type="submit" value="확인" class="submit-btn"/>
     </form>
    
  </div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function fnValidation(){
		//비밀번호 값이 비었을 때 
		var id = document.querySelector('#id');
		var now_pwd = document.getElementById("now_pwd").value;
		var now_chk_pwd = document.getElementById("now_chk_pwd").value;
		
		if(now_pwd == ""){
			alert("비밀번호를 입력해주세요.")
			return false;
			
		} else if(now_pwd !== now_chk_pwd){
			alert("비밀번호를 확인해주세요.")
			return false;
			
		}
		return true;
	}		
	
	
	function fnGotoUpdate(){
		if(fnValidation()){ 
			alert("로그인 되었습니다.");
		}
	}
	
</script>
</body>
</html>





	