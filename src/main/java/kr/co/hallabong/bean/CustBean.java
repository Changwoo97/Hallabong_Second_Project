package kr.co.hallabong.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustBean {
	private String reg_tm; // 가입일(YYYY-MM-DD) default 이용
	private String quit_tm; // 탈퇴일(YYYY-MM-DD) default 이용
	private String sta; // 회원상태(가입: REG, 탈퇴: QUIT)
	
	@Size(min=2, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	@NotBlank
	private String id;
	
	@Size(min=2,max=4)
	@Pattern(regexp = "[가-힣]*")
	private String name;  
	
	@Size(min=4,max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String pw;
	
	private String shpw;
	@Size(min=4,max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String pw2;
	

	private String email;
	
	private String tel;
	private String tel2;

	private String addr;

	
	private String addr1;

	private String addr_detail;

	private String gender;
	
	
	private String dob;
	private String dob_year;
	private String dob_month;
	private String dob_day;
	


	private boolean custIdExist;
	private boolean custLogin;
	private boolean custtelExist;
	
	
	public CustBean() {
		this.custIdExist=false;
		this.custLogin=false;	
		this.custtelExist=false;
	}

}
