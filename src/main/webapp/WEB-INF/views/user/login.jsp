<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<style type="text/css">
.btnLogin {
	background-color: display: block;
	padding: 0px 10px;
	text-align: center;
	overflow: hidden;
	width: 300px;
	height: 54px;
	border-radius: 3px;
	color: rgb(255, 255, 255);
	background-color: rgb(95, 0, 128);
	border: 0px none;
}

.signup {
	display: block;
	padding: 0px 10px;
	text-align: center;
	overflow: hidden;
	width: 300px;
	height: 54px;
	border-radius: 3px;
	color: rgb(95, 0, 128);
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(95, 0, 128);
}

.inputbox {
	width: 100%;
	height: 46px;
	padding: 0 11px 1px 15px;
	border-radius: 4px;
	border: 1px solid #ddd;
	font-weight: 400;
	font-size: 16px;
	line-height: 1.5;
	color: #333;
	outline: none;
	box-sizing: border-box;
}
</style>

</head>
<body>
 <c:import url="/WEB-INF/views/include/top.jsp"/>
	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">

						<c:if test="${fail == true }">
							<div class="alert alert-danger">
								<h3>로그인 실패</h3>
								<p>아이디 비밀번호를 확인해주세요</p>
							</div>
						</c:if>
						<form:form action="${root }user/login_pro" method="post" modelAttribute="tempLoginUserBean">
							<div class="form-group">
								<form:input path="id" placeholder="아이디를 입력해주세요" class="inputbox" />
								 <form:errors path="id" style="color:red"/> 
							</div>

							<div class="form-group">
								<form:password path="pw" placeholder="비밀번호를 입력해주세요" class="inputbox" />
								 <form:errors path="pw" style="color:red"/> 
							</div>

							<div align="right">
								<a href="${root }user/find_id_phone" style="font-size: 12px;">아이디 찾기</a> 
								<a>|</a> 
								<a href="${root }user/find_pw_phone" style="font-size: 12px;">비밀번호 변경</a>
							</div>
							<div align="center" style="padding: 10px;">
								<form:button class="btnLogin" path="btnLogin">로그인</form:button>
							</div>
							<div align="center">
								<a href="${root }user/join" class="signup" style="padding-top: 13px">회원가입</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>


	 <c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
</body>
</html>








