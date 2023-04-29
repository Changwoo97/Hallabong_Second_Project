<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>qaDetail</title>
	<link rel="stylesheet" href="${root}/css/admin/detail.css" />
</head>
<body>
	<div id="detail">
		<form:form action="${root}${path}" method="post" modelAttribute="qaBean">
			<form:hidden path="no"/>
			<form:hidden path="cust_id"/>
			<form:hidden path="q_reg_tm"/>
			<form:hidden path="a_reg_tm"/>
			<form:hidden path="sta"/>
			<table>
				<tr><td class="detailTop" colspan="2"></td></tr>
					<tr>
						<th>문의번호</th>
						<td>${qaBean.no}</td>
					</tr>
				<tr>
					<th>작성자</th>
					<td>${qaBean.cust_id}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${qaBean.q_reg_tm}</td>
				</tr>
				<tr>
					<th>문의내용</th>
					<td>
						<form:textarea path="q" cols="50" rows="25" readonly="true"></form:textarea>
					</td>
				</tr>
				<c:if test="${showAnswerArea}">
					<c:if test="${qaBean.a_reg_tm != null}">
					<tr>
						<th>답변일</th>
						<td>${qaBean.a_reg_tm}</td>
					</tr>
					</c:if>
					<tr>
						<th>답변내용</th>
						<td>
							<c:choose>
								<c:when test="${isModifyMode}">
									<form:textarea path="a" cols="50" rows="25"></form:textarea>
								</c:when>
								<c:otherwise>
									<form:textarea path="a" cols="50" rows="25" readonly="true"></form:textarea>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="detailBottom" colspan="2">
						<form:button>${submit}</form:button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>