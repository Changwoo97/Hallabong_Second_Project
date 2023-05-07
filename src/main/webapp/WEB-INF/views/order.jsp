<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>주문결제</title>
	<link type="text/css" rel="stylesheet" href="${root}css/mainPage.css" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${root}js/order.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp"></jsp:include>
	
	<form:form action="${root}order/order_proc" method="post" modelAttribute="ord">
		<div style="display: none">
			<c:forEach var="pair" items="${prodList}">
			<input type="checkbox" name="prodList" value="${pair.item1.no}" checked="checked">
			</c:forEach>
		</div>
		
		<table>
			<tr>
				<td>주문상품: 총${prodList.size()}개</td>
			</tr>
			<tbody>
			<c:set var="sum" value="0" />
			<c:forEach var="pair" items="${prodList}">
				<tr>
				<c:set var="prod" value="${pair.item1}" />
				<c:set var="qnty" value="${pair.item2}" />
					<td>${prod.no}</td>
					<td>${prod.name}</td>
					<td>${qnty}개</td>
					<td>${prod.sp}원</td>
				</tr>
				<c:set var="sum" value="${sum + (prod.sp * qnty)}"/>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td>합계</td>
					<td>${sum}원</td>
				</tr>
			</tbody>
		</table>

		<table>
			<tr>
				<td>주문인 이름</td>
				<td><form:input path="ordr_name" readonly="true"/></td>
			</tr>
			<tr>
				<td>주문인 전화번호</td>
				<td><form:input path="ordr_tel" readonly="true"/></td>
			</tr>
			<tr>
				<td>주문인 주소</td>
				<td><form:input path="ordr_addr" readonly="true"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" id="recv_check" />
					<span>주문인과 수령인 동일</span>
				</td>
			</tr>
			<tr>
				<td>수령인 이름</td>
				<td><form:input path="recv_name" /></td>
			</tr>
			<tr>
				<td>수령인 전화번호</td>
				<td><form:input path="recv_tel" /></td>
			</tr>
			<tr>
				<td>수령인 주소</td>
				<td><form:input path="recv_addr" /></td>
			</tr>
			<tr>
				<td>배송료</td>
				<td><form:input path="dlvy_fee" readonly="true"/></td>
			</tr>
			<tr>
				<td>결제수단</td>
				<td>
					<form:select path="pay_meth">
						<form:option value="credit_card">신용카드</form:option>
					</form:select>
				</td>
			</tr>
		</table>
	<form:button>결제하기</form:button>
	</form:form>
	
	<jsp:include page="/WEB-INF/views/include/bottom.jsp"></jsp:include>
</body>
</html>