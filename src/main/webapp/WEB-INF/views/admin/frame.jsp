<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="kr.co.hallabong.struct.Pair"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>frame</title>
	<link rel="stylesheet" href="${root}/css/admin/frame.css" />
</head>
<body>
	<div id="frameHeader">
		<span>${frameName}</span>
	</div>
	<div>
		<jsp:include page="${content}"></jsp:include>
	</div>
</body>
</html>