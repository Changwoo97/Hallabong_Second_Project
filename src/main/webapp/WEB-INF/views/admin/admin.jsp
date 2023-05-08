<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자</title>
	<link rel="stylesheet" href="${root}/css/admin/admin.css" />
	<script type="text/javascript" src="${root}/js/admin/admin.js"></script>
</head>
<body>
	<input type="hidden" id="root" value="${root}"/>
	<div id="header">
		<table>
			<tr>
				<td></td>
				<td class="button"><a href="${root}/admin/logout_proc">로그아웃</a></td>
				<td class="padding"></td>
			</tr>
		</table>
	</div>
	<div id="aside">
		<jsp:include page="/WEB-INF/views/admin/nav.jsp" />
	</div>
	<div id="movingBorder"></div>
	<div id="main">
		<jsp:include page="/WEB-INF/views/admin/frame.jsp" />
	</div>
</body>
</html>