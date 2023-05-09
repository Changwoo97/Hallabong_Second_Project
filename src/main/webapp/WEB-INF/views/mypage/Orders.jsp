<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>주문내역</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${root}css/mypage/orders.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 상단 타이틀 -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />

	<section class="cartPage">
		<div class="mypage">
			<article class="pay-step">
				<div class="pay-step-button">
					<span class="mypahe-button" style="background: #f9d9a1">
						<h2>주문내역</h2>
					</span> 
					<span class="mypahe-button">
						<h2><a href="${root}mypage/cart">장바구니</a></h2>
					</span> 
					<span class="mypahe-button">
						<h2><a href="${root}mypage/QAList">문의내역</a></h2>
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
							<td colspan="6" style="text-align: left">${pair.item1['ord_no']}
								(${pair.item1['reg_tm']})</td>
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
								<td><img src="${root}upload/${map['s_img']}"
									style="width: 100px" /></td>
								<td>${map['name']}</td>
								<td>${map['sp']}</td>
								<td>${map['qnty']}</td>
								<td>${map['dlvy']}</td>
								<td>
									<form action="${root}mypage/review/form" method="post">
										<input type="hidden" name="prod_no" value="${map['no']}" /> 
										<input type="hidden" name="rev_no" value="${map['rev_no']}" /> 
										<input type="submit" value="${!empty map['rev_no'] ? '리뷰수정':'리뷰쓰기'}" />
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
	<jsp:include page="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>

