<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Bootstrap CDN -->
<link rel="stylesheet" href="${root}css/mypage.css " />
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ProductQuestion</title>
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
	            <li><a href="${root }mypage/cart?prod_no=${prod_no}">장바구니</a></li>
	            <li><a href="${root }mypage/QAList?cust_id=아이디">문의내역</a></li>
	        </ul>
	    </div>
	    
	       <!-- 오른쪽 내용 -->
    <div class="mypage-content">          		
		<form:form>	
			 <table>  								
				<tr>
					<td  align="left">
						<h2>상품문의</h2>
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height: 2px; background: #000000; width: 930px;" />
					</td>
				</tr>
			</table>
				<c:forEach var="obj" items="${qaList }">
					 <div class="col-sm-3" ></div>
				     	 <div class="col-sm-6">
				        	 <div class="card shadow" style="width: 900px">
					            <div class="card-body">
									<div>
								 		<table>
											<tr >
												<td >Q&A : ${obj.no }</td>
												<td align="right" >아이디 : ${obj.cust_id }</td>
											</tr>
											<tr height="200px;">
												<td align="center" style="width: 100px;" >질문 : </td>
												<td align="center" style="width: 800px;" >${obj.q }</td>
											</tr>
											
											<tr >
												<td />
												<td align="right" >질문 시간 : ${obj.q_reg_tm }</td>						
											</tr>
											<tr height="200px;">
												<td align="center" style=" width: 100px;" >답변 : </td>
												<td align="center">${obj.a }</td>
											</tr>
											<tr/>
											<tr >
												<td />
												<td align="right">답변 시간 : ${obj.a_reg_tm }</td>						
											</tr>
											<tr height="100px;">	
												<td align="center" colspan="3">답변이 완료되었습니다. 문의해주셔서 감사합니다.</td>
												<td/>
											</tr>
										
										</table>
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