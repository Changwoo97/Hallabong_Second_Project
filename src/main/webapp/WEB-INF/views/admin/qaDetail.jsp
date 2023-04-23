<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>qaDetail</title>
	<link rel="stylesheet" href="${root}/css/admin/detail.css" />
</head>
<body>
	<div id="detail">
		<form action="${root}${path}" method="post">
			<input type="hidden" name="no" value="${no}">
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<c:if test="${bean.no != null}">
					<tr>
						<th>문의번호</th>
						<td>${no}</td>
					</tr>
				</c:if>
				<tr>
					<th>작성자</th>
					<td>${name}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${q_reg_tm}</td>
				</tr>
				<tr>
					<th>문의내용</th>
					<td><textarea name="content" cols="50" rows="25" readonly>${q}</textarea></td>
				</tr>
				<c:if test="${a_reg_tm != null}">
					<th>답변일</th>
					<td>${a_reg_tm}</td>
				</c:if>
				<tr>
					<th>답변내용</th>
					<td><textarea name="content" cols="50" rows="25">${a}</textarea></td>
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