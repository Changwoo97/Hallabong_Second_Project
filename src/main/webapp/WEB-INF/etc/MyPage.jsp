<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Alfa+Slab+One&family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyPage</title>
</head>
<body>
<%

	String campurl=request.getParameter("orders");

//null처리
if(campurl==null){
	campurl="";
}else{
	if(campurl.equals("1"))
		campurl="Orders.jsp";
	else if(campurl.equals("2"))
		campurl="OrdersDetail.jsp";
	else if(campurl.equals("3"))
		campurl="goodsDetail.jsp";
	else if(campurl.equals("4"))
		campurl="ProductReview.jsp";
	else if(campurl.equals("5"))
		campurl="ProductQuestion.jsp";
	
}

%>


	
<center>
<div width="100%">


</div>
		<table width="1200" style="margin-top: 120px;" >
			<!-- Top 부분 -->
			<tr height="100">
				<td align="center" colspan="2"><jsp:include page="Top.jsp" /></td>
			</tr>
			<!-- Left 부분 -->
			<tr height="500">
				<td align="center" width="250"><jsp:include page="Left.jsp" /></td>
			<!-- Center 부분 -->
				 <td align="center" width="900"><jsp:include page="<%=campurl%>" /></td> 
			</tr>
			
		</table>
	</center>
</body>
</html>