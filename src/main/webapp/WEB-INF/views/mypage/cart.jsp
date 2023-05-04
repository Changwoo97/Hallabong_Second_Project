
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="${root}css/mypage.css" />
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- 상단 타이틀 -->
	<c:import url="/WEB-INF/views/include/top.jsp" />
	
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
	   		<form:form>
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
				<c:forEach var="obj" items="${cartList }">
					<div class="col-sm-3" ></div>
				     	 <div class="col-sm-6">
				        	 <div class="card shadow" style="width: 900px">
					            <div class="card-body">
									<div>
								 		<table style="width: 850px;">
											<tr height="40">
												<td width="200" rowspan="4"> <img src=""> </td>
												<td width="330" align="left" >제품 번호 : ${obj.prod_no  }</td>
												<td align="right" colspan="2"></td>
												
											</tr>
											<tr height="40">
												<td align="left">아이디 : ${obj.cust_id }</td>	
												<td/>
											</tr>
											<tr height="40">
												<td />
												<td  colspan="2" align="right" >수량 : ${obj.qnty }개</td>
											</tr>	
											<tr height="40">
												<td />
												<td width="300" colspan="2" align="right">주문 시간 : ${obj.reg_tm }</td>
											</tr>										
										</table>
										
										 <!-- 체크박스 추가 -->
					                    <div>
					                        <input type="checkbox" name="cartList" value="${obj.prod_no}">
					                        <label>선택</label>
					                    </div>
                    
										<a href="${root }mypage/delete?prod_no=${obj.prod_no}" class="btn btn-danger" style="margin-left: 730px; height : 40px;">삭제</a>	
										<a href="${root }mypage/orders?prod_no=${obj.prod_no}" class="btn btn-danger">주문</a>						
									</div>	
								</div>
							</div>
						</div>
					</c:forEach>			
			</form:form>				
		</div>
	</div>	
	<!-- 하단타이틀 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />		
	</body>
</html>
	
