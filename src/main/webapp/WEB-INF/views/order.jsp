<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문결제</title>
<link type="text/css" rel="stylesheet" href="${root}css/mainPage.css" />
<script type="text/javascript"
   src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${root}js/order.js"></script>
<script
   src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<style type="text/css">

.inputbox {
         width: 100%;
         height: 46px;
         padding: 0 11px 1px 15px;
         border-radius: 4px;
         border: 1px solid #ddd;
         font-weight: 400;
         font-size: 16px;
         line-height: 1.5;
         color: #333;
         outline: none;
         box-sizing: border-box;
      } 
.btnLogin {
   background-color: display: block;
   padding: 0px 10px;
   text-align: center;
   overflow: hidden;
   width: 300px;
   height: 54px;
   border-radius: 3px;
   color: rgb(, 0, 0);
   background-color: #F7D358;
   border: 0px none;
}
</style>
<script>
   window.onload = function() {
      document.getElementById("recv_addr").addEventListener("click", function() { //주소입력칸을 클릭하면
         //카카오 지도 발생
         new daum.Postcode({
            oncomplete : function(data) { //선택시 입력값 세팅
               document.getElementById("recv_addr").value = data.address; // 주소 넣기
               document.querySelector("input[name=recv_addr_detail]").focus(); //상세입력 포커싱
            }
         }).open();
      });
   }
</script>
<!--    <script>
function requestPay() {
  IMP.init("imp25054182"); //iamport 대신 자신의 "가맹점 식별코드"를 사용
  IMP.request_pay({
    pg: "html5_inicis",
    pay_method: "card",
    merchant_uid : 'merchant_'+new Date().getTime(),
    name : '결제테스트',
    amount : 100,
    buyer_email : 'iamport@siot.do',
    buyer_name : '백유기',
    buyer_tel : '010-1234-5678',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456'
  }, function (rsp) {
       console.log(rsp);
       if (rsp.success) {
         var msg = '결제가 완료되었습니다.';
         alert(msg);
         location.href = "${root}order/order_proc"
       } else {
         var msg = '결제에 실패하였습니다.';
         msg += '에러내용 : ' + rsp.error_msg;
         alert(msg);
    } 
  });
 }
  
</script> -->
</head>
<body>
   <jsp:include page="/WEB-INF/views/include/top.jsp"></jsp:include>
   <div style="text-align: center">
      <form:form action="${root}order/order_proc" method="post" modelAttribute="ord">
         <h2>결제하기</h2>
         <hr class="hr1" />
         <div style="display: none">
            <c:forEach var="pair" items="${prodList}">
               <input type="checkbox" name="prodList" value="${pair.item1.no}" checked="checked">
            </c:forEach>
         </div>
         <table style="width: 800px; margin-left: auto; margin-right: auto; padding-top: 50px">
            <tr>
               <td style="margin-bottom: 10px">주문상품: 총${prodList.size()}개</td>
            </tr>
            <tbody>
               <c:set var="sum" value="0" />
               <c:forEach var="pair" items="${prodList}">
                  <tr>
                     <c:set var="prod" value="${pair.item1}" />
                     <c:set var="qnty" value="${pair.item2}" />
                     <td>${prod.no}</td>
                     <td>${prod.name}</td>
                     <td>${qnty}개</td>
                     <td>${prod.sp}원</td>
                  </tr>
                  <c:set var="sum" value="${sum + (prod.sp * qnty)}" />
               </c:forEach>
               <tr>
                  <td></td>
                  <td></td>
                  <td>합계</td>
                  <td>${sum}원</td>
               </tr>
            </tbody>
         </table>

         <table style="width: 800px; margin-left: auto; margin-right: auto">
            <tr>
               <td>주문인 이름</td>
               <td colspan="3"><form:input path="ordr_name" readonly="true" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>주문인 전화번호</td>
               <td><form:input path="ordr_tel" readonly="true" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>주문인 주소</td>
               <td><form:input path="ordr_addr" readonly="true" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td colspan="2" align="left">
                  <input type="checkbox" id="recv_check" /> 
                  <span>주문인과 수령인 동일</span>
               </td>
            </tr>
            <tr>
               <td>수령인 이름</td>
               <td><form:input path="recv_name" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>수령인 전화번호</td>
               <td><form:input path="recv_tel" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>수령인 주소</td>
               <td><form:input path="recv_addr" size="50" readonly="true" class="inputbox"/></td>
            </tr>
            <tr>
               <td>수령인 상세 주소</td>
               <td><form:input path="recv_addr_detail" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>배송료</td>
               <td><form:input path="dlvy_fee" readonly="true" size="50" class="inputbox"/></td>
            </tr>
            <tr>
               <td>결제수단</td>
               <td>
                  <form:select path="pay_meth"  class="inputbox">
                     <form:option value="credit_card">신용카드</form:option>
                  </form:select>
               </td>
            </tr>
         </table>
         <button class="btnLogin" style="background-color: #F7D358; border-style: none; color: black; margin-top: 20px " >결제하기</button>
      </form:form>
   </div>

   <jsp:include page="/WEB-INF/views/include/bottom.jsp"></jsp:include>
</body>
</html>