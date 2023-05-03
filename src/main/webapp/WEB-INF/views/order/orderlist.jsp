<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>order list</h2>
 <div class="text-right">
  <form id="chkForm" action="${root }board/write" method="post">
     <a href="${root }board/write?board_info_idx=${board_info_idx}" class="btn btn-primary">후기작성</a> 
         </form>
         </div>
</body>
</html>