<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<style>
main {
    max-width: 800px;
    margin: 50px auto;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0,0,0,0.2);
    padding: 30px;
}

main img {
    display: block;
    max-width: 100%;
    margin-bottom: 20px;
}

main h2 {
    font-size: 24px;
    margin-top: 0;
}

main p {
    font-size: 16px;
    line-height: 1.5;
}

main h3 {
    font-size: 20px;
    margin-bottom: 20px;
}

main button {
    display: block;
    background-color: #333;
    color: #fff;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
}

main button:hover {
    background-color: #444;
}
	
</style>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/include/top.jsp" />
	</header>
	<section>
	
	
	
	
	<c:forEach var="product" items="${ProdInfoPage }">
		<main>
			<img src="${root}${product.l_img }">
			<h2>상품 정보</h2>
			<p>${product.name}</p>
			<h3>${product.cost}원</h3>
			<c:forEach var="review" items="${ReviewList }">
				<p>${review.no}</p>
				<h3>${review.cont}</h3>
			</c:forEach>
			<button>구매하기</button>
		</main>
	</c:forEach>	
	</section>
	<footer>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
		<a href="${root}admin">관리자페이지</a>
		
	</footer>
</body>
</html>