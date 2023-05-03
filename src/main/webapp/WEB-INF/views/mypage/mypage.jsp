<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyPage</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="${root}css/mypage.css" />
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

</head>
<body>
<!-- 상단 타이틀 -->
<c:import url="/WEB-INF/views/include/top.jsp" />

<!-- 마이페이지 내용 전체를 감싸는 div -->
<div class="mypage-wrapper">


    <!-- 왼쪽 리스트 -->
    <div class="mypage-nav">
        <ul>
            <li><a href="${root}mypage/Orders?cust_id=${cust_id}">주문내역</a></li>
            <li><a href="${root}mypage/cart?cust_id=${cust_id}">장바구니</a></li>
            <li><a href="${root}mypage/QAList?cust_id=${cust_id}">문의내역</a></li>
        </ul>
    </div>

    <!-- 오른쪽 내용 -->
    <div class="mypage-content">
        <table class="table table-hover" id='board_list'>
            <thead>
               <tr>
                  <th class="text-center d-none d-md-table-cell">고객 아이디</th>
                  <th class="w-50">상품 번호</th>
                  <th class="text-center d-none d-md-table-cell">수량</th>
                  <th class="text-center d-none d-md-table-cell">주문 날짜</th>
               </tr>
            </thead>
			<tbody>
				<c:forEach var="obj" items="${cartList}">
					<tr>
						<td class="text-center d-none d-md-table-cell">${obj.prod_no}</td>
						<td class="text-center d-none d-md-table-cell">${obj.cust_id}</td>
						<td class="text-center d-none d-md-table-cell">${obj.qnty}</td>
						<td class="text-center d-none d-md-table-cell">${obj.reg_tm}</td>
					</tr>
				</c:forEach>
			</tbody>
		 </table>
    </div>
</div>

<!-- 하단타이틀 -->
<c:import url="/WEB-INF/views/include/bottom.jsp" />



</body>
</html>
