<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="${root}css/UpdateForm_style.css" />
</head>
<body>

	<h4>개인 정보 수정</h4>
	<hr class="hr1" />
	<br>

	<form id="contact-form" action="${root}cust/update" method="post">
		<div class="formDiv">
			<table>
				<colgroup>
					<col style="" />
					<col />
				</colgroup>
				<tbody>
						<tr>
							<th>아이디</th>
							<td>
								<input type="text" id="id" name="id" value="${custInfo.id}" />
							</td>
						</tr>
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" id="pw" name="pw" value="${custInfo.pw}"> 
								<input type="hidden" id="now_chk_pwd" name="now_chk_pwd" value="${custInfo.pw}">
							</td>
						</tr>

						<tr>
							<th>새 비밀번호</th>
							<td><input type="password" id="new_pwd" name="new_pwd"></td>
						</tr>


						<tr>
							<th>새 비밀번호 확인</th>
							<td><input type="password" id="new_chk_pwd" name="new_chk_pwd" ></td>
						</tr>


						<tr>
							<th>이름</th>
							<td>
								<input type="text" id="name" name="name" value="${custInfo.name}">
							</td>
						</tr>
						
						<tr>
							<th>주소</th>
							<td>
								<%-- <input type="text" id="addr" name="addr" value="${custInfo.addr}"> --%>
								<input type="text" id="addr1" name="addr1" value="${custInfo.addr}" readonly >
							</td>
						</tr>
						
						<tr>
							<th>상세 주소</th>
							<td>
								<input type="text" id="addr_detail" name="addr_detail">
							</td>
						</tr>

						<tr>
							<th>이메일</th>
							<td>
							    <input id="email" name="email" type="email" maxlength="64" onchange="fnEmailChange();" placeholder="이메일 중복확인을 해주세요." value="${custInfo.email}"/>
							    <button type="button" id="emailChkBtn" class="btn ${!empty custInfo.email ? 'disabled' : ''}" ${!empty custInfo.email ? 'disabled' : ''} onclick='fnEmailCheck()'>중복확인</button>
							</td>
						</tr>
						<tr>
							<th>휴대폰</th>
							<td><input type="text" id="tel" name="tel" value="${custInfo.tel}"> 
							</td>
						</tr>


						<tr>
							<th>성별</th>
							<td>
								<label for="female"><input type='radio' name='gender' id='female' value='F' ${custInfo.gender eq 'F' ? "checked" : ""} />여성</label>
								<label for="male"><input type='radio' name='gender' id='male' value='M' ${custInfo.gender eq 'M' ? "checked" : ""} />남성</label>
							</td>
						</tr>

						<tr class="birth">
							<th>생년월일</th>
							<td><input type="date" id="dob" name="dob" value="${custInfo.dob}" /></td>
						</tr>
				</tbody>
			</table>
		
			<div class="btn_wrap">
				 <a href="javascript:void(0);" onclick="fnDeleteConfirm();" class="btn">탈퇴하기</a>
				 <a href="javascript:void(0);" onclick="fnChangeMemberInfo();" class="btn">회원정보수정</a>
			</div>
		</div>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

	//주소 API
	window.onload = function(){
		document.getElementById("addr1").addEventListener("click", function(){ //주소입력칸을 클릭하면 지도 발생
			
			new daum.Postcode({
				oncomplete: function(data) { //선택시 입력값 세팅
				document.getElementById("addr1").value = data.address; // 주소 넣기
                document.querySelector("input[name=addr_detail]").focus(); //상세입력 포커싱
			}
			}).open();
		});
	}
	
   function fnValidation(){
      // 현재 비밀번호 체크      
      var passwd   = document.getElementById("pw").value;
      var now_chk_pwd = document.getElementById("now_chk_pwd").value;
      if(passwd != now_chk_pwd){
         alert("현재 비밀번호가 맞지 않습니다.\n다시 입력해 주세요.");
         return false;
      }
      
	  var new_pwd   = document.getElementById("new_pwd").value;
      var new_chk_pwd = document.getElementById("new_chk_pwd").value;
      //var passwdRegExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}/;  문자 대소문자 '반드시' 입력  
      var passwdRegExp = /^[A-Za-z0-9]{4,20}$/; 
      if((new_pwd != null && new_pwd != "") || (new_chk_pwd != null && new_chk_pwd != "")){
    	  if(new_pwd != new_chk_pwd){
    		  alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    		  return false;
    	  } else {
    		  if ((passwdRegExp.test(new_pwd) == false) || (passwdRegExp.test(new_chk_pwd) == false)) {
    	    	    //정규식에 맞지않으면 return null
    	    	    alert("비밀번호는 영문, 숫자를 허용하며 4자리에서 20자리 이내로 입력해 주세요."); 
    	    	    return false;
    	      }
    	  }    	  
      } 
      
      var custNm = $("#name").val();
      if (custNm == "") {
   	     alert("이름을 입력해 주세요.");
   	     return false;
   	  }
      
      if(!$("#emailChkBtn").attr("disabled")){ //가져오는 값 그대로일땐 비활성화, 변경되면 활성화 되어 버튼 사용할 수 있음
    	  alert("이메일 중복을 확인해 주세요.");
    	  return false;
      }
      
   	  //휴대폰 정규표현식
   	  var telVal = $("#tel").val();
   	  var regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
   	  if (!regExp.test(telVal)) {
   	     //정규식에 맞지않으면 리턴
   	     alert("휴대폰 형식을 확인해 주세요.");
   	     return false;
   	  }
   	  
   	  return true;
    	
   }

   // 회원정보수정
   function fnChangeMemberInfo(){
   		if(fnValidation()){ // 값체크
   			var params = $("#contact-form").serialize();// 아래의 주석과 동일한 형태로 전송됨.
            console.log("params=" + params);
			console.log("params=" + JSON.stringify(params));
            $.ajax({
                type: "post",
                url: "${root}cust/update",
                data: params,
                dataType: 'json',
                success: function (data) {
                	var sdata = JSON.parse(JSON.stringify(data));
                    console.log("success_data_origin= " + JSON.stringify(data));
                    console.log("success_data_str= " + sdata);// Object로 받아온 것을 문자열의 형태로 풀어서 보여주는 것
                    if(sdata.result > 0){
                    	// 정상적으로 수정되었을 경우
                    	alert("수정 되었습니다.");
                    }
                },
                error: function (request, status, error) {                	
                    console.log("error= code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        }
      
    }
   
    // 이메일 칸에 수정을했을때 중복 확인버튼 활성화
    function fnEmailChange(){
    	if($("#emailChkBtn").attr("disabled")){
    		$("#emailChkBtn").prop('disabled', false); 
    		$("#emailChkBtn").removeClass('disabled');    //disabled 클래스를 제거하여, 이 버튼을 활성화 상태로 변경    	 
    	}
    }
   
    // 이메일 중복확인
    function fnEmailCheck() {
    	var email = $("#email").val();
    	if(email == ""){
    		alert("이메일을 입력해 주세요.");
    		return;
    	}
    	
    	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (email.match(regExp) == null) {
      	    //정규식에 맞지않으면 return null
      	    alert("이메일 형식을 확인해 주세요.");
      	    return false;
        }
    	
    	//key : value 형식의 data를 직접 입력
    	var params = {
        		id: $("#id").val()
        		, email: email
        }
            
        // ajax 통신
        $.ajax({
            type : "POST",      // HTTP method type(GET, POST) 형식
            url : "${root}cust/email/check", // 컨트롤러에 구현해놓은 URL 주소
            data : params,           
            success : function(result) { 
                // 여기로 들어오는 자체가 ajax 자체는 성공!
                var json = JSON.parse(JSON.stringify(result));
                if (json.emailCount == 0) {
                	alert("사용가능한 이메일 입니다.");
                	$("#emailChkBtn").addClass('disabled');
                	$("#emailChkBtn").prop('disabled', true);
                } else {
                	alert("중복된 이메일이 존재합니다.");
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
                // ajax 통신중 문제가 있었을 경우
                // 일반적으로는 관리자에게 문의하세요. 등의 alert를 띄움.
                alert("관리자에게 문의하세요.");
            }
        });
    }

	// 탈퇴 컨펌 함수
	function fnDeleteConfirm() {
	    if (confirm("정말 탈퇴 하시겠습니까?")) {
	        // 예를 눌렀을 경우
	        // 아래의 params는 파라미터로 보낼값(아래는 json 형태로 구현)
	        var params = {
	        		id: $("#id").val()
	        }
	            
	        // ajax 통신
	        $.ajax({
	            type : "POST",      // HTTP method type(GET, POST) 형식
	            url : "${root}cust/quit", // 컨트롤러에 구현해놓은 URL 주소
	            data : params,           
	            success : function(result) { 
	            	var json = JSON.parse(JSON.stringify(result));
	                // 여기로 들어오는 자체가 ajax 자체는 성공!
	                if(json.delCount > 0){
	                	alert("탈퇴 처리 되었습니다.");
	                	// 로그아웃(세션삭제) 처리하고 메인으로 보내버리면됨.
	                	location.href = '${root}';
	                }
	            },
	            error : function(XMLHttpRequest, textStatus, errorThrown){
	                // ajax 통신중 문제가 있었을 경우
	                // 일반적으로는 관리자에게 문의하세요. 등의 alert를 띄움.
	                alert("관리자에게 문의 하세요.");
	            }
	        });
	    } else {
	        // 아니오를 눌렀을 경우
	        // 별다른 행동이 없을 경우 else는 삭제해도됨.
	
	    }
	}
	
</script>
</body>
</html>