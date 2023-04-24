package kr.co.hallabong.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.dao.UserDao;
import kr.co.hallabong.bean.UserBean;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUserBean")
	private UserBean logUserBean;
	
	public List<UserBean> getuserLoginPage(){
		List<UserBean> userLoginList = userDao.getuserLoginPage();
		
		return userLoginList;
	}
	
	public boolean checkUserIDExist(String user_id) {
		String user_name = userDao.checkUserIDExist(user_id);

		if (user_name == null) {
			return true;
		} else {
			return false;
		}

	}
	
	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}

	
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		if (tempLoginUserBean2 != null) {
			logUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			logUserBean.setUser_name(tempLoginUserBean2.getUser_name());
			logUserBean.setUserLogin(true);
		}
	}
	
	//modifyUserBean : modify.jsp에서 주입받은 정보를 객체에 담아서 가져옴
		public void getModifyUserInfo(UserBean modifyUserBean) {
			UserBean tempModifyUserBean=userDao.getModifyUserInfo(logUserBean.getUser_idx());
		
			modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
			modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
			modifyUserBean.setUser_idx(logUserBean.getUser_idx());
		}
		
		public void modifyUserInfo(UserBean modifyUserBean) {
			modifyUserBean.setUser_idx(logUserBean.getUser_idx());
			userDao.modifyUserInfo(modifyUserBean);
			
		}

}
