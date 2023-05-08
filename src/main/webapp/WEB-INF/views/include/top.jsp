<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<style>
.logo-icon-detail>img {
   width: 50px;
   height: 50px;
   position: relative
}

</style>
</head>
<body>
   <div id="header-wrap">
      <div class="top1">
         <c:choose>
            <c:when test="${loginCustBean.custLogin}">
               <span>${loginCustBean.id}님</span>
               <a href="${root}mypage/mypage"><span>마이페이지</span></a>
               <a href="${root}cust/update_login/form"><span>회원정보수정</span></a>
               <a href="${root}cust/logout"><span>로그아웃</span></a>
            </c:when>
            <c:otherwise>
               <a href="${root}cust/login"><span>로그인</span></a>
               <a href="${root}cust/join"><span>회원가입</span></a>
            </c:otherwise>
         </c:choose>
      </div>
      <div class="logo">
         <div class="logo-title">
            <a href="${root}"> <img src="${root}img/한라봉.png" />
            </a>
         </div>
         <div>
            <form action="${root}prod/search" class="search" method="get" onsubmit="return checkSearch()">
               <input type="text" name="name" />
               <button type="submit">
                  <img src="${root}img/free-icon-search-4443186.png">
               </button>
            </form>
         </div>
      


      <div class="logo-icon">
         <c:choose>
            <c:when test="${loginCustBean.custLogin}">
            
               <div class="logo-icon-detail">
                  <div class="icon-container">
                     <a href="${root}cust/logout">
                        <img src="${root}img/run.png"
                           onmouseover="this.src='${root}img/run (1).png'"
                           onmouseout="this.src='${root}img/run.png'" />
                     </a>
                     <div class="icon-text">로그아웃</div>
                  </div>
               </div>
               <div class="logo-icon-detail">
                  <div class="icon-container">
                     <a href="${root}mypage/cart">
                        <img src="${root}img/shopping-basket.png"
                           onmouseover="this.src='${root}img/basket.png'"
                           onmouseout="this.src='${root}img/shopping-basket.png'" />
                     </a>
                     <div class="icon-text">장바구니</div>
                  </div>
               </div>
               <div class="logo-icon-detail">
                  <div class="icon-container">
                     <a href="#">
                        <img src="${root}img/customer-service.png"
                           onmouseover="this.src='${root}img/customer-service1.png'"
                           onmouseout="this.src='${root}img/customer-service.png'" />
                     </a>
                  <p class="icon-text">고객센터</p>
                  </div>
               </div>
            </c:when>
            <c:otherwise>
               <div class="logo-icon-detail">
                  <div class="icon-container">
                     <a href="${root}cust/login">
                        <img src="${root}img/logout.png"
                           onmouseover="this.src='${root}img/login.png'"
                           onmouseout="this.src='${root}img/logout.png'" />
                     </a>
                     <p class="icon-text">로그인</p>
                  </div>
               </div>
               <div class="logo-icon-detail">
                  <div class="icon-container">
                     <a href="${root}cust/join">
                        <img src="${root}img/free-icon-pencil-6345518.png"
                           onmouseover="this.src='${root}img/free-icon-report-6345607.png'"
                           onmouseout="this.src='${root}img/free-icon-pencil-6345518.png'" />
                     </a>
                     <p class="icon-text">회원가입</p>
                  </div>
               </div>
               <div class="logo-icon-detail">
               <div class="icon-container">
                  <a href="#">
                  <img src="${root}img/customer-service.png"   
                     onmouseover="this.src='${root}img/customer-service1.png'"
                     onmouseout="this.src='${root}img/customer-service.png'" />
                  </a>
                  <p class="icon-text">고객센터</p>
                  
                  </div>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
      </div>
   </div>
</body>
</html>