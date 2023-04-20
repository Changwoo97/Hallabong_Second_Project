package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//상품
public class ProdBean {
	private String no; // 상품번호
	private char fs; // 판매여부
	private String name; // 제품명
	private int cost; // 원가
	private int sp; // 판매가
	private int inv; // 재고량
	private String s_img; // 소이미지
	private String l_img; // 상세이미지
	private String desc; // 설명
	private String cat_no; // 카테고리 번호
	private String reg_tm; // 등록시간(YYYY-MM-DD) default 이용
}
