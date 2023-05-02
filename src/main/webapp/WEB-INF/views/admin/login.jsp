<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인</title>
	<link rel="stylesheet"  href="${root}/css/admin/login.css" />
</head>
<body>
	<form:form action="${root}/admin/login_proc" method="post" modelAttribute="tempAdminBean">
		<table id="login">
			<tr><td class="top" colspan="3"></td></tr>
			<tr>
				<td> 
					<table>
						<tr>
							<th>아이디</th>
							<td><form:input path="id"/></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><form:password path="pw"/></td>
						</tr>	
					</table>
				</td>	
				<td><form:button>로그인</form:button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>