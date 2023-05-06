<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="board_date">작성날짜</label>
						<input type="text" id="board_date" name="board_date" class="form-control" value="${selectNoti.reg_tm }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="notice_subject">제목</label>
						<input type="text" id="board_subject" name="notice_subject" class="form-control" value="${selectNoti.tit }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="notice_content" class="form-control" rows="10" style="resize:none" disabled="disabled">${selectNoti.cont }</textarea>
					</div>
					<%--<div class="form-group">
 						<div class="text-right">
							<a href="${root }board/main?board_info_idx=${board_info_idx}&page=${page}" class="btn btn-primary">목록보기</a>
						</div> 
					</div>--%>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
</body>
</html>