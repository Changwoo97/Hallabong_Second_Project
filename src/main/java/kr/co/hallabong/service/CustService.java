 package kr.co.hallabong.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.dao.CustDao;


@Service
public class CustService {
	
	@Autowired
	private CustDao custDao;

	@Resource(name = "loginCustBean")
	private CustBean loginCustBean; // 세션영역
	
	// 로그인
	public void getLoginCustInfo(CustBean tempLoginCustBean) {

		CustBean tempLoginCustBean2 = custDao.getLoginCustInfo(tempLoginCustBean);

		// 가져온 값이 없을시 예외처리
		if (tempLoginCustBean2 != null) {
			loginCustBean.setId(tempLoginCustBean2.getId());
			loginCustBean.setPw(tempLoginCustBean2.getPw());
			loginCustBean.setCustLogin(true); // 최초에 false로 되음. 로그인성공

		}

	}
	
	// 이메일중복확인
//	public void getCheckCustEmailInfo(CustBean tempLoginCustBean) {
//
//		CustBean tempLoginCustBean2 = custDao.getCheckCustEmailInfo(tempLoginCustBean);
//		
//		// 가져온 값이 없을시 예외처리
//		if (tempLoginCustBean2 != null) {
//			loginCustBean.setId(tempLoginCustBean2.getId());
//			loginCustBean.setPw(tempLoginCustBean2.getPw());
//			loginCustBean.setCustLogin(true); // 최초에 false로 되음. 로그인성공
//
//		}
//
//	}
	
	/*
	// 아이디 중복체크
	public boolean checkUserIdExist(String id) {
		String id = 

		if (user_name == null) {
			return true;
		} else {
			return false;
		}
	}

	public void addUserInfo(CustBean joinCustBean) {
		CustDao.addUserInfo(joinCustBean);
	}
	*/

	// 로그인
	

			
		

	}
	
	/*
	
	//modifyCustBean : modify.jsp에서 주입 받은 정보를 객체에 담아서 가져옴
	public void getModifyUserInfo(CustBean modifyCustBean) {
		CustBean tempModifyCustBean=custDao.getModifyUserInfo(loginCustBean.getUser_idx());
		
		modifyCustBean.setUser_id(tempModifyCustBean.getUser_id());
		modifyCustBean.setUser_name(tempModifyCustBean.getUser_name());
		modifyCustBean.setUser_idx(loginCustBean.getUser_idx()); //업데이트할 때 필요
		
	}

	
	public void modifyUserInfo(CustBean modifyCustBean) {
		modifyCustBean.setUser_idx(loginCustBean.getUser_idx());
		userDao.modifyUserInfo(modifyCustBean);
		
	}*/
