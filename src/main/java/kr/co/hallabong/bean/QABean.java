package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Q&A
public class QABean {
	private String no; // Q&A번호 default 이용
	private String cust_id; // 고객아이디
	private String q; // 질문
	private String q_reg_tm; // 질문등록시간 default 이용
	private String a; // 답변
	private String a_reg_tm; // 답변등록시간 default 이용
	private String sta; // Q&A 상태
}
