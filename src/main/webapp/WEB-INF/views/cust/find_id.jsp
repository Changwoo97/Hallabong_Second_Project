<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>미니 프로젝트</title>
	<!-- Bootstrap CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<style type="text/css">
		.find_button {
			background-color: display: block;
			padding: 0px 10px;
			text-align: center;
			overflow: hidden;
			width: 300px;
			height: 54px;
			border-radius: 3px;
			color: rgb(0, 0, 0);
			background-color: #F7D358;
			border: 0px none;
		
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top.jsp" />
	
	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<div style="text-align: center">
							<h1>아이디 찾기</h1>	
							<form:form action="find_id_pro" method="post" modelAttribute="findid">
								<div class="form-group">
										
									<div>
										<form:input path="name" placeholder="이름을 입력해주세요" class="inputbox"/>
									</div>
								</div>
								<div class="form-group">
									
									<div>
										<form:input path="tel" placeholder="휴대폰 번호를 입력해주세요" class="inputbox"/>
									</div>
								</div>
								<div>
									<form:button class="find_button">아이디 찾기</form:button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
