<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>nav</title>
	<link rel="stylesheet" href="${root}/css/admin/nav.css" />
	<script type="text/javascript" src="${root}/js/admin/nav.js"></script>
</head>
<body>
	<table id="nav">
		<tr>
			<td>
				<span id="notice">공지사항 관리</span>
			</td>
		</tr>
		<tr class="notice">
			<td class="foldable">
				<span>공지사항 등록</span>
			</td>
		</tr>
		<tr class="notice">
			<td class="foldable">
				<span>공지사항 조회</span>
			</td>
		</tr>
		<tr>
			<td>
				<span id="prod">상품관리</span>
			</td>
		</tr>
		<tr class="prod">
			<td class="foldable">
				<span>상품등록</span>
			</td>
		</tr>
		<tr class="prod">
			<td class="foldable">
				<span>상품조회</span>
			</td>
		</tr>
		<tr class="prod">
			<td>
				<span id="dlvy">배송관리</span>
			</td>
		</tr>
		<tr>
			<td class="foldable">
				<span data-href="${root}/admin/dlvy/request">배송요청</span>
			</td>
		</tr>
		<tr class="dlvy">
			<td class="foldable">
				<span data-href="${root}/admin/dlvy/ready">배송준비</span>
			</td>
		</tr>
		<tr class="dlvy">
			<td class="foldable">
				<span data-href="${root}/admin/dlvy/wait">배송대기</span>
			</td>
		</tr>
		<tr class="dlvy">
			<td class="foldable">
				<span data-href="${root}/admin/dlvy/process">배송중</span>
			</td>
		</tr>
		<tr class="dlvy">
			<td class="foldable">
				<span data-href="${root}/admin/dlvy/complete">배송완료</span>
			</td>
		</tr>
		<tr>
			<td>
				<span id="rtn">반품관리</span>
			</td>
		</tr>
		<tr class="rtn">
			<td class="foldable">
				<span data-href="${root}/admin/rtn/request">반품요청</span>
			</td>
		</tr>
		<tr class="rtn">
			<td class="foldable">
				<span data-href="${root}/admin/rtn/process">반품중</span>
			</td>
		</tr>
		<tr class="rtn">
			<td class="foldable">
				<span data-href="${root}/admin/rtn/complete">반품완료</span>
			</td>
		</tr>
		<tr>
			<td>
				<span id="stlm">정산관리</span>
			</td>
		</tr>
		<tr class="stlm">
			<td class="foldable">
				<span data-href="${root}/admin/stlm/search">정산조회</span>
			</td>
		</tr>
		<tr>
			<td>
				<span id="qa">문의관리</span>
			</td>
		</tr>
		<tr class="qa">
			<td class="foldable">
				<span data-href="${root}/admin/qa/request">문의요청</span>
			</td>
		</tr>
		<tr class="qa">
			<td class="foldable">
				<span data-href="${root}/admin/qa/complete">문의완료</span>
			</td>
		</tr>
	</table>
</body>
</html>