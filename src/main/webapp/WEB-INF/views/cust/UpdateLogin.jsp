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
     <form id="chkForm" action="${root }cust/login/check" method="post">
	     <label>  아이디 <input type="text" name="cust_id" id="cust_id" class="text-field" value="${cust_id}" placeholder="아이디" readonly></label><br/>      
	     <label>  비밀번호 <input type="password" name="passwd" id="passwd" class="text-field" placeholder="현재 비밀번호를 입력하세요"></label><br/>
		 <button type="button" onclick="fnGotoUpdate();" class="submit-btn">확인</button>     
     </form>
     
     <form id="updateForm" action="${root }cust/update/form" method="post">
	     <input type="hidden" name="cust_idx" id="cust_idx" />     
     </form>
   </div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

	function fnValidation(){
		//비밀번호 값이 비었을 때 
		var passwd = document.getElementById("passwd").value;
		var regExp = /^[A-Za-z0-9]{4,20}$/; 
		//var regExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}/; 문자 대소문자 반드시 입력
		
		if(passwd == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
			
		} else if (!regExp.test(passwd)) {
	    	//비밀번호 정규표현식(숫자와 영문대소문자 포함 형태의 4~20자리 이내의 암호 정규식-필수 아님)
	        alert("비밀번호는 영문, 숫자를 허용하며 4자리에서 20자리 이내로 입력하세요."); 
	        return false;
	    }  
		
		return true;
	}		
	
	
	function fnGotoUpdate(){
		if(fnValidation()){ 
			// 아이디 / 패스워드 일치시 개인정보 수정 페이지 이동
			var params = $("#chkForm").serialize();// 아래의 주석과 동일한 형태로 전송됨.
			//var chkForm = {
			//		custId: $("#custId").val()
	        //        , passwd : $("#passwd").val()
	        //}
            console.log("params=" + params);
            $.ajax({
                type: "post",
                url: "/cust/login/check",
                data: params,
                dataType: 'json',
                success: function (data) {
                    console.log("success_data_origin= " + data);
                    console.log("success_data_str= " + JSON.stringify(data));// Object로 받아온 것을 문자열의 형태로 풀어서 보여주는 것
                    console.log("success_data3_ex= " + data.cust_idx);
                    if(data.cust_idx > 0){
                    	// update 페이지로 이동
                    	var cust_idx = data.cust_idx;
                    	$("#updateForm #cust_idx").val(cust_idx); // *id가 cust_idx인 요소 선택, 해당 요소의 값을 방금 할당한 cust_idx 변수로 설정
                    	$("#updateForm").submit();
                    } else {
                    	alert("비밀번호를 다시 확인해주시기 바랍니다.");
                    }
                },
                error: function (request, status, error) {                	
                    console.log("error= code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            });
		}
	}
	
	
</script>
</body>
</html>





	