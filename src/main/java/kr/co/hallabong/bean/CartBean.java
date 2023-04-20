package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 장바구니
public class CartBean {
	private String cust_id; // 고객 아이디
	private String prod_no; // 상품번호
	private int qnty; // 수량
	private String reg_tm; // 등록일(YYYY-MM-DD), default 이용
}
