<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Admin_Notice_style.css">
</head>
<body>
	<h4>공지사항 등록</h4>
	<hr class="hr1" />
	<br>

	<form id="contact-form">
		<div class="formDiv">
			<table>
				<colgroup>
					<col style="" />
					<col />
				</colgroup>
				<tbody>

					<tr>
						<th>제목</th>
						<td><input type="text" id="subject" name="subject" /></td>

					</tr>


					<tr>
						<th>첨부파일</th>
						<td><input type="file"></td>
					</tr>

				</tbody>
			</table>

			<br>
			<div>
				<textarea class="textarea" rows="30%" cols="100%"></textarea>
			</div>

			<div class="btn_wrap">
			<input type="submit" onclick="history.back()" value="뒤로가기" >
			<input type="submit" value="등록하기" >			
			<input type="reset" value="다시쓰기" />
		</div>


		</div>
		</form>
</body>
</html>