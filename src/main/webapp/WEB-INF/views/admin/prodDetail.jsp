<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>prodDetail</title>
	<link rel="stylesheet" href="${root}/css/admin/detail.css" />
</head>
<body>
	<div id="detail">
		<form:form action="${root}${path}" method="post" modelAttribute="prodBean">
			<form:hidden path="no" />
			<form:hidden path="reg_tm" />
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<c:if test="${no != null}">
					<tr>
						<th>상품번호</th>
						<td>${no}</td>
					</tr>
				</c:if>
				<tr>
					<th>판매여부</th>
					<td class="detailRadio">
						<form:radiobutton path="fs" value="Y" />
						<form:label path="fs">판매중</form:label>
						<form:radiobutton path="fs" value="N"/>
						<form:label path="fs">판매보류</form:label>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<th>원가</th>
					<td><form:input path="cost" /></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><form:input path="sp" /></td>
				</tr>
				<tr>
					<th>소이미지</th>
					<td></td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<td></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<form:select path="cat_no">
							<form:options items="${cats}" itemLabel="name" itemValue="no" />
						</form:select>
					</td>
				</tr>
				<tr>
					<c:if test="${reg_tm != null}">
						<th>등록일</th>
						<td>${reg_tm}</td>
					</c:if>
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