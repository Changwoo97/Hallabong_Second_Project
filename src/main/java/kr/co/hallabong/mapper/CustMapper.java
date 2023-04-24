package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.bean.CustInfoBean;


public interface CustMapper {
	
	@Select("SELECT id, pw "
			+ "FROM CUST "
			+ "WHERE ID = #{id} AND PW = #{pw}")
	CustBean getLoginCustInfo(CustBean tempLoginCustBean); //tempLoginCustBean은 request영역
	
	
	
	/*
	@Select("select user_name "
		 +	"from user_table "
		 +	"where user_id = #{user_id}")
	String checkcustIdExist(String id);
	
	
	
	@Insert("insert into user_table(user_idx, user_name, user_id, user_pw) "
			+ "values(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addcustInfo(CustBean joinUserBean);

	
	@Select("select user_idx, user_name "
			+ "from user_table "
			+ "where user_id = #{user_id} and user_pw = #{user_pw}")
	CustBean getLogincustInfo(CustBean tempLogincustBean); //tempLogincustBean은 request영역
	
	
	/*
	@Select("select user_id, user_name "
			+ "from user_table "
			+ "where user_idx = #{user_idx}")
	CustBean getModifyUserInfo(int user_idx);
	
	
	@Update("update user_table "
			+ "set user_pw=#{user_pw} "
			+ "where user_idx=#{user_idx} ")
	void modifyUserInfo(CustBean modifyUserBean);
	*/
}
