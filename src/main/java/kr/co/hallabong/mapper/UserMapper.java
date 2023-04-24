package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.UserBean;

public interface UserMapper {

	@Select("select user_idx, userPage_name " +
			"from userPage_table "+
			"order by board_info_idx")
	
	List<UserBean> getuserLoginPage();
}
