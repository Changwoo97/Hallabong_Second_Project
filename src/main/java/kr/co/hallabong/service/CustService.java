 package kr.co.hallabong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.dao.CustDAO;

@Service
public class CustService {
	
	@Autowired
	private CustDAO custDAO;
	
	// 로그인정보확인
	public int getLoginCustIdx(NewCustBean paramLoginCustBean) {
		return custDAO.getLoginCustIdx(paramLoginCustBean);
	}
	
	// 회원상세정보 조회
	public NewCustBean getLoginCustDetailInfo(int paramCustIdx) {
		return custDAO.getLoginCustDetailInfo(paramCustIdx);
	}
	
	// 회원정보수정
	public int updateCustInfo(NewCustBean paramLoginCustBean) {
		paramLoginCustBean.setUpd_id(paramLoginCustBean.getCust_id());
		return custDAO.updateCustInfo(paramLoginCustBean);
	}
	
	// 이메일중복확인
	public int getEmailDupCheck(NewCustBean paramLoginCustBean) {
		return custDAO.getEmailDupCheck(paramLoginCustBean);
	}
	
	// 탈퇴하기
	public int deleteCust(int paramCustIdx) {
		return custDAO.deleteCust(paramCustIdx);
	}
	
}
