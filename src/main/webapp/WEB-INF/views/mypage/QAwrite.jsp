<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>문의작성</title>
	<link rel="stylesheet" href="${root}css/mypage.css" />
	<link rel="stylesheet" href="${root}css/mypage/RevDetail.css">
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body>
	<!-- 상단 타이틀 -->
	<c:import url="/WEB-INF/views/include/top.jsp" />
	
	<h4>문의작성</h4>
	<hr class="hr1" />

	<form id="revform" action="${root}mypage/QAwrite_pro" method="post">
		<input type="hidden" name="prod_no" value="${revInfo.prod_no}">
		
		<div class="container" style="margin-top: 100px" align="center">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class="card shadow">
						<div class="card-body">
							<div class="form-group" style="text-align: left;">
								<label>문의내용</label>
								<textarea name="content_text" rows="10" class="form-control" placeholder="내용을 입력해 주세요.">${revInfo.cont}</textarea>
							</div>
						</div>

						<div class="btn_wrap">
						<c:if test="${empty revInfo.no}">
							<input type="submit" value="문의작성" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;"/>
						</c:if>
						<c:if test="${not empty revInfo.no}">
							<input type="submit" value="문의수정" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;"/>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<!-- 하단타이틀 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>