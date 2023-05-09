<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<style type="text/css">
.inputbox {
	width: 100%;
	height: 40px;
	padding: 0px 11px 1px 15px;
	border-radius: 4px;
	border: 1px solid rgb(221, 221, 221);
	font-weight: 400;
	font-size: 16px;
	line-height: 38px;
	color: rgb(51, 51, 51);
	border-style: none;
	box-sizing: border-box;
}

.dob_box {
	height: 44px;
	display: flex;
	-webkit-box-align: center;
	align-items: center;
	width: 100%;
	border: 1px solid rgb(204, 204, 204);
	border-radius: 3px;
	padding: 0px 15px;
}

.check_button {
	background-color: display: block;
	padding: 0px 10px;
	text-align: center;
	overflow: hidden;
	border-radius: 3px;
	color: rgb(0, 0, 0);
	background-color: #F7D358;
	border: 0px none;
}

.aa {
	min-width: 24px;
	min-height: 24px;
	display: inline-block;
	position: relative;
	border-radius: 50%;
	background-color: #F7D358;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

</head>
<body>
 <c:import url="/WEB-INF/views/include/top.jsp"/>


	<form id="contact-form" action="${root }cust/update_pw" method="post">

		<div class="container" style="margin-top: 100px" align="center">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<h4 align="left">개인 정보 수정</h4>
					<hr class="hr1" />
					<div class="card shadow">
						<div class="card-body">
							<div class="form-group" style="text-align: left;">
								<label>아이디</label>
								 <input type="text" name="id" value="${findpw.id}" readonly class="form-control" /> 
							</div>
							<div class="form-group" style="text-align: left;">
								<label>새 비밀번호</label> <input type="password" id="new_pwd" name="new_pwd" class="form-control">
							</div>
							<div class="form-group" style="text-align: left;">
								<label>새 비밀번호 확인</label>
								<input type="password" id="new_chk_pwd" name="new_chk_pwd" class="form-control">
							</div>
						</div>


						<div class="btn_wrap" style="padding: 10px;">

							<a href="javascript:void(0);" onclick="fnChangeMemberInfo();" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;">비밀번호 변경</a>
						</div>
					</div>
				</div>
			</div>


		</div>



	</form>
	 <c:import url="/WEB-INF/views/include/bottom.jsp"/>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		//주소 API

		function fnValidation() {
			var new_pwd = document.getElementById("new_pwd").value;
			var new_chk_pwd = document.getElementById("new_chk_pwd").value;
			//var passwdRegExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}/;  문자 대소문자 반드시 입력  
			var passwdRegExp = /^[A-Za-z0-9]{4,20}$/;
			if ((new_pwd != null && new_pwd != "")
					|| (new_chk_pwd != null && new_chk_pwd != "")) {
				if (new_pwd != new_chk_pwd) {
					alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					return false;
				} else {
					if ((passwdRegExp.test(new_pwd) == false)
							|| (passwdRegExp.test(new_chk_pwd) == false)) {
						//정규식에 맞지않으면 return null
						alert("비밀번호는 영문대소문자와 숫자를 포함하여 4자리에서 20자리 이내로 입력해 주세요.");
						return false;
					}
				}
			}
	return true;
		}


		// 회원정보수정
		function fnChangeMemberInfo() {
			if (fnValidation()) { // 값체크
				var params = $("#contact-form").serialize();// 아래의 주석과 동일한 형태로 전송됨.
				console.log("params=" + params);
				console.log("params=" + JSON.stringify(params));
				$.ajax({
					type : "post",
					url : "${root}cust/update_pw",
					data : params,
					dataType : 'json',
					success : function(data) {
						console.log("success_data_origin= " + data);
						console.log("success_data_str= "
										+ JSON.stringify(data));// Object로 받아온 것을 문자열의 형태로 풀어서 보여주는 것
						if (data.result > 0) {
							// 정상적으로 수정되었을 경우
							alert("변경 되었습니다.");
							location.href='${root}/cust/login'
						}
					},
					error : function(request, status, error) {
						console.log("error= code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				});
			}

		}

	
		
		

	
		
	</script>
</body>
</html>