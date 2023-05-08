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
	            <li><a href="${root }mypage/Orders?cust_id=${cust_id}">주문내역</a></li>
	            <li><a href="${root }mypage/cart?prod_no=${prod_no}">장바구니</a></li>
	            <li><a href="${root }mypage/QAList?cust_id=${cust_id}">문의내역</a></li>
	        </ul>
	    </div>
	    
	       <!-- 오른쪽 내용 -->
    <div class="mypage-content">          		
			
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
			<form action="${root}mypage/QAwrite_pro" method="post">	
				<div class="text-right">
	            	<input type="submit" value="문의하기" />
	        	 </div>		
				 <div class="col-sm-3" ></div>
			     	 <div class="col-sm-6">
			        	 <div class="card shadow" style="width: 900px">
				            <div class="card-body">
								<div>
							 		<table>
									
										<tr height="50px;">
											<td align="left" style="width: 100px;" >질문</td>		
										</tr>
										<tr>
											<td align="center" style="width: 800px;" >
												<textarea name="content_text" class="form-control" row="100" style="resize:none"></textarea>
											</td>
										</tr>
									</table>
								</div>	
							</div>
						</div>
					</div>							
				</form>			
			</div>
		</div>
	
		
		<!-- 하단타이틀 -->
		<c:import url="/WEB-INF/views/include/bottom.jsp" />
	</body>
</html>