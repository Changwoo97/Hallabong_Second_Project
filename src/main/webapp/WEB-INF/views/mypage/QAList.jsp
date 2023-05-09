<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ProductQuestion</title>
	<link rel="stylesheet" href="${root}css/mypage.css" />
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
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
					<span class="mypahe-button">
						<h2><a href="${root}mypage/cart">장바구니</a></h2>
					</span> 
					<span class="mypahe-button" style="background: #f9d9a1">
						<h2>문의내역</h2>
					</span>
				</div>
				<hr style="height: 2px; background: #000000; width: 100%; margin-top: 0px" />
			</article>
		</div>
		<article class="cart-main">
			<div class="cart-select">
               <a href="${root}mypage/QAwrite" class="btn btn-primary" >문의하기</a>
			</div>
			
			<c:forEach var="qa" items="${qaList}">
				<table class="cartList">
					<thead>
						<tr>
							<td colspan="5" style="text-align: left">${qa.no} (${qa.sta eq 'REQUEST' ? '요청' : '완료'})</td>
						</tr>
						<tr>
							<th style="min-width: 50%; width: 50%; max-width: 50%">질문 (${qa.q_reg_tm})</th>
							<th>답변 (${qa.a_reg_tm})</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><textarea rows="10" style="border: 0px; width: 100%; height: 100%" readonly>${qa.q}</textarea></td>
							<td><textarea rows="10" style="border: 0px; width: 100%; height: 100%" readonly>${qa.a}</textarea></td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</article>
	</section>

	<jsp:include page="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>