package kr.co.hallabong.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CustBean;

@Mapper //MyBatis에서 사용하는 Mapper 인터페이스임을 명시
public interface CustMapper {
	/*개인정보수정(본인재확인)*/
	@Select("SELECT id "
		   + "FROM cust "
		   + "WHERE id = #{id} "
		   + "AND pw = #{pw} ")
	String getLoginCustIdx(CustBean paramLoginCustBean); 
	//메서드는 NewCustBean 타입의 매개변수 paramLoginCustBean을 받고, int 타입의 CUST_IDX를 반환
	//즉, 이 메서드는 paramLoginCustBean 객체에 저장된 사용자 ID와 비밀번호를 이용하여 로그인한 사용자의 인덱스 값을 반환
	
	/*개인정보 상세정보 조회*/
    @Select("SELECT id "
    		+ "   , pw "
		    + "   , name "
		    + "   , email "
		    + "   , tel "
		    + "   , addr "
		    + "   , gender "
		    + "   , TO_CHAR(dob, 'YYYY-MM-DD') AS dob "
		    + "   , TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm "
		    + "   , TO_CHAR(quit_tm, 'YYYY-MM-DD') AS quit_tm "
		    + "   , sta "
		    + "FROM cust "
		    + "WHERE id = #{id} ")
	CustBean getLoginCustDetailInfo(String id); 
    
    /*탈퇴하기*/
    @Delete("DELETE FROM cust " +
		    "WHERE id = #{id} ")
	int deleteCust(String id);	

	@Select("SELECT id, name "
			+ "FROM cust "
			+ "WHERE id = #{id} and pw = #{pw} ")
	CustBean getLoginCustInfo(CustBean tempLoginUserBean); //tempLoginCustBean은 request영역
	//회원가입
	@Insert("INSERT INTO cust VALUES (#{id}, #{pw}, #{name}, #{email}, #{tel}, #{addr}, #{gender}, TO_DATE(#{dob}, 'YYYY-MM-DD'), DEFAULT, DEFAULT, 'REG') ")
	void addjoin(CustBean joinusecuCustBean);
	
	@Insert("INSERT INTO cust VALUES (#{id}, #{pw}, #{name}, #{email}, #{tel}, #{addr}, NULL, TO_DATE(#{dob}, 'YYYY-MM-DD'), DEFAULT, DEFAULT, 'REG') ")
	void addjoinGenderNull(CustBean joinusecuCustBean);
	
	@Insert("INSERT INTO cust VALUES (#{id}, #{pw}, #{name}, #{email}, #{tel}, #{addr}, #{gender}, NULL, DEFAULT, DEFAULT, 'REG') ")
	void addjoinDobNull(CustBean joinusecuCustBean);
	
	@Insert("INSERT INTO cust VALUES (#{id}, #{pw}, #{name}, #{email}, #{tel}, #{addr}, NULL, NULL, DEFAULT, DEFAULT, 'REG') ")
	void addjoinGenderNullDobNull(CustBean joinusecuCustBean);
	
	//아이디 
	@Select("select name from cust where id=#{id}")
	String checkUserIDExist(String id);
	
	@Select("select pw from cust where id= #{id} and tel = #{tel}")
	CustBean findPw(CustBean findpw);
	
	@Select("select Id from cust where name= #{name} and tel = #{tel}")
	CustBean findId(CustBean findid);
	
	//수정
	@Update("UPDATE cust "
		  + "   SET name = #{name}"
		  + "     , email = #{email}"
		  + "     , tel = #{tel}"
		  + "     , addr = #{addr}"
		  + "     , gender = #{gender}"
		  + "     , dob = #{dob}"
		  + "     , pw = #{pw}"		  
		  + " WHERE id = #{id}")
	int updateCustInfo(CustBean updateCustBean);
	
	/*이메일중복확인*/
	@Select("SELECT COUNT(1) AS cnt "
		  + "  FROM cust "
		  + " WHERE email = #{email} "
		  + " AND id <> #{id} ")
	int getEmailDupCheck(CustBean paramLoginCustBean);

}

