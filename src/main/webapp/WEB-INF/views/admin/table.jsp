<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>table</title>
	<link rel="stylesheet" href="${root}/css/admin/table.css" />
	<script type="text/javascript" src="${root}/js/admin/table.js"></script>
	<c:forEach var="src" items="${srcs}">
		<script type="text/javascript" src="${root}${src}"></script>
	</c:forEach>
</head>
<body>
	<div id="tableMenubar">
		<table>
			<tr> 
				<td class="padding"></td>
				<c:set var="selectedPageBundleNum" value="${Math.floor(selectedPageNum / 5) + 1}" />
				<c:set var="beginPageNum" value="${(selectedPageBundleNum - 1) * 5 + 1}"/>
				<c:set var="endPageNum" value="${selectedPageBundleNum * 5}"/>
				<c:if test="${endPageNum > pageSize}">
					<c:set var="endPageNum" value="${pageSize}"/>
				</c:if>
				<c:if test="${beginPageNum > 5}">
					<td class="pageNum">
						<button value="${beginPageNum - 1}">&lt;</button>
					</td>
				</c:if>
	
				<c:forEach var="i" begin="${beginPageNum}" end="${endPageNum}">
					<td class="pageNum">
						<button name="pageNum" value="${i}">${i}</button>
					</td>
				</c:forEach>
				<c:if test="${endPageNum < pageSize}">
					<td class="pageNum">
						<button value="${endPageNum + 1}">&gt;</button>
					</td>
				</c:if>
				<td></td>
				<td class="right">
					<button class="searchView">검색판</button>
				</td>
				<td class="padding"></td>
			</tr>
		</table>
	</div>
	<div id="tableSearch">
		<table>
			<c:forEach var="cell" items="${thead}">
				<c:if test="${!cell.item2.equals('N')}">
					<tr>
						<th>${cell.item1}</th>
						<td>
							<c:choose>
								<c:when test="${cell.item2.substring(0, 1).equals('D')}">
									<input type="date" name="${cell.item2.substring(2)}BeginDate"/>
									~
									<input type="date" name="${cell.item2.substring(2)}EndDate"/>
								</c:when>
								<c:when test="${cell.item2.substring(0, 1).equals('K')}">
									<input type="text" name="${cell.item2.substring(2)}" />
								</c:when>
							</c:choose>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<td colspan="2">
					<button name="search">검색</button>
				</td>
			</tr>
		</table>
	</div>
	<table id="table">
		<thead>
			<tr>
				<c:forEach var="cell" items="${thead}">
					<th>${cell.item1}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${tbody}">
				<tr>
					<c:forEach var="cell" items="${row}">
						<td>${cell}</td>
					</c:forEach>
				</tr>
			</c:forEach>	
		</tbody>
		<tfoot>
			<tr></tr>
		</tfoot>
	</table>
</body>
</html>