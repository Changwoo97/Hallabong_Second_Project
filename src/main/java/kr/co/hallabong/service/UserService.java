package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.dao.UserDao;
import kr.co.hallabong.bean.UserBean;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<UserBean> getuserLoginPage(){
		List<UserBean> userLoginList = userDao.getuserLoginPage();
		
		return userLoginList;
	}

}
