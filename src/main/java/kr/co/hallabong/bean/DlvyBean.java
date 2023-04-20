package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 배송
public class DlvyBean {
	private String no; // 배송번호
	private String send_name; // 보내는 사람
	private String send_tel; // 보내는 사람 전화번호
	private String send_addr; // 보내는 사람 주소
	private String recv_name; // 받는 사람 
	private String recv_tel; // 받는 사람 주소
	private String recv_addr; //받는 사람 주소
	private int fee; // 배송료
	private String reg_tm; // 등록일(YYYY-MM-DD) default 이용
	private String dep_tm; // 출발일(YYYY-MM-DD) default 이용
	private String arr_tm; // 도착일(YYYY-MM-DD) default 이용
	private String sta; // 배송상태(배송요청: REQUEST -> 배송준비: READY -> 배송대기: WAIT -> 배송중: PROCESS -> 배송완료: COMPLETE)
	private String ord_no; // 주문번호
}
