<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top_Menu</title>
<link type="text/css" rel="stylesheet" href="${root}css/top.css" />
</head>
<body>
	<div id="header-wrap">

		<c:choose>
			<c:when test="${loginCustBean.custLogin == true}">
			<div class="top1">
				<!-- 로그인 시 -->
				<span><a href="${root }user/modify" class="nav-link">정보수정</a></span>
				<span><a href="${root }user/logout" class="nav-link">로그아웃</a></span>
				<span>${tempLoginUserBean.name }</span>
		</div>
			</c:when>
			<c:otherwise>
				<div class="top1">
					<span><a href="${root }user/join" class="nav-link">회원가입</a></span>
					<span><a href="${root }user/login">로그인</a></span>
					
				</div>
			</c:otherwise>
		</c:choose>

		<div class="logo">
			<div class="logo-title">
				<img src="${root}img/한라봉.png" />
			</div>
			<div class="search">
				<input type="text"> <img
					src="${root}img/free-icon-search-4443186.png">
			</div>
			<div class="logo-icon">
				<span class="logo-icon-detail"><img src="${root}img/로그인.png" /></span>
				<span class="logo-icon-detail"><img src="${root}img/장바구니.png" /></span>
				<span class="logo-icon-detail"><img src="${root}img/장바구니.png" /></span>

			</div>
		</div>


	</div>

</body>
</html>