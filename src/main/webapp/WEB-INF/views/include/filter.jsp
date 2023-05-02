<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${root}css/filter.css" />
</head>
<body>

	<div id="category-filter">
		<button class="filter-button" data-filter="all">전체</button>
		<button class="filter-button" data-filter="채소">채소</button>
		<button class="filter-button" data-filter="과일">과일</button>
		<button class="filter-button" data-filter="육류">육류</button>
		<button class="filter-button" data-filter="해산물">해산물</button>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(
				function() {
					// 카테고리 필터링 버튼 클릭 이벤트 핸들러
					$('.filter-button').click(
							function() {
								// 선택된 버튼 활성화 처리
								$('.filter-button').removeClass('active');
								$(this).addClass('active');

								// 필터링 처리
								var filter = $(this).data('filter');
								if (filter === 'all') {
									$('.products tr').show();
								} else {
									$('.products tr').hide();
									$(
											'.products tr td:first-child:contains('
													+ filter + ')').parent()
											.show();
								}
							});
				});
	</script>

</body>
</html>