<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 상세 페이지</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${root}css/product.css">
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="${root}js/product.js"></script>
	<style>
		@font-face {
	    font-family: 'GangwonEduPowerExtraBoldA';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	    
		}
		
		img{
		border-radius: 0.5rem;
		}
	
		body {
		font-family: 'GangwonEduPowerExtraBoldA';
		}
		section{
   		margin: 0px 200px 0px;
		}
		article{
		margin: 30px;
		}
		
		tr { display: block; float: left; }
		th, td { display: block; 
		margin-bottom: 15px;
		}
		
		main {
	    width: 600px;
	    margin: 0 auto;
	    background-color: #fff;
	    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	    padding: 30px;
	    height: 550px;
	    }
	    
	    #cartButton{
	    	background-color: #f9d9a1;	
	    	color: black;
	    	border-radius: 0.25rem;
	    }
		
		.product-info1{
		margin: 0px 200px 0px;
		}
			
		.product-info{
		width: 100%;
		}
		
		.prod-input{
		width: 70px;
		border-radius: 0.25rem;
		}
		
		section{
			display: flex;
			justify-content: center;
		}
		.product-detail tr th{
			width: 150px;	
		}
		
		.review-detail{
			display: flex;
			justify-content: space-between;
		}
		
		.page-item.active .page-link {
	    z-index: 1;
	    color: #fff;
	    background-color: orange;
		}
		
	</style>
</head>
<body>
	<input type="hidden" id="root" value="${root}"/>
	<input type="hidden" id="custLogin" value="${loginCustBean.custLogin}"/>
   <header>
      <c:import url="/WEB-INF/views/include/top.jsp" />
   </header>
   
	<div class="product-info1">
 			<h4>${prodBean.name} 상품정보</h4> 
	</div>
   	
   <section>	
   		
   		<article>
	   		<div class="product-img">
	   			<img src="${root}upload/${prodBean.s_img}">
	   		</div>
   		</article>
   		
   		<article>
   			<main> 
         		<table class="product-detail">
    
	         		<tr>
	         			<th>상품이름</th>
	         			<th>상품가격</th>
	         			<th>수량</th>
	         		</tr>
	   
	         		<tr style="width: 200px;">
	         			<td>${prodBean.name}</td>
	         			<td>${prodBean.cost}원</td>
	         			<td><input class="prod-input" type="number" id="qnty" name="qnty" value="1" min="1"></td>
	         			<td colspan="2"><button id="cartButton">장바구니에 담기</button></td>
	         		</tr>
      
         	
         		</table>
		<input type="hidden" id="prod_no" name="prod_no" value="${prodBean.no}" />  	
   		<hr />
   		
   		<c:forEach var="review" items="${reviewList}">
	   		<div class="review-detail">
	   			<span>구매자</span>
	   			<span>${review.cust_id}</span>
	   			<span>${review.cont}</span>
	   		</div>
   		</c:forEach>
   		
         <div class="d-none d-md-block">
            <ul class="pagination justify-content-center">
               <c:choose>
                  <c:when test="${pageBean.prevPage <= 0 }">
                     <li class="page-item disabled">
                        <a href="#" class="page-link">이전</a>
                     </li>
                  </c:when>
                  <c:otherwise>
                     <li class="page-item">
                        <a href="${root }prod/product?prod_no=${prod_no}&page=${pageBean.prevPage}" class="page-link">이전</a>
                     </li>
                  </c:otherwise>
               </c:choose>
               
               <c:forEach var='idx' begin="${pageBean.min}" end='${pageBean.max}'>
                  <c:choose>
                     <c:when test="${idx == pageBean.currentPage}">
                        <li class="page-item active">
                           <a href="${root}prod/product?prod_no=${prod_no}&page=${idx}" class="page-link">${idx }</a>
                        </li>
                     </c:when>
                     <c:otherwise>
                        <li class="page-item">
                           <a href="${root}prod/product?prod_no=${prod_no}&page=${idx}" class="page-link">${idx }</a>
                        </li>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
                     
               <c:choose>
                  <c:when test="${pageBean.max >= pageBean.pageCnt }">
                     <li class="page-item disabled">
                        <a href="#" class="page-link">다음</a>
                     </li>
                  </c:when>
                  <c:otherwise>
                     <li class="page-item">
                        <a href="${root}}prod/product?prod_no=${prod_no}&page=${pageBean.nextPage}" class="page-link">다음</a>
                     </li>
                  </c:otherwise>
               </c:choose>
            </ul>
         </div>
<%--          <div class="text-right">
            <a href="${root}board/write?board_info_idx=${board_info_idx}"
               class="btn btn-primary">글쓰기</a>
         </div> --%>
      </main>
  </article>
   </section>
   <footer>
      <c:import url="/WEB-INF/views/include/bottom.jsp"></c:import>
   </footer>
   
 
</body>
</html>