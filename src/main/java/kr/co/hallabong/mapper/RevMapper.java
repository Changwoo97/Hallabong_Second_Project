package kr.co.hallabong.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.RevBean;

public interface RevMapper {
	
	@Select("SELECT NO\n" + 
			"FROM REV\n" + 
			"WHERE CUST_ID = #{cust_id} " +
			"  AND PROD_NO = #{prod_no} ")
	String getRevNo(RevBean revParam);
	
	@Select("SELECT NO\n" + 
			"     , PROD_NO\n" +
			"     , CUST_ID\n" +
			"     , CONT\n" + 
			"     , REG_TM\n" +
			"FROM REV\n" + 
			"WHERE NO = #{rev_no} ")
	RevBean getRevDetail(String rev_no);
	
	@Insert("INSERT INTO rev (no, prod_no, cust_id, cont, reg_tm)\n" +
			"VALUES (DEFAULT, #{prod_no}, #{cust_id}, #{cont}, DEFAULT) ")
	void insertRev(RevBean bean);
	
	@Update("UPDATE rev SET cont = #{cont} WHERE cust_id = #{cust_id} AND prod_no = #{prod_no} ")
	void updateRev(RevBean bean);
}
