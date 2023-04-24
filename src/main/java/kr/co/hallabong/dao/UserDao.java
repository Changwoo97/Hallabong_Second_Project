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
	
	public String checkUserIDExist(String user_id) {
		return userMapper.checkUserIDExist(user_id);
	}
	
	//로그인
		public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
			return userMapper.getLoginUserInfo(tempLoginUserBean);
		}
	
		//회원가입
		public void addUserInfo(UserBean joinUserBean){
			userMapper.addUserInfo(joinUserBean);
		}
		
		//사용자 정보수정
		public UserBean getModifyUserInfo(int user_idx) {
			return userMapper.getModifyUserInfo(user_idx);
		}
		public void modifyUserInfo(UserBean modifyUserBean) {

			userMapper.modifyUserinfo(modifyUserBean);
			
		}
		
}