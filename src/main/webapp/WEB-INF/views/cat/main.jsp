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
<!-- <style type="text/css">
.productList {
	margin: 0px 200px 0px;
}

.productList h2 {
	font-size: 24px;
	font-weight: bold;
	margin: 20px 0;
}

.products {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.products th, .products td {
	padding: 8px;
	text-align: center;
}

.products th {
	background-color: #f9d9a1;
	font-weight: bold;
}

.products td {
	border: 1px solid #ddd;
}

.product-no {
	width: 100px;
}

.product-img {
	width: 150px;
}

.product-name {
	width: 250px;
}

.product-price {
	width: 100px;
}

.product-date {
	width: 200px;
}

.product-img img {
	width: 100%;
	height: auto;
	display: block;
}
</style> -->
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/include/top.jsp" />
	</header>
	<section class="productList">
		<h1>상품 리스트</h1>

		<c:import url="/WEB-INF/views/include/filter.jsp" />
		
		
		
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
						<td>${product.category}</td>
						<td class="product-img"><img src="${root}${product.s_img}"
							alt="${product.name} 이미지"></td>
						<td><a href="${root}prod/product?prod_No=${product.no}">${product.name}</a></td>
						<td>${product.sp}원</td>
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