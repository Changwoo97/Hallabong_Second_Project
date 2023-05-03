<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<title>상품 리스트</title>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${root}css/search.css" />
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/include/top.jsp" />
	</header>
	<section class="productList">
		<h1>상품 리스트</h1>
		
		<table class="category-filter">
				<thead>
					<tr>
						<td colspan="5">카테고리</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<button class="filter-button" type="button" onclick="location.href='${root }cat/main?cat_No=1'">
								채소
							</button>
						</td>
						<td>
							<button class="filter-button" type="button" onclick="location.href='${root }cat/main?cat_No=2'">
								과일
							</button>
						</td>
						<td>
							<button class="filter-button" type="button" onclick="location.href='${root }cat/main?cat_No=3'">
								육류
							</button></td>
						<td>
							<button class="filter-button" type="button" onclick="location.href='${root }cat/main?cat_No=4'">
								헤산물
							</button>
						</td>
					</tr>
				</tbody>
			</table>

		<table class="products">
			<thead>
				<tr>
					<th class="product-no">제품분류</th>
					<th class="product-img">상품이미지</th>
					<th class="product-name">제품명</th>
					<th class="product-price">판매가</th>
					<th class="product-date">상품등록날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${catMainPage}">
					<tr>
						<td>${categoryMap[product.cat_no]}</td>
						<td class="product-img"><img src="${root}${product.s_img}"
							alt="${product.name} 이미지"></td>
						<td><a href="${root}prod/product?prod_No=${product.no}">${product.name}</a></td>
						<td>${product.cost}원</td>
						<td>${product.reg_tm}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/include/bottom_info.jsp" />
	</footer>
</body>
</html>