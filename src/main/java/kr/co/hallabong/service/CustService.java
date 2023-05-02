package kr.co.hallabong.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.dao.CustDao;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.api.Message;
@Service
public class CustService {

	@Autowired
	private CustDao custDao;


	@Resource(name = "CustBean")
	private CustBean CustBean;
	
	

	// 로그인
	public void getLoginCustInfo(CustBean tempLoginUserBean) {
		CustBean tempLoginUserBean2 = custDao.getLoginCustInfo(tempLoginUserBean);
		// 가져온 값이 없을시 예외처리

		if (tempLoginUserBean2 != null) {
			CustBean.setId(tempLoginUserBean2.getId());
			CustBean.setName(tempLoginUserBean2.getName());
			CustBean.setCustLogin(true); // 최초에 false로 되음. 로그인성공

		}

	}

	public void addjoinUserInfo(CustBean joinusecuCustBean) {
		custDao.addjoin(joinusecuCustBean);
	}

	public boolean checkUserIDExist(String id) {
		String name = custDao.checkUserIDExist(id);

		if (name == null) {
			return true;
		} else {
			return false;
		}

	}
	


	public void findId(CustBean findid) {
		CustBean findid2 = custDao.findId(findid);
		if (findid2 != null) {
			findid.setId(findid2.getId());
		}else {
			findid.setId("찾으시는 정보의 아이디가 없습니다");
		}
	}

	public void findPw(CustBean findpw) {
		CustBean findpw2 = custDao.findPw(findpw);
		if (findpw2 != null) {
			findpw.setPw(findpw2.getPw());
		}else {
			findpw.setPw("찾으시는 정보의 비밀번호가 없습니다 다시 확인해주세요");
		}
	}
	
	public void certifiedPhoneNumber(String tel, String numStr) {
		 
        String api_key = "NCSH4IG8IK8VCXJO";
          String api_secret = "ORIXGKSEC2WG3BLW314EJQATXXMJVFEK";
          Message coolsms = new Message(api_key, api_secret);

        
          HashMap<String, String> params = new HashMap<String, String>();
          params.put("to", tel);    
          params.put("from", "010-4129-9680");   
          params.put("type", "SMS");
          params.put("text", "  작성할내용 ["+numStr+"] 내용 ");
          params.put("app_version", "test app 1.2"); // application name and version

          try {
              JSONObject obj = (JSONObject) coolsms.send(params);
              System.out.println(obj.toString());
          } catch (CoolsmsException e) {
              System.out.println(e.getMessage());
              System.out.println(e.getCode());
          }

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
 * // 아이디 중복체크 public boolean checkUserIdExist(String id) {
 * 
 * String id =
 * 
 * if (user_name == null) { return true; } else { return false; } }
 * 
 * public void addUserInfo(CustBean joinCustBean) {
 * CustDao.addUserInfo(joinCustBean); }
 */

// 로그인

/*
 * 
 * //modifyCustBean : modify.jsp에서 주입 받은 정보를 객체에 담아서 가져옴 public void
 * getModifyUserInfo(CustBean modifyCustBean) { CustBean
 * tempModifyCustBean=custDao.getModifyUserInfo(loginCustBean.getUser_idx());
 * 
 * modifyCustBean.setUser_id(tempModifyCustBean.getUser_id());
 * modifyCustBean.setUser_name(tempModifyCustBean.getUser_name());
 * modifyCustBean.setUser_idx(loginCustBean.getUser_idx()); //업데이트할 때 필요
 * 
 * }
 * 
 * 
 * public void modifyUserInfo(CustBean modifyCustBean) {
 * modifyCustBean.setUser_idx(loginCustBean.getUser_idx());
 * userDao.modifyUserInfo(modifyCustBean);
 * 
 * }
 */
