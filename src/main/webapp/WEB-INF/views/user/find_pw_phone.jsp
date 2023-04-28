<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>
	<c:import url="/WEB-INF/views/include/top.jsp" />
	<center>
	<h1>비밀번호 찾기</h1>	
	<form:form action="find_pw_pro" method="post" modelAttribute="findpw" >
		<div>
				
			<form:label path="id">아이디</form:label>		
			<form:input path="id" placeholder="아이디를 입력해주세요"/>
		</div>
		<div>
			<form:label path="tel">휴대폰번호</form:label>
			<form:input path="tel" placeholder="휴대폰 번호를 입력해주세요"/>
		</div>
		<div>
			<form:button>찾기</form:button>
		</div>
		</form:form>
	</center>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
