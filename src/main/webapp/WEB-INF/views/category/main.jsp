<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 페이지</title>
<link type="text/css" rel="stylesheet"
	href="${root}css/categoryMain.css?after" />
</head>
<body>

	<header>
		<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	</header>

	<section class="author-archive">
		<div class="container">
			<h1>카테고리</h1>
			<input type="radio" id="All" name="categories" value="All" checked>
			<input type="radio" id="vegetable" name="categories"
				value="vegetable"> <input type="radio" id="fruit"
				name="categories" value="fruit"> <input type="radio"
				id="meat" name="categories" value="meat"> <input
				type="radio" id="seafood" name="categories" value="seafood">

			<ol class="filters">
				<li><label for="All">전체</label></li>
				<li><label for="vegetable">채소</label></li>
				<li><label for="fruit">과일</label></li>
				<li><label for="meat">육류</label></li>
				<li><label for="seafood">해산물</label></li>
				<!--<li>
        		<label for="fullPage.js">fullPage.js</label>
      		  </li> -->
			</ol>

			<ol class="posts">
				<li class="post" data-category="vegetable">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1530173860335l0.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="#">채소</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉]양파 1kg </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="vegetable">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/10673e09-cd60-4c05-8eb4-814721fd1797.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">채소</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉]달달 고구마 5kg </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="fruit">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1653038449592l0.jpeg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">과일</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉]참외 1kg </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="fruit">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1653038832359l0.jpeg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">과일</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉] 방울토마토 1kg </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="fruit">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1653038519929l0.jpeg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">과일</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉] 블루마리 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="meat">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1583389061846l0.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">육류</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">소고기</a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="seafood">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/a7b388be-17db-4681-b1d0-b69c14aa3ef6.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">해산물</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">[한라봉]고등어구이 2팩 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="seafood">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/a32be9a9-8aff-4efd-a61d-152ba9cb1115.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">해산물</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">꽃게다짐순살 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="seafood">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/9c7dd054-02ba-4ca4-9358-edbbabcc8102.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">해산물</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">장어구이 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="meat">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1583389183389l0.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">육류</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">채끝살 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="meat">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/1bd68112-6f93-4a23-9416-787de105ed27.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">육류</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">소고기 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>

				<li class="post" data-category="seafood">
					<article>
						<figure>
							<a href="#" target="_blank"> <img
								src="../img/027272ca-e4f7-4af7-bcf5-5f8a408c4150.jpg" alt="">
							</a>
							<figcaption>
								<ol class="post-categories">
									<li><a href="">해산물</a></li>
								</ol>
								<h2 class="post-title">
									<a href="#" target="_blank">새우튀김 </a>
								</h2>
							</figcaption>
						</figure>
					</article>
				</li>
			</ol>
		</div>
	</section>
	
	<footer>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
	</footer>


</body>
</html>