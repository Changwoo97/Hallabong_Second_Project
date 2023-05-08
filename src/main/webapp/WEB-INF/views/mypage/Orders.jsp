<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Orders</title>
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
		            <li><a href="${root }mypage/cart?prod_no=${prod_no}">장바구니</a></li>
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
				<c:forEach var="obj" items="${OrdList}">	
			      <div class="col-sm-3" ></div>
			     	 <div class="col-sm-6">
			         	<div class="card shadow" style="width: 900px">
			          	  <div class="card-body">
					 		<div>					 		
						 		<table>						 		
									<tr height="40">
										<td rowspan="8" width="200"><img src="" alt="" /></td>
										<td align="left" width="350">주문번호 : ${obj.no}</td>
										<td align="right" width="350"></td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 성함 : ${obj.ordr_name}</td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 전화번호 : ${obj.ordr_tel} </td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">주문인 주소 :${obj.ordr_addr} </td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">배송인 성함 : ${obj.recv_name}</td>
									</tr>
									<tr height="40">
										<td align="left" colspan="2">배송인 전화번호 :${obj.recv_tel} </td>
									</tr>
									<tr height="40">
										<td align="left">배송인 주소 : ${obj.recv_addr}</td>
									</tr>
									<c:forEach var="ord_dtl_list" items="${obj.ord_dtl_list}" varStatus="dtlStatus">
										<tr height="40">
											<td align="left">구매상품[${dtlStatus.count}] : ${ord_dtl_list.prod_name}
												<a href="javascript:void(0);" onclick="fnGoReviewPage(this);" data-ordNo="${ord_dtl_list.ord_no}" data-prodNo="${ord_dtl_list.prod_no}" style="font-family: Noto Sans;${obj.sta eq 'REQUEST' ? 'display:none;' : 'display:block;'}" class="e1bmghrs0 css-1roqgte e4nu7ef3 reviewBtn_${ord_dtl_list.ord_no}">후기작성</a>
											</td>
										</tr>
									</c:forEach>		
									<tr height="40">
										<td align="right" colspan="2">결제 수단 : ${obj.pay_meth}</td>
									</tr>
									<tr height="40">
										<td rowspan="2" align="center" >
											<c:forEach var="com" items="${completeOrder}">
										        <form method="post" action="${root}mypage/Orders_complete">
										          <input type="hidden" name="no" value="${com.no}" />
										          <input type="hidden"  value="주문확정" />
										          <button type="submit" class="btn btn-primary" >주문 확정</button>
										        </form>
										    </c:forEach>									  
										</td>										
										<td align="right" colspan="2">가격 : ${obj.dlvy_fee}</td>
									</tr>
									<tr height="40">
										<td align="left">주문 시간 : ${obj.reg_tm}</td>
										<td align="right">정산 시간 : ${obj.stlm_tm} </td>
									</tr>
									
								</table>
								<form id="reviewForm" action="${root}mypage/review/form" method="post">
									<input type="hidden" name="ord_no" />
									<input type="hidden" name="prod_no" />
								</form>
								<%-- <a href="${root }board/delete?board_info_idx=${board_info_idx}&content_idx=${content_idx }" style="font-family: Noto Sans" class="e1bmghrs0 css-1roqgte e4nu7ef3">구매 확정</a> --%>
								<a href="javascript:void(0);" onclick="fnConfirmOrd(this);" data-ordNo="${obj.no}" data-ordSta="${obj.sta}" style="font-family: Noto Sans;${obj.sta eq 'REQUEST' ? 'display:block;' : 'display:none;'}" class="e1bmghrs0 css-1roqgte e4nu7ef3">구매확정</a>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

	// 구매확정버튼
	function fnConfirmOrd(obj){
		var ordSta = $(obj).data("ordsta");
		var ordNo = $(obj).data("ordno");
		if("REQUEST" == ordSta){// 주문완료상태 이상이면 후기를 
			if (confirm("구매확정 처리하시겠습니까?")) {
				// 구매확정처리 ajax
				
				// 구매확정처리후(success 일때)
				var completeSta = "COMPLETE";    // 임시로 완료값으로 변경.
				$(obj).hide();                   // 구매확정 버튼 숨김 처리
				$(".reviewBtn_" + ordNo).show(); // 후기작성 버튼활성화
			}
		}		
		
	}
	
	// 후기작성버튼
	function fnGoReviewPage(obj){
		$('#reviewForm')[0].reset(); // 폼값 초기화		
		var ordNo = $(obj).data("ordno");
		var prodNo = $(obj).data("prodno");
		$('#reviewForm [name="ord_no"]').val(ordNo);
		$('#reviewForm [name="prod_no"]').val(prodNo);
		$("#reviewForm").submit(); // 리뷰작성화면이동
	}

</script>
		
		
	</body>
</html>

			 