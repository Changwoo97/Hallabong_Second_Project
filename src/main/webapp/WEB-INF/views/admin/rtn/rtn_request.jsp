<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>반품요청</title>
	<link rel="stylesheet" href="rtn/rtn.css" />
</head>
<body id="main_body">
	<div id="main_header">
		<span>반품요청</span>
	</div>
	<table id="main_menubar">
		<tr>
			<td style="width: 10px; min-width: 10px;"></td>
			<td style="width: 30px; min-width: 30px;">
				<form action="">
					<input type="submit" value="◀"/>
				</form>
			</td>
			<c:forEach var="i" begin="${beginPageNum}" end="${endPageNum}">
				<td style="width: 30px; min-width: 30px;">
					<form action="">
						<input type="submit" value="${i}">
					</form>
				</td>
			</c:forEach>
			<td style="width: 30px; min-width: 30px;">
				<form action="">
					<input type="submit" value="▶">
				</form>
			</td>
			<td></td>
			<td style="width: 120px; min-width: 120px;">
				<select name="keyword_type" style="width: 100%">
					<option value="ord_no">주문번호</option>
					<option value="prod_no">제품번호</option>
					<option value="prod_name">제품명</option>
					<option value="send_name">주문인</option>
					<option value="send_tel">주문인 전화번호</option>
					<option value="send_addr">주문인 주소</option>
					<option value="recv_name">수령인</option>
					<option value="recv_tel">수령인 전화번호</option>
					<option value="recv_addr">수령인 주소</option>
				</select>
			</td>
			<td style="width: 150px; min-width: 150px;">
				<input type="text" name="keyword" value="${keyword}"/>
			</td>
			<td style="width: 40px; min-width: 40px;">
				<input type="submit" value="검색"/>
			</td>
			<td style="width: 10px; min-width: 10px;"></td>
		</tr>
	</table>
	<%
	List<List<String>> table = new ArrayList<>();

	for (int i = 0; i < 100; i++) {
		List<String> row = new ArrayList<>();
		
		for (int j = 0; j < 4; j++) {
			row.add(i + "-" + j);
		}
		
		table.add(row);
	}
	
	pageContext.setAttribute("table", table);
	%>
	<table id="table">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>신청일</th>
				<th>주문번호</th>
				<th>제품번호</th>
				<th>제품명</th>
				<th>수량</th>
				<th>주문인</th>
				<th>주문인 전화번호</th>
				<th>주문인 주소</th>
				<th>수령인</th>
				<th>수령인 전화번호</th>
				<th>수령인 주소</th>
				<th><input type="submit" value="요청확인"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${table}">
				<tr>
					<c:forEach var="cell" items="${row}">
						<td>${cell}</td>
					</c:forEach>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</body>
</html>