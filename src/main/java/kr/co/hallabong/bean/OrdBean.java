package kr.co.hallabong.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 주문
public class OrdBean {
	private String no; // 주문번호 default 이용
	private String cust_id; // 고객아이디
	private String type; // 주문종류 (주문: ord, 교환: ex, 환불: rtn)
	private String ordr_name; // 주문인 이름
	private String ordr_tel; // 주문인 전화번호
	private String ordr_addr; // 주문인 주소
	private String recv_name; // 수령인 이름
	private String recv_tel; // 수령인 전화번호
	private String recv_addr; // 수령인 주소
	private String pay_meth; // 지불수단
	private int dlvy_fee; // 배송료
	private String cont; // 기타내용(교환사유, 환불사유)
	private String reg_tm; // 주문일(YYYY-MM-DD) default 이용
	private String stlm_tm;	 // 정산일(YYYY-MM-DD) default 이용
	private String sta; // 주문상태(요청:REQUEST  -> 배송: PROCESS -> 정산전: SEMI-COMPLETE -> 정산후: COMPLETE)
	
	private List<OrdDtlBean> ord_dtl_list; //상품 상세 리스트
}
