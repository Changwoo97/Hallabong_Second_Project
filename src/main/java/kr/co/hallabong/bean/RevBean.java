package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 리뷰
public class RevBean {
	private String cust_id; // 고객아이디
	private String prod_no; // 상품번호
	private String cont; // 내용
	private String reg_tm; // 등록시간(YYYY-MM-DD) default 이용
}
