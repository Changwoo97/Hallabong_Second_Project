<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>catDetail</title>
	<link rel="stylesheet" href="${root}/css/admin/detail.css" />
</head>
<body>
	<div id="detail">
		<form:form action="${root}${path}" method="post" modelAttribute="catBean">
			<form:hidden path="no"/>
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<c:if test="${catBean.no != null}">
					<tr>
						<th>카테고리 번호</th>
						<td>${catBean.no}</td>
					</tr>
				</c:if>
				<tr>
					<th>카테고리 이름</th>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td class="detailBottom" colspan="2">
						<form:button>${submit}</form:button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>