<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>한라봉 프로젝트</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${root}css/mypage/RevDetail.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<h4>리뷰작성</h4>
	<hr class="hr1" />

	<form id="revform" action="${root}mypage/review/form_pro" method="post">
		<input type="hidden" name="prod_no" value="${revInfo.prod_no}">
		
		<div class="container" style="margin-top: 100px" align="center">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class="card shadow">
						<div class="card-body">
							<div class="form-group" style="text-align: left;">
								<label>상품명</label> 
								<input type="text" name="prod_nm" value="${revInfo.prod_nm}" readonly class="form-control">
							</div>
							<div class="form-group" style="text-align: left;">
								<label>내용</label>
								<textarea name="cont" class="form-control" placeholder="내용을 입력해 주세요.">${revInfo.cont}</textarea>
							</div>
						</div>

						<div class="btn_wrap">
						<c:if test="${empty revInfo.no}">
							<input type="submit" value="리뷰등록" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;"/>
						</c:if>
						<c:if test="${not empty revInfo.no}">
							<input type="submit" value="리뷰수정" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;"/>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<jsp:include page="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>