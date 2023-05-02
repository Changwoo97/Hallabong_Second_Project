package kr.co.hallabong.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustBean {
   
   private int cust_idx; //+추가

   private String id;      // 아이디
   private String pw;      // 현재비밀번호   
   private String name;    // 이름
   //private String email;   // 이메일
   //private String tel;     // 전화번호
   //private String addr;    // 주소
   //private String gender;  // 성별
   //private String dob;     // 생년월(YYYY-MM-DD)
   private String reg_tm;  // 가입일(YYYY-MM-DD) default 이용
   private String quit_tm; // 탈퇴일(YYYY-MM-DD) default 이용
   private String sta;     // 회원상태(가입: REG, 탈퇴: QUIT)
   
   private boolean custLogin; // 로그인여부
   private boolean custIdExist;
   
   @Size(min=4, max=20)
   @Pattern(regexp = "[a-zA-Z0-9]*")
   private String new_pwd; // 새 비밀번호 + 추가
   
   public int getCust_idx() {
      return cust_idx;
   }


   public void setCust_idx(int cust_idx) {
      this.cust_idx = cust_idx;
   }


   public String getId() {
      return id;
   }


   public void setId(String id) {
      this.id = id;
   }


   public String getPw() {
      return pw;
   }


   public void setPw(String pw) {
      this.pw = pw;
   }


   public String getNew_pwd() {
      return new_pwd;
   }


   public void setNew_pwd(String new_pwd) {
      this.new_pwd = new_pwd;
   }


   public String getName() {
      return name;
   }


   public void setName(String name) {
      this.name = name;
   }


   public String getEmail() {
      return email;
   }


   public void setEmail(String email) {
      this.email = email;
   }


   public String getTel() {
      return tel;
   }


   public void setTel(String tel) {
      this.tel = tel;
   }


   public String getAddr() {
      return addr;
   }


   public void setAddr(String addr) {
      this.addr = addr;
   }


   public String getGender() {
      return gender;
   }


   public void setGender(String gender) {
      this.gender = gender;
   }


   public String getDob() {
      return dob;
   }


   public void setDob(String dob) {
      this.dob = dob;
   }


   public String getReg_tm() {
      return reg_tm;
   }


   public void setReg_tm(String reg_tm) {
      this.reg_tm = reg_tm;
   }


   public String getQuit_tm() {
      return quit_tm;
   }


   public void setQuit_tm(String quit_tm) {
      this.quit_tm = quit_tm;
   }


   public String getSta() {
      return sta;
   }


   public void setSta(String sta) {
      this.sta = sta;
   }


   public boolean isCustLogin() {
      return custLogin;
   }


   public void setCustLogin(boolean custLogin) {
      this.custLogin = custLogin;
   }


   public boolean isCustIdExist() {
      return custIdExist;
   }


   public void setCustIdExist(boolean custIdExist) {
      this.custIdExist = custIdExist;
   }   
   
   public CustBean() {
      this.custIdExist=false;
      this.custLogin=false;
   }
   
   /*아래를 신규 CustBean으로 봄*/
   private String custId;
   private String passwd;
   private String custNm;
   private String email;
   private String tel;
   private String addr;
   private String gender;
   private String dob;
   private String joinDt;
   private String regId;
   private String regDt;
   private String updId;
   private String updDt;
   
   public String getCustId() {
      return custId;
   }


   public void setCustId(String custId) {
      this.custId = custId;
   }


   public String getPasswd() {
      return passwd;
   }


   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }
   
}
