package kr.co.hallabong.bean;

import lombok.Getter;

@Getter
public class PageBean {


	// 최소 페이지 번호
	private int min;
	// 최대 페이지 번호
	private int max;
	// 이전 버튼의 페이지 번호
	private int prevPage;
	// 다음 버튼의 페이지 번호
	private int nextPage;
	// 전체 페이지 개수
	private int pageCnt;
	// 현재 페이지 번호
	private int currentPage;
	
	
	// contentCnt : 전체글 개수, currentPage : 현재 글 번호, contentPageCnt : 페이지당 글의 개수, paginationCnt : 페이지 버튼의 개수
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		
		// 현재 페이지 번호
		this.currentPage = currentPage;
		
		// 전체 페이지 개수
		pageCnt = contentCnt / contentPageCnt;
		//533/10=53 이므로 1page더 필요
		if(contentCnt % contentPageCnt > 0) {
			pageCnt++;
		}
		/* 
		 1 ~ 10 : 1(최소), 10(최대)
		 11 ~ 20 : 11, 20
		 21 ~ 30 : 21, 30
		
		 //-1
		 0 ~ 9 : 1
		 10 ~ 19 : 11
		 20 ~ 29 : 21
		
		//페이지당 글의갯수로 나누면
		0:1
		1:11
		2:21
		//페이지당 글의갯수로 다시 곱하면
		0:1
		10:11
		20:21
		//+1를 하면
		1:1
		11:11
		21:21*/
		
		min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
		max = min + paginationCnt - 1; //1+10=11이므로 1을 빼줌
		//51페이지에는 60부터 ~ 69번째글까지 존재하므로 
		//전체페이지를 넘어가지 않도록 세팅함
		if(max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		//마지막페이지를 넘어가지 않도록
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
	}
	
}
