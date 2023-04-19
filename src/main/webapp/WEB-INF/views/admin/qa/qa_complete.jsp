<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>문의완료</title>
	<link rel="stylesheet" href="qa/qa.css" />
</head>
<body id="main_body">
	<div id="main_header">
		<span>문의완료</span>
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
					<option value="ord_no">작성자</option>
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
		
		for (int j = 0; j < 6; j++) {
			row.add(i + "-" + j);
		}
		
		table.add(row);
	}
	
	pageContext.setAttribute("table", table);
	%>
	<table id="table">
		<thead>
			<tr>
				<th>작성일</th>
				<th>답변일</th>
				<th>작성자</th>
				<th>문의내용</th>
				<th>답변내용</th>
				<th><input type="submit" value="수정하기"/></th>
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