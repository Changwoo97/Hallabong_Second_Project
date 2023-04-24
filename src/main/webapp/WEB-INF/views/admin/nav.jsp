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
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>공지사항 관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/noti/registration">공지사항 등록</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/noti/check">공지사항 조회</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>상품관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/prod/registration">상품등록</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/prod/check">상품조회</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>카테고리 관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/cat/registration">카테고리 등록</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/cat/check">카테고리 조회</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>배송관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/dlvy/request">배송요청</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/dlvy/ready">배송준비</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/dlvy/wait">배송대기</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/dlvy/process">배송중</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>	
				<span data-href="${root}/admin/dlvy/complete">배송완료</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>반품관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/rtn/request">반품요청</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/rtn/process">반품중</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/rtn/complete">반품완료</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span id="stlm">정산관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/stlm/check">정산조회</span>
			</td>
		</tr>
		<tr><td class="navMargin"></td></tr>
		<tr>
			<td>
				<img src="${root}/img/admin/arrow.png" class="groupBullet"/>
				<span>문의관리</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/qa/request">문의요청</span>
			</td>
		</tr>
		<tr>
			<td class="indent">
				<img src="${root}/img/admin/arrow.png" class="bullet"/>
				<span data-href="${root}/admin/qa/complete">문의완료</span>
			</td>
		</tr>
	</table>
</body>
</html>