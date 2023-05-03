<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${root}css/mainPage.css" />
</head>

<body>
	<header>
		<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	</header>
<div>

</div>
	<nav class="navigator">
		<ul>
			<li class="navigator-link"><a href="#mainNotice">공지사항</a></li>
			<li class="navigator-link"><a href="#mainCategory">카테고리</a></li>
			<li class="navigator-link"><a href="#mainBoard">게시판</a></li>
			<li class="navigator-link"><a href="#mainNew">신상품</a></li>
		</ul>
	</nav>

	<section>
		<article id="banner">
			<div>
				<img src="img/9174a938-1c00-4709-84f7-a41d8858b750.jpg">
			</div>
		</article>
    
		<article>
			<div class="gab"></div>
			<h4 class="card-title2">공지사항</h4>
			<a name="mainNotice"></a>

			<div class="card-body2">
				<table class="table table-hover" id='board_list'>
					<thead>
						<tr>
							<th class="text-center w-25">글번호</th>
							<th>제목</th>
							<th class="text-center w-25 d-none d-xl-table-cell">작성날짜</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-center">5</td>
							<th><a href='#'></a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'></a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'></a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'></a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'></a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
					</tbody>
				</table>
				<a href="board_main.html" class="btn btn-primary">더보기</a>
			</div>
		</article>

		<article id="category">
			<a name="mainCategory"></a>
			<div class="gab"></div>

			<div class="Info">

				<h2>
					<a href="${root }category/main" class="navigator-link">카테고리</a>
				</h2>
			</div>

			<div class="categoryIcon">
				<ul class="catrgoryDetail">
					<li><img src="img/vegetable1.png"></li>
					<li><a href="${root }cat/main?cat_No=1">채소</a></li>
					<li>채소</li>
				</ul>
				<ul class="catrgoryDetail">
					<li><img src="img/basket1.png"></li>
					<li><a href="${root }cat/main?cat_No=2">과일</a></li>
					<li>과일</li>
				</ul>
				<ul class="catrgoryDetail">
					<li><img src="img/meat1.png"></li>
					<li><a href="${root }cat/main?cat_No=3">육류</a></li>
					<li>고기</li>
				</ul>
				<ul>
					<li><img src="img/fish.png"></li>
					<li><a href="${root }cat/main?cat_No=4">해산물</a></li>
					<li>해산물</li>
				</ul>
		</article>

		<article id="newProduct">
			<div class="gab"></div>
			<a name="mainNew"></a>
			<div class="Info">
				<h2>신상품</h2>
			</div>
			<div class="newProduct-list" style="margin-top: 10px">

				<c:forEach var="NewProduct" items="${newProdList}">
					<ul class="newProduct-listDeatil">
						<li><img src="${root}${NewProduct.s_img}"
							alt="${NewProduct.name} 이미지"></li>
						<li><a href="${root}prod/product?prod_No=${NewProduct.no}">${NewProduct.name}</a>
						</li>
						<li>${NewProduct.cost}원</li>
					</ul>

				</c:forEach>
			</div>
		</article>
	</section>

	<footer>
		<c:import url="/WEB-INF/views/include/bottom.jsp"></c:import>
	</footer>
</body>
</html>