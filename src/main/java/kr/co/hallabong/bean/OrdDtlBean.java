package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 주문상세
public class OrdDtlBean {
	private String ord_no; // 주문번호
	private String prod_no; // 상품번호
	private int prod_cost; // 상품원가
	private int prod_sp; // 상품판매가
	private int prod_qnty; // 상품수량
}
