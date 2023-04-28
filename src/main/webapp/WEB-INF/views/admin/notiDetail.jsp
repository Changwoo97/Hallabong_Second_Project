<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>notiDetail</title>
	<link rel="stylesheet" href="${root}/css/admin/detail.css" />
</head>
<body>
	<div id="detail">
		<form:form action="${root}${path}" method="post" modelAttribute="notiBean">
			<form:hidden path="no" />
			<form:hidden path="reg_tm" />
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<c:if test="${notiBean.no != null}">
				<tr>
					<th>공지번호</th>
					<td>${notiBean.no}</td>
				</tr>	
				</c:if>
				<tr>
					<th>제목</th>
					<td><form:input path="tit" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><form:textarea path="cont" cols="50" rows="25"/></td>
				</tr>
				<c:if test="${notiBean.reg_tm != null}">
				<tr> 
					<th>등록일</th>
					<td>${notiBean.reg_tm}</td>
				</tr>
				</c:if>
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