<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>제품 검색 결과</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="${root}css/search.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
		    // 카테고리 필터링 버튼 클릭 이벤트 핸들러
		    $('.filter-button').click(function() {
		        // 선택된 버튼 활성화 처리
		        $('.filter-button').removeClass('active');
		        $(this).addClass('active');

		        // 필터링 처리
		        var filter = $(this).data('filter');
		        if (filter === 'all') {
		            $('.products-body tr').show();
		        } else {
		            $('.products-body tr').hide();
		            // 필터링에서 제외할 엘리먼트는 필터링 처리에서 제외함
		            $('.products-body tr td:nth-child(1):contains(' + filter + ')').not('[data-filter="exclude"]').parent().show();
		        }
		        // 필터링 처리 후 개수를 다시 계산하여 HTML 요소에 할당함
		        var numPosts = $('.products-body tr:visible').length;
		        $('.numPosts').text(numPosts);
		    });
		});
	</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/include/top.jsp" />
	</header>

	<section class="productList">
		<div>
			<table class="category-filter">
				<thead>
					<tr>
						<td colspan="5">카테고리</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><button class="filter-button" data-filter="all">전체</button></td>
						<td><button class="filter-button" data-filter="채소">채소</button></td>
						<td><button class="filter-button" data-filter="과일">과일</button></td>
						<td><button class="filter-button" data-filter="육류">육류</button></td>
						<td><button class="filter-button" data-filter="해산물">해산물</button></td>
					</tr>
				</tbody>
			</table>
		</div>

			<p>${numposts}개의 검색결과</p>
			<div>
			<table class="products">
				<thead class="products-head">
					<tr>
						<th>제품분류</th>
						<th>상품이미지</th>
						<th>제품명</th>
						<th>판매가</th>
						<th>상품등록날짜</th>
					</tr>

				</thead>
				<tbody class="products-body">
					<c:forEach var="product" items="${searchProductList}">
						<tr>
							<td>${categoryMap[product.cat_no]}</td>
							<td class="product-img"><img src="${root}upload/${product.s_img }" alt="${product.name} 이미지" style="width: 100px"></td>
							<td><a href="${root}prod/product?prod_no=${product.no}">${product.name}</a></td>
							<td>${product.sp}원</td>
							<td>${product.reg_tm}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer>
		<jsp:include page="/WEB-INF/views/include/bottom.jsp" />
	</footer>
</body>
</html> 
