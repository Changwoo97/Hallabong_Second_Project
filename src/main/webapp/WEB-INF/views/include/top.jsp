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
      <div class="logo-title"><img src="img/한라봉.png"/></div>
      <div class="search">
         <input type="text">
         <img src="img/free-icon-search-4443186.png">   
      </div>
      <div class="logo-icon">
         <span class="logo-icon-detail"><img src="img/로그인.png"/></span>   
         <span class="logo-icon-detail"><img src="img/장바구니.png"/></span>   
         <span class="logo-icon-detail"><img src="img/장바구니.png"/></span>
      </div>
   </div>

   
</div>

</body>
</html>