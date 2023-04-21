package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 공지사항
public class NotiBean {
	private String no; // 공지번호 default 이용
	private String tit; // 제목
	private String cont; // 내용
	private String reg_tm; // 등록일 default 이용
}
