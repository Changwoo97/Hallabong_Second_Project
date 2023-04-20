package kr.co.hallabong.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 고객
public class CustBean {
	private String id; // 아이디
	private String pw; // 비밀번호
	private String name; // 이름
	private String email; // 이메일
	private String tel; // 전화번호
	private String addr; // 주소
	private String gender; // 성별
	private String dob; // 생년월(YYYY-MM-DD)
	private String reg_tm; // 가입일(YYYY-MM-DD) default 이용
	private String quit_tm; // 탈퇴일(YYYY-MM-DD) default 이용
	private String sta; // 회원상태(가입: REG, 탈퇴: QUIT)
}
