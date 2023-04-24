<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${root}css/mainPage.css" />
</head>

<body>
	<header>
		<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	</header>

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
							<th><a href='#'>5월12일 지구종말</a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'>하기싫어</a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'>귀찮아</a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'>졸려</a></th>
							<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
						</tr>
						<tr>
							<td class="text-center">5</td>
							<th><a href='board_read.html'>집에 가고 싶다</a></th>
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
					<li>채소</li>
					<li>신선하고 맛없는 채소</li>
				</ul>
				<ul class="catrgoryDetail">
					<li><img src="img/basket1.png"></li>
					<li>과일</li>
					<li>당많은 과일</li>
				</ul>
				<ul class="catrgoryDetail">
					<li><img src="img/meat1.png"></li>
					<li>고기</li>
					<li>소화 잘되는 고기</li>
				</ul>
				<ul>
					<li><img src="img/fish.png"></li>
					<li>해산물</li>
					<li>썪은 동태 눈깔</li>
				</ul>
			</div>
		</article>


		<article id="board">
			<div class="gab"></div>
			<a name="mainBoard"></a>
			<div class="Info">
				<h2>게시판</h2>
			</div>
			<div class="container" style="margin-top: 10px">
				<div class="row">
					<div class="col-lg-6" style="margin-top: 20px">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">자유게시판</h4>
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
											<th><a href='${root }board/read'>제목입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>제목입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>제목입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>제목입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>제목입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
									</tbody>
								</table>
								<a href="board_main.html" class="btn btn-primary">더보기</a>
							</div>
						</div>
					</div>

					<div class="col-lg-6" style="margin-top: 20px">
						<div class="card shadow">
							<div class="card-body">
								<h4 class="card-title">문의 게시판</h4>
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
											<th><a href='${root }board/read'>개판입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>소판입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>닭판입니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>맛없어요</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
										<tr>
											<td class="text-center">5</td>
											<th><a href='board_read.html'>고소합니다</a></th>
											<td class="text-center d-none d-xl-table-cell">2018-12-12</td>
										</tr>
									</tbody>
								</table>

								<a href="board_main.html" class="btn btn-primary">더보기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>

		<article id="newProduct">
			<div class="gab"></div>
			<a name="mainNew"></a>

			<div class="Info">
				<h2>신상품</h2>
			</div>
			<div class="newProduct-list" style="margin-top: 10px">

				<ul class="newProduct-listDeatil">
					<li><img src="img/165c1a25-574c-4a69-a49d-4a22da7695b2.jpg"></li>
					<li>[한라봉]인간사료</li>
					<li>7900원</li>
				</ul>


				<ul class="newProduct-listDeatil">
					<li><img src="img/165c1a25-574c-4a69-a49d-4a22da7695b2.jpg"></li>
					<li>[한라봉]인간사료</li>
					<li>7900원</li>
				</ul>


				<ul class="newProduct-listDeatil">
					<li><img src="img/165c1a25-574c-4a69-a49d-4a22da7695b2.jpg"></li>
					<li>[한라봉]인간사료</li>
					<li>7900원</li>
				</ul>


				<ul class="newProduct-listDeatil">
					<li><img src="img/165c1a25-574c-4a69-a49d-4a22da7695b2.jpg"></li>
					<li>[한라봉]인간사료</li>
					<li>7900원</li>
				</ul>
			</div>
		</article>
	</section>
	
	<footer>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
		<a href="${root}admin">관리자페이지</a>
	</footer>
</body>
</html>