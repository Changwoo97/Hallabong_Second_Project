<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form action="${root}${path}" method="post">
			<input type="hidden" name="no" value="${no}">
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="${tit}"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" cols="50" rows="25">${cont}</textarea></td>
				</tr>
				<tr>
					<td class="detailBottom" colspan="2">
						<input type="submit" value="${submit}" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>