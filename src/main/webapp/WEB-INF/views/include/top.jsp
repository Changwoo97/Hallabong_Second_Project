<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top_Menu</title>
	<link type="text/css" rel="stylesheet" href="${root}css/top.css" />
	<script>
		function checkSearch() {
			var keyword = document.getElementsByName('name')[0].value.trim();
			if (keyword.length < 2) { 
			    alert('두 글자 이상의 검색어를 입력해주세요.');
				return false;
			}
			return true;
		}
	</script> 
</head>
<body>
	<div id="header-wrap">
		<div class="top1">
		<c:choose>
		<c:when test="${loginCustBean.custLogin}">
		<span>${loginCustBean.id}님</span>
		<a href="${root}mypage/mypage"><span>마이페이지</span></a>
		<a href="${root}cust/logout"><span>로그아웃</span></a>
		<a href="${root}cust/update_login/form"><span>임시회원정보수정</span></a>
		</c:when>
		<c:otherwise>
	    <a href="${root}cust/login"><span>로그인</span></a>
	    <a href="${root}cust/join"><span>회원가입</span></a>
		</c:otherwise>
		</c:choose>
		</div>
	   <div class="logo">
	      <div class="logo-title">
	      	<a href="${root}" >
	      		<img src="${root}img/한라봉.png"/>
	      	</a>
	      </div>
	      <div>
	      	<form action="${root}prod/search" class="search" method="get" onsubmit="return checkSearch()">
				<!-- <label for="name">상품명 검색</label> -->
				<input type="text" name="name" />
				<button type="submit">
					 <img src="${root}img/free-icon-search-4443186.png">
				</button>
			</form>	
	    </div>
	      
	      <div class="logo-icon">
	         <span class="logo-icon-detail"><img src="${root}img/로그인.png"/></span>   
	         <span class="logo-icon-detail"><img src="${root}img/장바구니.png"/></span>   
	         <span class="logo-icon-detail"><img src="${root}img/장바구니.png"/></span>
	      </div>
	   </div>  
	</div>

	<%-- <div id="header-wrap">
	
	<c:if test = "${kakaonickname != null}">
 
		${kakaonickname}님이 로그인 하셨습니다.<br><br>
		 
		<form action = "kakao_logout.do" method = "post">
		<button type = "submit" name = "submit">로그아웃</button></form><br><br>
		 
		<form action = "authentication.do" method = "post">
		<button type = "submit" name = "submit">회원 인증하기 (인증을 해야 각종 기능들 사용 가능)</button></form><br><br>
		 
		<form action = "kakao_member_profile.do" method = "post">
		<button type = "submit" name = "submit">나의 프로필 확인</button></form><br><br>
		 
	</c:if>  
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
 --%>
</body>
</html>