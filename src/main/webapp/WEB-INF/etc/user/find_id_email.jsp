<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.selectbutton_phone {
	height: 48px;
	font-weight: 500;
	font-size: 16px;
	color: rgb(95, 0, 128);
	background-color: transparent;
	border: none;
}

.selectbutton_email {
	height: 48px;
	font-weight: 500;
	font-size: 16px;
	color: rgb(95, 0, 128);
	box-shadow: rgb(95, 0, 128) 0px -2px 0px 0px inset;
	background-color: transparent;
	border: none;
}

.inputbox {
	width: 100%;
	height: 46px;
	padding: 0px 11px 1px 15px;
	border-radius: 4px;
	border: 1px solid rgb(221, 221, 221);
	font-weight: 400;
	font-size: 16px;
	line-height: 1.5;
	color: rgb(51, 51, 51);
	outline: none;
	box-sizing: border-box;
}

a {
	display: inline-block;
	padding: 8px 0px 11px;
	font-size: 14px;
	font-weight: 500;
	line-height: 19px;
	color: rgb(51, 51, 51);
}

.buttonss {
	display: block;
	padding: 0 10px;
	text-align: center;
	overflow: hidden;
	width: 100%;
	height: 52px;
	border-radius: 4px;
	color: #fff;
	background-color: #5f0080;
	border: 0 none;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container" style="margin-top: 100px">
		<center>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<h1>아이디 찾기</h1>
					<table width="300">
						<tr class=" buttons">
							<td align="center"><a href="${root }user/find_id_phone"><button
										name="button" class="selectbutton_phone" value="phone">휴대폰
										인증</button></a></td>
							<td align="center">

								<button name="button" class="selectbutton_email" value="email">이메일
									인증</button>
							</td>
						</tr>
						<tr>
							<td colspan="2"><a>이름</a></td>
						</tr>
						<tr>
							<td colspan="2"><input type="text" name="user_id"
								class="inputbox" placeholder="이름을 입력해주세요"></td>
						</tr>
						<tr>
							<td colspan="2"><a>이메일</a></td>
						</tr>
						<tr>
							<td colspan="2"><input type="email" name="user_email"
								class="inputbox" placeholder="이메일을 입력해주세요"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="submit" class="buttonss">확인</button>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</center>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
