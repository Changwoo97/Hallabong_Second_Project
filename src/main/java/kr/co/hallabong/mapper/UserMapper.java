package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.UserBean;

public interface UserMapper {

	@Select("select user_idx, userPage_name " +
			"from userPage_table "+
			"order by board_info_idx")
	
	List<UserBean> getuserLoginPage();
	
	@Select("select user_name "
			+ "from user_table "
			+ "where user_id=#{user_id}")
	String checkUserIDExist(String user_id);
	
	@Insert("insert into user_table(user_idx,user_name,user_id,user_pw) "
			+ "values(user_seq.nextval,#{user_name},#{user_id},#{user_pw})")
	void addUserInfo(UserBean joinUserBean);

	@Select("select user_idx, "
			+ "user_name from user_table "
			+ "where user_id=#{user_id} and user_pw=#{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("select user_id,user_name from user_table where user_idx=#{user_idx}")
	UserBean getModifyUserInfo(int user_idx);
	
	@Update("update user_table "
			+ "set user_pw=#{user_pw} "
			+ "where user_idx=#{user_idx}")
	void modifyUserinfo(UserBean modifyUserBean);
}
