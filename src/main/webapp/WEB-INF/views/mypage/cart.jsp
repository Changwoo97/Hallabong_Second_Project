<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${root}css/mypage.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script type="text/javascript" src="${root}js/mypage/cart.js"></script>
</head>
<body>
	<input type="hidden" id="root" value="${root}"/>
	
	<header>
		<jsp:include page="/WEB-INF/views/include/top.jsp"/>
	</header>

   <section class="cartPage">	
   		<div class="mypage">
			<article class="pay-step">
				<div class="pay-step-button">
					<span class="mypahe-button">
						<h2><a href="${root}mypage/Orders">주문내역</a></h2>
					</span>
					<span class="mypahe-button" style="background: #f9d9a1">
						<h2>장바구니</h2>
					</span>
					<span class="mypahe-button">
						<h2><a href="${root}mypage/QAList">문의내역</a></h2>
					</span>
				</div>
				<hr style="height: 2px; background: #000000; width: 100%; margin-top: 0px" />
			</article>
		</div>
		<article class="cart-main">
			<div class="cart-select">
               <button class="btn btn-primary" onclick="selectAll()">전체 선택</button>
               <button class="btn btn-danger" onclick="deleteSelected()">선택 삭제</button>
               <button class="btn btn-danger" onclick="ordersSelected()">선택 주문</button>
			</div>

			<form id="form" method="post">
				<table class="cartList">
					<thead>
						<tr>
							<th>선택</th>
							<th colspan="2">상품</th>
							<th>가격</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody>			    
	            		<c:forEach var="map" items="${cartList}">
						<tr>
							<td><input type="checkbox" name="cartList" value="${map['no']}"></td>
							<td><img src="${root}upload/${map['s_img']}" style="width: 100px" /></td>
							<td>${map['name']}</td>
							<td>${map['sp']}원</td>
							<td>${map['qnty']}개</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>  
		</article>
   </section>
   <jsp:include page="/WEB-INF/views/include/bottom.jsp"/>      
   </body>
</html>
   