<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>택배</title>
	<link rel="stylesheet" href="${root}/css/post/post.css" />
</head>
<body>
	<table id="frame">
		<tr id="header">
			<th></th>
			<th class="headerCell">
				<a href="${root}/post/wait">배송대기</a>
			</th>
			<th class="headerCell">
				<a href="${root}/post/process">배송중</a>
			</th>
			<th class="headerCell">
				<a href="${root}/post/complete">배송완료</a>
			</th>
			<th></th>
		</tr>
		<tr>
			<td class="contentCell" colspan="5">
				<table>
					<thead>
						<tr>
						<c:forEach var="cell" items="${thead}">
							<th>${cell}</th>
						</c:forEach>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="row" items="${tbody}">
						<tr>
						<c:forEach var="cell" items="${row}">
							<td>${cell}</td>
						</c:forEach>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>	
	</table>
</body>
</html>