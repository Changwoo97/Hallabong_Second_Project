 package kr.co.hallabong.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.dao.CustDao;


@Service
public class CustService {
	
	@Autowired
	private CustDao custDao;
	
	// 로그인정보확인
	public int getLoginCustIdx(NewCustBean paramLoginCustBean) {
		return custDao.getLoginCustIdx(paramLoginCustBean);
	}
	
	// 회원상세정보 조회
	public NewCustBean getLoginCustDetailInfo(int paramCustIdx) {
		return custDao.getLoginCustDetailInfo(paramCustIdx);
	}
	
	// 회원정보수정
	public int updateCustInfo(NewCustBean paramLoginCustBean) {
		paramLoginCustBean.setUpd_id(paramLoginCustBean.getCust_id());
		return custDao.updateCustInfo(paramLoginCustBean);
	}
	
	// 이메일중복확인
	public int getEmailDupCheck(NewCustBean paramLoginCustBean) {
		return custDao.getEmailDupCheck(paramLoginCustBean);
	}
	
	// 탈퇴하기
	public int deleteCust(int paramCustIdx) {
		return custDao.deleteCust(paramCustIdx);
	}
	
}

