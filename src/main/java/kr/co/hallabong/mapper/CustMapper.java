package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.bean.CustInfoBean;

@Mapper //MyBatis에서 사용하는 Mapper 인터페이스임을 명시
public interface CustMapper {
	
	/*개인정보수정(본인재확인)*/
	@Select("SELECT CUST_IDX "
		   + "FROM CUST "
		   + "WHERE CUST_ID = #{cust_id} "
		   + "AND PASSWD = #{passwd}")
	int getLoginCustIdx(NewCustBean paramLoginCustBean); 
	//메서드는 NewCustBean 타입의 매개변수 paramLoginCustBean을 받고, int 타입의 CUST_IDX를 반환
	//즉, 이 메서드는 paramLoginCustBean 객체에 저장된 사용자 ID와 비밀번호를 이용하여 로그인한 사용자의 인덱스 값을 반환
	
	/*개인정보 상세정보 조회*/
    @Select("SELECT CUST_IDX "
    		+ "   , CUST_ID"
    		+ "   , PASSWD"
		    + "   , CUST_STAT"
		    + "   , CUST_NM"
		    + "   , EMAIL"
		    + "   , TEL"
		    + "   , ADDR"
		    + "   , GENDER"
		    + "   , TO_CHAR(DOB, 'YYYY-MM-DD') AS DOB"
		    + "   , TO_CHAR(JOIN_DT, 'YYYY-MM-DD') AS JOIN_DT"
		    + "   , TO_CHAR(QUIT_DT, 'YYYY-MM-DD') AS QUIT_DT"
		    + "   , REG_ID"
		    + "   , TO_CHAR(REG_DT, 'YYYY-MM-DD') AS REG_DT"
		    + "   , UPD_ID"
		    + "   , TO_CHAR(UPD_DT, 'YYYY-MM-DD') AS UPD_DT "
		    + "FROM CUST "
		    + "WHERE CUST_IDX = #{cust_idx}")
	NewCustBean getLoginCustDetailInfo(int paramCustIdx); 
    
    /*탈퇴하기*/
    @Delete("DELETE FROM CUST " +
		    " WHERE cust_idx = #{cust_idx}")
	int deleteCust(int cust_idx);	
	
	//수정
	@Update("UPDATE CUST"
		  + "   SET CUST_NM = #{cust_nm}"
		  + "     , EMAIL = #{email}"
		  + "     , TEL = #{tel}"
		  + "     , ADDR = #{addr}"
		  + "     , GENDER = #{gender}"
		  + "     , DOB = #{dob}"
		  + "     , UPD_ID = #{upd_id}"
		  + "     , UPD_DT = SYSDATE"
		  + " WHERE CUST_IDX = #{cust_idx}")
	int updateCustInfo(NewCustBean updateCustBean);
	
	/*이메일중복확인*/
	@Select("SELECT COUNT(1) AS CNT"
		  + "  FROM CUST "
		  + " WHERE EMAIL = #{email}"
		  + " AND CUST_IDX <> #{cust_idx}")
	int getEmailDupCheck(NewCustBean paramLoginCustBean);

	

}

