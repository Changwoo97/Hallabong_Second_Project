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
<%-- <link type="text/css" rel="stylesheet" href="${root}css/UpdateLogin_style.css" /> --%>
<style>
.inputbox {
   width: 100%;
   height: 40px;
   padding: 0px 11px 1px 15px;
   border-radius: 4px;
   border: 1px solid rgb(221, 221, 221);
   font-weight: 400;
   font-size: 16px;
   line-height: 38px;
   color: rgb(51, 51, 51);
   border-style: none;
   box-sizing: border-box;
}

.dob_box {
   height: 44px;
   display: flex;
   -webkit-box-align: center;
   align-items: center;
   width: 100%;
   border: 1px solid rgb(204, 204, 204);
   border-radius: 3px;
   padding: 0px 15px;
}

.check_button {
   background-color: display: block;
   padding: 0px 10px;
   text-align: center;
   overflow: hidden;
   border-radius: 3px;
   color: rgb(0, 0, 0);
   background-color: #F7D358;
   border: 0px none;
}

.aa {
   min-width: 24px;
   min-height: 24px;
   display: inline-block;
   position: relative;
   border-radius: 50%;
   background-color: #F7D358;
}

.hr1 {
   border: 0px;
   border-top: 2px solid black;
   width: 50%;
}


h4 {
   
   font-size: 25px;
   text-align: left;
   width: 50%;
   margin: 30px auto;
    
}

.form-group {
    display: flex;
    margin-bottom: 1rem;
    }

.form-group label{
   width: 100px;
    display: inline-block;
    margin-bottom: auto;
}

.text-field{

   width: 500px;
   height: 46px;
   padding: 0 11px 1px 15px;
   border-radius: 4px;
   border: 1px solid #ddd;
   font-weight: 400;
   font-size: 16px;s
   line-height: 1.5;
   color: #333;
   outline: none;
   box-sizing: border-box;
   
}

.btn {
   display: block;
   padding: 0px 10px;
   text-align: center;
   overflow: hidden;
   width: 300px;
   height: 54px;
   border-radius: 3px;
   color: rgb(95, 0, 128);
   background-color: rgb(255, 255, 255);
   border: 1px solid rgb(95, 0, 128);
   margin-bottom: 20px;
}
 
    
</style>



</head>
<body>
   <c:import url="/WEB-INF/views/include/top.jsp" />
   <h4>개인 정보 수정</h4>
  <p style="text-align: center;">회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</p>
  <hr class="hr1"/>
   
    <!-- <div class="login-form">    -->   
     <form id="chkForm" action="${root}cust/update/form" method="post">
       
       
         <div class="container" style="margin-top: 100px" align="center">
         <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
               <div class="card shadow">
                  <div class="card-body">
                  
                     <div class="form-group" style="text-align: left;">
                         <label>  
                         <input type="text" name="id" id="id" class="text-field" value="${id}" readonly> </label> 
                     </div>
                     <div class="form-group" style="text-align: left;">
                         <label>  
                         <input type="password" name="pw" id="pw" class="text-field" placeholder="현재 비밀번호를 입력하세요"></label>
               <%--          <input type="password" id="now_pwd" name="now_pwd" value="${custInfo.pw}" class="form-control"> --%>
                        <input type="hidden" id="now_chk_pwd" name="now_chk_pwd" value="${custInfo.pw}">
                     </div>
      
                  </div>

                  <div class="btn_wrap">
                     <button type="button" onclick="fnGotoUpdate();" class="btn" style="background-color: #F7D358; border-style: none; color: black;">확인</button>
                     
                  </div>
               </div>
            </div>
         </div>


      </div>



   </form>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script
      src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      <script>

   function fnValidation(){
      //비밀번호 값이 비었을 때 
      var passwd = document.getElementById("pw").value;
      var regExp = /^[A-Za-z0-9]{4,20}$/; 
      //var regExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}/; 문자 대소문자 반드시 입력
      
      if(passwd == ""){
         alert("비밀번호를 입력해주세요.");
         return false;
         
      } else if (!regExp.test(passwd)) {
          //비밀번호 정규표현식(숫자와 영문대소문자 포함 형태의 4~20자리 이내의 암호 정규식-필수 아님)
           alert("비밀번호는 영문, 숫자를 허용하며 4자리에서 20자리 이내로 입력하세요."); 
           return false;
       }  
      
      return true;
   }      
   
   
   function fnGotoUpdate(){
	      if(fnValidation()){ 
	         // 아이디 / 패스워드 일치시 개인정보 수정 페이지 이동
	         var params = $("#chkForm").serialize();// 아래의 주석과 동일한 형태로 전송됨.
	         //var chkForm = {
	         //      custId: $("#custId").val()
	           //        , passwd : $("#passwd").val()
	           //}
	            console.log("params=" + params);
	            $.ajax({
	                type: "post",
	                url: "${root}cust/login/check",
	                data: params,
	                dataType: 'json',
	                success: function (data) {
	                   var sdata = JSON.parse(JSON.stringify(data));
	                    console.log("success_data_origin= " + data);
	                    console.log("success_data_str= " + JSON.stringify(data));// Object로 받아온 것을 문자열의 형태로 풀어서 보여주는 것
	                if(sdata.id.length > 0){
	                       // update 페이지로 이동
	                       // $("#updateForm #cust_idx").val(custId);
	                       $("#chkForm").submit();
	                    } else {
	                       alert("비밀번호를 다시 확인해주시기 바랍니다.");
	                    } 
	                },
	                error: function (request, status, error) {                   
	                    console.log("error= code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	                }
	            });
	      }
	   }
</script>
<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>   