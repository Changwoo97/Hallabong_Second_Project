<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top_Menu</title>
<link type="text/css" rel="stylesheet" href="${root}css/top.css" />
</head>
<body>
<div id="header-wrap">
   <div class="top1">
      <span>회원가입</span>
      <span><a href="${root }user/login">로그인</a></span>
      <span>고객센터</span>
   </div>
   
   <div class="logo">
      <div class="logo-title">
      	<a href="${root }" >
      		<img src="${root}img/한라봉.png"/>
      	</a>
      </div>
      <div>
      	<form action="${root }prod/search" class="search" method="get" onsubmit="return checkSearch()">
			<!-- <label for="name">상품명 검색</label> -->
			<input type="text" name="name" />
			<button type="submit">
				 <img src="${root }img/free-icon-search-4443186.png">
			</button>
		</form>
		
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
    </div>
      
      <div class="logo-icon">
         <span class="logo-icon-detail"><img src="${root}img/로그인.png"/></span>   
         <span class="logo-icon-detail"><img src="${root}img/장바구니.png"/></span>   
         <span class="logo-icon-detail"><img src="${root}img/장바구니.png"/></span>
      </div>
   </div>

   
</div>

</body>
</html>