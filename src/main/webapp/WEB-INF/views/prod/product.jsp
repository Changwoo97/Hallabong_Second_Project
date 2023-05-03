<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<style>
main {
   max-width: 800px;
   margin: 50px auto;
   background-color: #fff;
   box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   padding: 30px;
}

main img {
   display: block;
   max-width: 100%;
   margin-bottom: 20px;
}

main h2 {
   font-size: 24px;
   margin-top: 0;
}

main p {
   font-size: 16px;
   line-height: 1.5;
}

main h3 {
   font-size: 20px;
   margin-bottom: 20px;
}

main button {
   display: block;
   background-color: #333;
   color: #fff;
   border: none;
   padding: 10px 20px;
   font-size: 16px;
   cursor: pointer;
}

main button:hover {
   background-color: #444;
}
</style>
<link rel="stylesheet" href="style.css">
</head>
<body>
   <header>
      <c:import url="/WEB-INF/views/include/top.jsp" />
   </header>
   <section>
      <main>
         <img src="${root}upload/${prodBean.s_img}">
         <h2>상품 정보</h2>
         <p>${prodBean.name}</p>
         <h3>${prodBean.cost}원</h3>
         <button>구매하기</button>

         <table>
            <tr>
               <th>구매자</th>
               <th colspan="4">리뷰평</th>
            </tr>
            <c:forEach var="review" items="${reviewList}">
               <tr>
                  <td>${review.no}</td>
                  <td>${review.cust_id}</td>
                  <td>${review.cont}</td>
                  <td><button>수정하기</button></td>
                  <td><button>삭제하기</button></td>
               </tr>
            </c:forEach>
         </table>
         
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
                        <a href="${root }prod/product?prod_no=${prod_no}&page=${pageBean.prevPage}" class="page-link">이전</a>
                     </li>
                  </c:otherwise>
               </c:choose>
               
               <c:forEach var='idx' begin="${pageBean.min}" end='${pageBean.max}'>
                  <c:choose>
                     <c:when test="${idx == pageBean.currentPage}">
                        <li class="page-item active">
                           <a href="${root}prod/product?prod_no=${prod_no}&page=${idx}" class="page-link">${idx }</a>
                        </li>
                     </c:when>
                     <c:otherwise>
                        <li class="page-item">
                           <a href="${root}prod/product?prod_no=${prod_no}&page=${idx}" class="page-link">${idx }</a>
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
                        <a href="${root}}prod/product?prod_no=${prod_no}&page=${pageBean.nextPage}" class="page-link">다음</a>
                     </li>
                  </c:otherwise>
               </c:choose>
            </ul>
         </div>
<%--          <div class="text-right">
            <a href="${root}board/write?board_info_idx=${board_info_idx}"
               class="btn btn-primary">글쓰기</a>
         </div> --%>
      </main>

   </section>
   <footer>
      <c:import url="/WEB-INF/views/include/bottom.jsp"></c:import>
   </footer>
</body>
</html>