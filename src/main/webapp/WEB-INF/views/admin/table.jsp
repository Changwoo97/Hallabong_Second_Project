
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
</head>
<body>
	<div id="tableMenubar">
		<table>
			<tr> 
				<td class="padding"></td>
				<c:set var="pageBundleNum" value="${Mathf.floor(selectedPageNum / 5) + 1}"/>
				<c:set var="beginPageNum" value="${(pageBundleNum - 1) * 5 + 1}"/>
				<c:set var="endPageNum" value="${pageBundleNum * 5}"/>
				<c:if test="${endPageNum > pageSize}">
					<c:set var="endPageNum" value="${pageSize}"/>
				</c:if>
				<c:if test="${beginPageNum > 5}">
					<td class="pageNum">
						<button name="pageButton" value="${beginPageNum - 1}">&lt;</button>
					</td>
				</c:if>
	
				<c:forEach var="i" begin="${beginPageNum}" end="${endPageNum}">
					<td class="pageNum">
						<c:choose>
							<c:when test="${selectedPageNum == i}">
								<button name="pageButton" value="${i}" disabled>${i}</button>
							</c:when>
							<c:otherwise>
								<button name="pageButton" value="${i}">${i}</button>
							</c:otherwise>
						</c:choose>
					</td>
				</c:forEach>
				<c:if test="${endPageNum < pageSize}">
					<td class="pageNum">
						<button name="pageButton" value="${endPageNum + 1}">&gt;</button>
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
		<c:forEach var="searchKeyAndValue" items="${searchKeyAndValues}">
			<input type="hidden" data-name="${searchKeyAndValue.item1}" data-value="${searchKeyAndValue.item2}" class="searchKeyAndValue" />
		</c:forEach>
		<form name="searchForm" action="${root}${searchPath}" method="get">
			<table>
				<c:forEach var="cell" items="${thead}">
					<c:if test="${cell['type'] != null}">
						<tr>
							<th>${cell['title']}</th>
							<td>
								<c:choose>
									<c:when test="${cell['type'].equals('date')}">
										<input type="date" name="${cell['name']}BeginDate" />
										~
										<input type="date" name="${cell['name']}EndDate" />
									</c:when>
									<c:when test="${cell['type'].equals('select')}">
										<select name="${cell['name']}">
											<option value="T">전체</option>
											<c:forEach var="i" begin="1" end="${cell['selectEnd']}">
												<c:set var="selectValueKey" value="selectValue${i}" />
												<c:set var="selectLabelKey" value="selectLabel${i}" />
												<option value="${cell[selectValueKey]}" >${cell[selectLabelKey]}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:when test="${cell['type'].equals('keyword')}">
										<input type="text" name="${cell['name']}"/>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<table id="table">
		<thead>
			<tr>
				<c:forEach var="cell" items="${thead}">
					<th>${cell['title']}</th>
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
			<tr>
				<c:forEach var="cell" items="${tfoot}">
					<th>${cell}</th>
				</c:forEach>
			</tr>
		</tfoot>
	</table>
</body>
</html>