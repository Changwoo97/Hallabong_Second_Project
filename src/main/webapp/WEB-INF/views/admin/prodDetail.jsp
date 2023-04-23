<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form action="${root}${path}" method="post">
			<input type="hidden" name="no" value="${no}">
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
				<c:if test="${no != null}">
					<tr>
						<th>상품번호</th>
						<td><input type="text" name="name" value="${no}" readonly/></td>
					</tr>
				</c:if>
				<tr>
					<th>판매여부</th>
					<td class="detailRadio">
						<input type="radio" name="fs-Y" id="fs" value="Y" checked/>
						<label for="fs-Y">판매중</label>
						<input type="radio" name="fs-N" id="fs" value="N"/>
						<label for="fs-N">판매보류</label>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="name" value="${name}" /></td>
				</tr>
				<tr>
					<th>원가</th>
					<td><input type="number" name="cost" value="${cost}" /></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input type="number" name="sp" value="${sp}" /></td>
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
						<select name="cat_no">
							<c:forEach var="cat" items="${cats}">
								<option value="${cat['no']}">${cat['name']}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<c:if test="${!reg_tm.isEmpty()}">
						<th>등록일</th>
						<td>${reg_tm}</td>
					</c:if>
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