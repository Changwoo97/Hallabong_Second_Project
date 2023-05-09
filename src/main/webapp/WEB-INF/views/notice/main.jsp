<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<style>
a {
    color: orange;
    text-decoration: none;
    background-color: transparent;
    }
    

</style>

</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	</header>

	<div class="container" style="margin-top:100px">
		<div class="card shadow">
			<div class="card-body">
				<h4 class="card-title">공지사항</h4>
				<table class="table table-hover" id='board_list'>
					<thead>
						<tr>
							<th class="text-center d-none d-md-table-cell">글번호</th>
							<th class="w-50">제목</th>
							<th class="text-center d-none d-md-table-cell">작성날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var='noti' items='${NoticeList }'>
						<tr>
							<td class="text-center d-none d-md-table-cell">${noti.no }</td>
							<td>
								<a href='${root }notice/read?noti_no=${noti.no}'>
								${noti.tit }</a>
							</td>
							<td class="text-center d-none d-md-table-cell">${noti.reg_tm }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<div class="d-none d-md-block">
            <ul class="pagination justify-content-center">
               <c:choose>
                  <c:when test="${pageBean.prevPage <= 0 }">
                     <li class="page-item disabled">
                        <a href="#" class="page-link">이전</a>
                     </li>
                  </c:when>
                  <c:otherwise>
                     <li class="page-item">
                        <a href="${root }notice/main?page=${pageBean.prevPage}" class="page-link">이전</a>
                     </li>
                  </c:otherwise>
               </c:choose>
               
               <c:forEach var='idx' begin="${pageBean.min}" end='${pageBean.max}'>
                  <c:choose>
                     <c:when test="${idx == pageBean.currentPage}">
                        <li class="page-item active">
                           <a href="${root}notice/main?page=${idx}" class="page-link">${idx }</a>
                        </li>
                     </c:when>
                     <c:otherwise>
                        <li class="page-item">
                           <a href="${root}notice/main?page=${idx}" class="page-link">${idx }</a>
                        </li>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
                     
               <c:choose>
                  <c:when test="${pageBean.max >= pageBean.pageCnt }">
                     <li class="page-item disabled">
                        <a href="#" class="page-link">다음</a>
                     </li>
                  </c:when>
                  <c:otherwise>
                     <li class="page-item">
                        <a href="${root}notice/main?page=${pageBean.nextPage}" class="page-link">다음</a>
                     </li>
                  </c:otherwise>
               </c:choose>
            </ul>
         </div>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/include/bottom.jsp"></c:import>
	</footer>
</body>
</html>