<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>   
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${root}css/mypage.css" />
<link rel="stylesheet"   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script type="text/javascript" src="${root}js/mypage/cart.js"></script>
	
	<style>
		.cartList {
		margin: 20px 0px 0px;
		width: 100%;
		border-collapse: collapse;
		}
		
		.cartList th, .cartList td {
		padding: 8px;
		text-align: center;
		}
		.cartList th {
		background-color: #f9d9a1;
		font-weight: bold;
		}
		.cartList td {
		border: 1px solid #ddd;
		}
		.cartPage{
		margin: 0px 200px 0px; 
		}
		.cart-main{
		margin: 0 auto;
		}
		
		.cart-select{
		text-align: right;
		
		}
		
		.mypage{
		width: 100%;
		text-align: center;
   		margin: 0px 0px 0px;
		}

		
		.pay-step{
		width: 100%;
		border-collapse: collapse;
		margin-top: 20px;
		}
		
		.pay-step-button{
		text-align: left;
		}
		
		.mypahe-button {
	    display: inline-block;
	    margin-right: 10px;
	    padding: 5px 10px;
	    border: 0px;
	    cursor: pointer;
	    border-radius: 0.25rem;
		}
		
		.product-img{
		width: 100px;
		}
		
		span a{
		color: orange;
		}
		
		
	</style>
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	</header>

   <section class="cartPage">	
   
   		<div class="mypage">
			<article class="pay-step">

				<div class="pay-step-button">
					<span class="mypahe-button" style="background: #f9d9a1">
						<h2>장바구니</h2>
					</span>
					<span class="mypahe-button">
						<h2>주문결제</h2>
					</span>
					<span class="mypahe-button">
						<h2>문의내역</h2>
					</span>
					<span class="mypahe-button">
						<a href="${root }mypage/Orders?cust_id=아이디"><h2>주문내역</h2></a>
					</span>
				</div>
				<hr style="height: 2px; background: #000000; width: 100%; margin-top: 0px" />
			</article>
		</div>
   		${loginCustBean.id}님의 주문 내역
		<article class="cart-main">
			<div class="cart-select">
               <button class="btn btn-primary" onclick="selectAll()">전체 선택</button>
               <button class="btn btn-danger" onclick="deleteSelected()">선택 삭제</button>
               <button class="btn btn-danger" onclick="ordersSelected()">선택 주문</button>
			</div>

			<form id="form" method="post">
			<table class="cartList">
				<thead>
					<tr>
						<th>선택</th>
						<th class="product-img">상품이미지</th>
						<th>상품이름</th>
						<th>가격</th>
						<th>수량</th>
					</tr>

				</thead>
				<tbody>			    
            		<c:forEach var="obj" items="${cartList}">
					<tr>
						<td>
							<input type="checkbox" name="cartList" value="${obj.prod_no}">
						</td>
						<td></td>
						<td>상품이름</td>
						<td>가격</td>
						<td>${obj.qnty}개</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</form>  
			
			
		</article>
		
   </section>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   <!-- 마이페이지 내용 전체를 감싸는 div -->
   <div class="mypage-wrapper">
   

   
   
   
   
   
   
   
   
   
   
   
   <!-- 왼쪽 리스트 -->
    <div class="mypage-nav">
        <ul>
            <li><a href="${root }mypage/Orders?cust_id=아이디">주문내역</a></li>
            <li><a href="${root }mypage/cart?prod_no=제품번호">장바구니</a></li>
            <li><a href="${root }mypage/QAList?cust_id=아이디">문의내역</a></li>
        </ul>
    </div>
    
     <!-- 오른쪽 내용 -->
      <div class="mypage-content">
               <table>
               <tr height="30">
                  <td colspan="3" align="left">
                     <h2>장바구니</h2>
                  </td>
               </tr>
               <tr>
                  <td>
                     <hr style="height: 2px; background: #000000; width: 930px;" />
                  </td>
               </tr>
            </table>
             <!-- 전체 선택, 삭제 버튼 추가 -->
             <div class="text-right" style="height: 60px;">
               <button class="btn btn-primary" onclick="selectAll()">전체 선택</button>
               <button class="btn btn-danger" onclick="deleteSelected()">선택 삭제</button>
               <button class="btn btn-danger" onclick="ordersSelected()">선택 주문</button>
             </div>   
            <form id="form" method="post">           
            <c:forEach var="obj" items="${cartList}">
               <div class="col-sm-3" ></div>
                     <div class="col-sm-6">
                        <div class="card shadow" style="width: 900px">
                           <div class="card-body">
                           <div>
			                <input type="checkbox" name="cartList" value="${obj.prod_no}">
			            </div>
                           <div>
                               <table style="width: 850px;">
                                 <tr height="40">
                                    <td width="200" rowspan="4"> <img src=""> </td>
                                    <td width="330" align="left" >제품 번호 : ${obj.prod_no}</td>
                                    <td align="right" colspan="2"></td>
                                    
                                 </tr>
                                 <tr height="40">
                                    <td align="left">아이디 : ${obj.cust_id}</td>   
                                    <td/>
                                 </tr>
                                 <tr height="40">
                                    <td />
                                    <td colspan="2" align="right" >수량 : ${obj.qnty}개</td>
                                 </tr>                               
                              </table>
                                                  
                           </div>   
                        </div>
                     </div>
                  </div>
        
              </c:forEach>
                <input type="submit" value="결제하기">         
         </form>  
         
        
                   
      </div>
   </div>   
   <!-- 하단타이틀 -->
   <c:import url="/WEB-INF/views/include/bottom.jsp" />      
   </body>
</html>
   