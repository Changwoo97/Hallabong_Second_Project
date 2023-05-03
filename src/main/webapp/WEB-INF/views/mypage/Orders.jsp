<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${root}css/mypage.css" />
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
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
		            <li><a href="${root }mypage/cart?cust_id=아이디">장바구니</a></li>
		            <li><a href="${root }mypage/QAList?cust_id=아이디">문의내역</a></li>
		        </ul>
		    </div>
		    
		   <!-- 오른쪽 내용 -->
		   <div class="mypage-content">
		   <form:form>
			   <table  >
				
					<tr height="30">
						<td  align="left">
							<h2>주문내역</h2>
						</td>
					</tr>
					<tr>
						<td >
							<hr style="height: 2px; background: #000000; width: 930px;" />
						</td>
					</tr>						
				</table>
				<c:forEach var="obj" items="${OrdList }">	
			      <div class="col-sm-3" ></div>
			     	 <div class="col-sm-6">
			         	<div class="card shadow" style="width: 900px">
			          	  <div class="card-body">
					 		<div >
						 		<table>						 		
									<tr height="40">
										<td rowspan="8" width="200"><img src="" alt="" /></td>
										<td align="left" width="350">주문번호 ${obj.no }</td>
										<td align="right" width="350"></td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 성함 : ${obj.ordr_name }</td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 전화번호 : ${obj.ordr_tel} </td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 주소 :${obj.ordr_addr} </td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">배송인 성함 : ${obj.recv_name }</td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">배송인 전화번호 :${obj.recv_tel} </td>
									</tr>
									<tr height="40">
										<td align="left">배송인 주소 : ${obj.recv_addr }</td>
									</tr>				
									<tr height="40">
										<td align="right" colspan="2">결제 수단 : ${obj.pay_meth }</td>
									</tr>
									<tr height="40">
										<td rowspan="2" align="center" >
											<c:forEach var="aa" items="${list}">
												<form action="" method="post">
													<input type="hidden" name="no" value="${aa.no}">
													<input type="submit" value="구매확정"/>
												</form>
											 <button class="e1bmghrs0 css-1roqgte e4nu7ef3" type="button" width="150" height="44" radius="3">
											 
												 <span class="css-ymwvow e4nu7ef1" > 구매 확정 </span>
											 </button>
											 </c:forEach>
										</td>
										<td align="right" colspan="2">가격 : ${obj.dlvy_fee }</td>
									</tr>
									<tr height="40">
										<td align="left">주문 시간 : ${obj.reg_tm }</td>
										<td align="right">정산 시간 : ${obj.stlm_tm } </td>
									</tr>
									
								</table>
								<a href="${root }board/delete?board_info_idx=${board_info_idx}&content_idx=${content_idx }" style="font-family: Noto Sans" class="e1bmghrs0 css-1roqgte e4nu7ef3">구매 확정</a>
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

			 