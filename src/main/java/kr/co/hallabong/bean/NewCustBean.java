package kr.co.hallabong.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCustBean {
	
	private int cust_idx;      // 회원인덱스
	private String cust_id;    // 아이디   
	private String passwd;     // 현재비밀번호
	private String cust_stat;  // 회원상태(활동: 10, 탈퇴: 20)
	private String cust_nm;    // 이름       
	private String email;      // 이메일      
	private String tel;        // 전화번호     
	private String addr;       // 주소       
	private String gender;     // 성별       
	private String dob;        // 생년월(YYYY-MM-DD) 
	private String join_dt;    // 가입일(YYYY-MM-DD) default 이용
	private String quit_dt;    // 탈퇴일(YYYY-MM-DD) default 이용
	private String reg_id;     // 등록자
	private String reg_dt;     // 등록일시
	private String upd_id;     // 수정자
	private String upd_dt;     // 수정일시
	
	
}
