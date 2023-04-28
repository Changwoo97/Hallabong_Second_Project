package kr.co.hallabong.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.bean.CustInfoBean;


public interface CustMapper {
	
	@Select("SELECT id,name "
			+ "FROM CUST "
			+ "WHERE ID = #{id} and PW = #{pw}")
	CustBean getLoginCustInfo(CustBean tempLoginUserBean); //tempLoginCustBean은 request영역
	//회원가입
	@Insert("insert into cust values (#{id},#{pw},#{name},#{email},#{tel},#{addr},#{gender},#{dob},default,default,'REG')")
	void addjoin(CustBean joinusecuCustBean);
	//아이디 
	@Select("select name from cust where id=#{id}")
	String checkUserIDExist(String id);
	
	@Select("select pw from cust where id= #{id} and tel = #{tel}")
	CustBean findPw(CustBean findpw);
	
	@Select("select Id from cust where name= #{name} and tel = #{tel}")
	CustBean findId(CustBean findid);
	
	
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
