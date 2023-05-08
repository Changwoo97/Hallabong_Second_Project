<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Orders</title>
	<link rel="stylesheet" href="${root}css/mypage.css" />
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style>
		.cartList {
		margin: 20px 0px 0px;
		width: 100%;
		border-collapse: collapse;
		}
		
		.cartList th, .cartList td {
		padding: 8px;
		text-align: center;
		}
		.cartList th {
		background-color: #f9d9a1;
		font-weight: bold;
		}
		.cartList td {
		border: 1px solid #ddd;
		}
		.cartPage{
		margin: 0px 200px 0px; 
		}
		.cart-main{
		margin: 0 auto;
		}
		
		.cart-select{
		text-align: right;
		
		}
		
		.mypage{
		width: 100%;
		text-align: center;
   		margin: 0px 0px 0px;
		}

		
		.pay-step{
		width: 100%;
		border-collapse: collapse;
		margin-top: 20px;
		}
		
		.pay-step-button{
		text-align: left;
		}
		
		.mypahe-button {
	    display: inline-block;
	    margin-right: 10px;
	    padding: 5px 10px;
	    border: 0px;
	    cursor: pointer;
	    border-radius: 0.25rem;
		}
		
		.product-img{
		width: 100px;
		}
		
		span a{
		color: orange;
		}
	</style>
</head>
	<body>
		<!-- 상단 타이틀 -->
		<c:import url="/WEB-INF/views/include/top.jsp" />
		
		   <section class="cartPage">	
   		<div class="mypage">
			<article class="pay-step">
				<div class="pay-step-button">
					<span class="mypahe-button">
						<h2><a href="${root}mypage/cart">장바구니</a></h2>
					</span>
					<span class="mypahe-button">
						<h2>주문결제</h2>
					</span>
					<span class="mypahe-button">
						<h2>문의내역</h2>
					</span>
					<span class="mypahe-button" style="background: #f9d9a1">
						<h2>주문내역</h2>
					</span>
				</div>
				<hr style="height: 2px; background: #000000; width: 100%; margin-top: 0px" />
			</article>
		</div>
		<article class="cart-main">
 			<c:forEach var="pair" items="${ordList}">
			<table class="cartList">
				<thead>
					<tr>
						<td colspan="6" style="text-align: left">${pair.item1['ord_no']} (${pair.item1['reg_tm']})</td>
					</tr>
					<tr>
						<th colspan="2">상품</th>
						<th>가격</th>
						<th>수량</th>
						<th>배송상태</th>
						<th>리뷰</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="map" items="${pair.item2}">
					<tr>
						<td><img src="${root}upload/${map['s_img']}" style="width: 100px"/></td>
						<td>${map['name']}</td>
						<td>${map['sp']}</td>
						<td>${map['qnty']}</td>
						<td>${map['dlvy']}</td>
						<td>
							<form action="${root}" method="post">
								<input type="hidden" name="no" value="${map['no']}"/>
								<input type="submit" value="리뷰쓰기"/>
							</form>
						</td>
					</tr>	
					</c:forEach>
				</tbody>
			</table>
			</c:forEach> 
		</article>
   </section>

	<!-- 하단타이틀 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />	
	</body>
</html>

			 