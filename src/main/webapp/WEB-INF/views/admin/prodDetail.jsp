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
		<form action="${root}${path}" method="post" >
			<input type="hidden" name="no" value="${no}">
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
						<input type="radio" id="fs_Y" name="fs" value="Y" checked/>
						<label for="fs_Y">판매중</label>
						<input type="radio" id="fs_N" name="fs" value="N"/>
						<label for="fs_N">판매보류</label>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="name" value="${name}"></td>
				</tr>
				<tr>
					<th>원가</th>
					<td><input type="text" name="cost" value="${cost}"></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input type="text" name="sp" value="${sp}"></td>
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
								<option value="${cat.no}" ${cat_no.equals(cat.no) ? "selected" : ""}>${cat.name}</option>
							</c:forEach>
						</select>
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
						<input type="submit" value="${submit}">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>