<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인</title>
	<link rel="stylesheet"  href="${root}/css/admin/login.css" />
</head>
<body>
	<form action="${root}/admin/login_proc" method="post">
		<table id="login">
			<tr><td class="top" colspan="3"></td></tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"/></td>
				<td rowspan="2"><input type="submit" value="로그인"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw"/></td>
			</tr>
		</table>
	</form>
</body>
</html>