<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
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

a {
	text-align: left;
	font-weight: 500;
	color: rgb(51, 51, 51);
	line-height: 20px;
}

button {
	display: block;
	padding: 0px 10px;
	text-align: center;
	overflow: hidden;
	width: 100%;
	height: 52px;
	border-radius: 3px;
	color: rgb(95, 0, 128);
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(95, 0, 128);
}

.gender {
	min-width: 24px;
	min-height: 24px;
	border-radius: 50%;
	background-color: rgb(95, 0, 128);
}

.birthday {
	width: 100%;
	height: 40px;
	padding: 0px 11px 1px 15px;
	border-radius: 4px;
	border: 1px solid rgb(221, 221, 221);
	font-weight: 400;
	font-size: 16px;
	line-height: 38px;
	color: rgb(51, 51, 51);
	outline: none;
	box-sizing: border-box;
}

.signupbutton {
	display: block;
	padding: 0px 10px;
	text-align: center;
	overflow: hidden;
	width: 240px;
	height: 56px;
	border-radius: 3px;
	color: rgb(255, 255, 255);
	background-color: rgb(95, 0, 128);
	border: 0px none;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<center>
		<div class="container" style="margin-top: 100px">
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-6">
					<form action="${root }user/login" method="get">
						<div>
							<h1 style="padding-left: 100px;">회원가입</h1>
						</div>
						<div>
							<table width=800>
								<tr>
									<td align="right"><span><font color="red">*</font></span>
										<a>필수입력사항</a>
										<hr /></td>
								</tr>
							</table>
						</div>
						<div align="center">
							<table width=800>
								<tr>
									<td><a>이름</a> <span><font color="red">*</font></span></td>
									<td width="550"><input type="text" name="name"
										size="20" placeholder="이름을 입력해주세요" class="inputbox"></td>
								</tr>
								<tr>
									<td><a>아이디</a> <span><font color="red">*</font></span></td>
									<td width="550"><input type="text" name="id"
										size="20" placeholder="아이디를 입력해주세요" class="inputbox">
									</td>
									<td>
										<button>중복확인</button>
									</td>
								</tr>
								<tr>
									<td><a>비밀번호</a> <span><font color="red">*</font></span></td>
									<td width="550"><input type="password" name="pw"
										size="20" placeholder="비밀번호를 입력해주세요" class="inputbox">
									</td>
								</tr>
								<tr>
									<td><a>비밀번호확인 <font color="red">*</font></a></td>
									<td width="550"><input type="password" name="pw2"
										size="20" placeholder="비밀번호를 다시 입력해주세요" class="inputbox">
									</td>
								</tr>
								<tr>
									<td><a>이메일</a> <span><font color="red">*</font></span></td>
									<td width="550"><input type="email" name="email"
										size="20" placeholder="예:example@naver.com" class="inputbox">
									</td>
									<td><button>중복확인</button></td>
								</tr>
								<tr>
									<td><a>휴대폰</a> <span><font color="red">*</font></span></td>
									<td width="550"><input type="text" name="tel"
										size="20" placeholder="숫자만 입력해주세요" class="inputbox"></td>
									<td><span><button>인증번호 받기</button></span></td>
								</tr>
								<tr>
									<td><a>주소</a> <span><font color="red">*</font></span></td>
									<td>
										<button name="addr">주소 검색</button>
										<h5 align="right">배송지에 따라 상품 정보가 달라질 수 있습니다</h5>
									</td>
								</tr>
							</table>
							<table width=800>
								<tr>
									<td><a>성별</a></td>
									<td><input type="radio" value="남"
										name="gender" /><a>남성</a></td>
									<td><input type="radio" value="여" 
										name="gender" /><a>여성</a></td>
									<td><input type="radio" value="선택안함"
										name="성별" /><a>선택안함</a></td>
								</tr>
							</table>
							<table width=800>
								<tr width="550" >
									<td><a>생년월일</a></td>
									<td><input name="birthYear" placeholder="YYYY" type="text"
										class="birthday" value=""></td>
									<td><input name="birthMonth" placeholder="MM" type="text"
										class="birthday" value=""></td>
									<td><input name="birthDay" placeholder="DD" type="text"
										class="birthday" value=""></td>
								</tr>
								<tr>
									<td colspan="6">
										<hr />
									</td>
								</tr>
							</table>
						</div>
						<div style="padding-left: 100px">
							<button type="submit" class="signupbutton">가입하기</button>
						</div>
						
					</form>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</center>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>








