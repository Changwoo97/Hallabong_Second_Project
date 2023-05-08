<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한라봉 프로젝트</title>
<style type="text/css">
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




</style>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

</head>
<body>
	 <c:import url="/WEB-INF/views/include/top.jsp" />
   <h4>개인 정보 수정</h4>
   <p style="text-align: center; color: white;">회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</p>
   <hr class="hr1" />

   <form id="contact-form" action="${root }cust/update" method="post">

      <div class="container" style="margin-top: 100px" align="center">
         <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
               <div class="card shadow">
                  <div class="card-body">
                     <div class="form-group" style="text-align: left;">
                        <label>아이디</label>
                         <input type="text" name="cust_id" value="${custInfo.id}" readonly class="form-control" /> 
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>현재 비밀번호</label> 
                        <input type="password" id="now_pwd" name="now_pwd" value="${custInfo.pw}" class="form-control">
                        <input type="hidden" id="now_chk_pwd" name="now_chk_pwd" value="${custInfo.pw}">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>새 비밀번호</label> <input type="password" id="new_pwd" name="new_pwd" class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>새 비밀번호 확인</label>
                        <input type="password" id="new_chk_pwd" name="new_chk_pwd" class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>이름</label> 
                        <input type="text" id="name" name="name" value="${custInfo.name}" class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>주소</label>
                        <%-- <input type="text" id="addr" name="addr" value="${custInfo.addr}"> --%>
                        <input type="text" id="addr" name="addr" value="${custInfo.addr}" readonly class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>상세 주소</label> <input type="text" id="addr_detail" name="addr" class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>이메일</label>
                        <div class="input-group">
                           <input id="email" name="email" type="email" maxlength="64" onchange="fnEmailChange();" placeholder="이메일 중복확인을 해주세요." value="${custInfo.email}" class="form-control" />
                           <div class="input-group-append">
                              <button type="button" id="emailChkBtn" style="border: 1px;" class="btn ${!empty custInfo.email ? 'disabled' : ''}" ${!empty custInfo.email ? 'disabled' : ''} onclick='fnEmailCheck()'>중복확인</button>
                           </div>
                        </div>
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>휴대폰</label> 
                        <input type="text" id="tel" name="tel" value="${custInfo.tel}" class="form-control">
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>성별</label>
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label for="female">
                        <input type='radio' name='gender' id='female' value='F' ${custInfo.gender eq 'F' ? "checked" : ""} class="aa" />여성</label> 
                        <label for="male">
                        <input type='radio' name='gender' id='male'value='M' ${custInfo.gender eq 'M' ? "checked" : ""} class="aa" />남성</label>
                     </div>
                     <div class="form-group" style="text-align: left;">
                        <label>생년월일</label>
                        <div class="dob_box">
                           <input id="dob_year" class="inputbox" placeholder="yyyy" /> 
                           / 
                           <input id="dob_month" class="inputbox" placeholder="mm" />
                            / 
                            <input id="dob_day" class="inputbox" placeholder="dd" />
                        </div>
                     </div>
                  </div>


                  <div class="btn_wrap">
                     <a href="javascript:void(0);" onclick="fnDeleteConfirm();" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;">탈퇴하기</a>
                     <a href="javascript:void(0);" onclick="fnChangeMemberInfo();" class="btn btn-primary" style="background-color: #F7D358; border-style: none; color: black;">회원정보수정</a>
                  </div>
               </div>
            </div>
         </div>


      </div>



   </form>

   <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script
      src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script>
      //주소 API
      window.onload = function() {
         document
               .getElementById("addr")
               .addEventListener(
                     "click",
                     function() { //주소입력칸을 클릭하면 지도 발생

                        new daum.Postcode(
                              {
                                 oncomplete : function(data) { //선택시 입력값 세팅
                                    document.getElementById("addr").value = data.address; // 주소 넣기
                                    document
                                          .querySelector(
                                                "input[name=addr_detail]")
                                          .focus(); //상세입력 포커싱
                                 }
                              }).open();
                     });
      }

      function fnValidation() {
         // 현재 비밀번호 체크      
         var now_pwd = document.getElementById("now_pwd").value;
         var now_chk_pwd = document.getElementById("now_chk_pwd").value;
         if (now_pwd != now_chk_pwd) {
            alert("현재 비밀번호가 맞지 않습니다.\n다시 입력해 주세요.");
            return false;
         }

         var new_pwd = document.getElementById("new_pwd").value;
         var new_chk_pwd = document.getElementById("new_chk_pwd").value;
         //var passwdRegExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}/;  문자 대소문자 반드시 입력  
         var passwdRegExp = /^[A-Za-z0-9]{4,20}$/;
         if ((new_pwd != null && new_pwd != "")
               || (new_chk_pwd != null && new_chk_pwd != "")) {
            if (new_pwd != new_chk_pwd) {
               alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
               return false;
            } else {
               if ((passwdRegExp.test(new_pwd) == false)
                     || (passwdRegExp.test(new_chk_pwd) == false)) {
                  //정규식에 맞지않으면 return null
                  alert("비밀번호는 영문대소문자와 숫자를 포함하여 4자리에서 20자리 이내로 입력해 주세요.");
                  return false;
               }
            }
         }

         var custNm = $("#cust_nm").val();
         if (custNm == "") {
            alert("이름을 입력해 주세요.");
            return false;
         }

         if (!$("#emailChkBtn").attr("disabled")) { //가져오는 값 그대로일땐 비활성화, 변경되면 활성화 되어 버튼 사용할 수 있음
            alert("이메일 중복을 확인해 주세요.");
            return false;
         }

         //휴대폰 정규표현식
         var telVal = $("#tel").val();
         var regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
         if (!regExp.test(telVal)) {
            //정규식에 맞지않으면 리턴
            alert("휴대폰 형식을 확인해 주세요.");
            return false;
         }

         return true;

      }

      // 회원정보수정
      function fnChangeMemberInfo() {
         if (fnValidation()) { // 값체크
            var params = $("#contact-form").serialize();// 아래의 주석과 동일한 형태로 전송됨.
            console.log("params=" + params);
            console.log("params=" + JSON.stringify(params));
            $.ajax({
               type : "post",
               url : "/cust/update",
               data : params,
               dataType : 'json',
               success : function(data) {
                  console.log("success_data_origin= " + data);
                  console
                        .log("success_data_str= "
                              + JSON.stringify(data));// Object로 받아온 것을 문자열의 형태로 풀어서 보여주는 것
                  if (data.result > 0) {
                     // 정상적으로 수정되었을 경우
                     alert("수정 되었습니다.");
                  }
               },
               error : function(request, status, error) {
                  console.log("error= code:" + request.status + "\n"
                        + "message:" + request.responseText + "\n"
                        + "error:" + error);
               }
            });
         }

      }

      // 이메일 칸에 수정을했을때 중복 확인버튼 활성화
      function fnEmailChange() {
         if ($("#emailChkBtn").attr("disabled")) {
            $("#emailChkBtn").prop('disabled', false);
            $("#emailChkBtn").removeClass('disabled'); //disabled 클래스를 제거하여, 이 버튼을 활성화 상태로 변경        
         }
      }

      // 이메일 중복확인
      function fnEmailCheck() {
         var email = $("#email").val();
         if (email == "") {
            alert("이메일을 입력해 주세요.");
            return;
         }

         var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
         if (email.match(regExp) == null) {
            //정규식에 맞지않으면 return null
            alert("이메일 형식을 확인해 주세요.");
            return false;
         }

         //key : value 형식의 data를 직접 입력
         var params = {
            cust_idx : $("#cust_idx").val(),
            email : email
         }

         // ajax 통신
         $.ajax({
            type : "POST", // HTTP method type(GET, POST) 형식
            url : "/cust/email/check", // 컨트롤러에 구현해놓은 URL 주소
            data : params,
            success : function(result) {
               // 여기로 들어오는 자체가 ajax 자체는 성공!
               if (result.emailCount == 0) {
                  alert("사용가능한 이메일 입니다.");
                  $("#emailChkBtn").addClass('disabled');
                  $("#emailChkBtn").prop('disabled', true);

               } else {
                  alert("중복된 이메일이 존재합니다.");
               }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
               // ajax 통신중 문제가 있었을 경우
               // 일반적으로는 관리자에게 문의하세요. 등의 alert를 띄움.
               alert("관리자에게 문의하세요.");
            }
         });
      }

      // 탈퇴 컨펌 함수
      function fnDeleteConfirm() {
         if (confirm("정말 탈퇴 하시겠습니까?")) {
            // 예를 눌렀을 경우
            // 아래의 params는 파라미터로 보낼값(아래는 json 형태로 구현)
            var params = {
               cust_idx : $("#cust_idx").val()
            }

            // ajax 통신
            $.ajax({
               type : "POST", // HTTP method type(GET, POST) 형식
               url : "/cust/quit", // 컨트롤러에 구현해놓은 URL 주소
               data : params,
               success : function(result) {
                  // 여기로 들어오는 자체가 ajax 자체는 성공!
                  if (result.delCount > 0) {
                     alert("탈퇴 처리 되었습니다.");
                     // 로그아웃(세션삭제) 처리하고 메인으로 보내버리면됨.
                     location.href = '/';
                  }
               },
               error : function(XMLHttpRequest, textStatus, errorThrown) {
                  // ajax 통신중 문제가 있었을 경우
                  // 일반적으로는 관리자에게 문의하세요. 등의 alert를 띄움.
                  alert("관리자에게 문의 하세요.");
               }
            });
         } else {
            // 아니오를 눌렀을 경우
            // 별다른 행동이 없을 경우 else는 삭제해도됨.

         }
      }
   </script>
   <c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>