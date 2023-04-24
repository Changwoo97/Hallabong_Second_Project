package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.mapper.UserMapper;
import kr.co.hallabong.bean.UserBean;

@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;
	
	public List<UserBean> getuserLoginPage(){
		List<UserBean> userLoginList = userMapper.getuserLoginPage();
		
		return userLoginList;
	}
}