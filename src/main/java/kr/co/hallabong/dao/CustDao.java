package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.mapper.CustMapper;


@Repository
public class CustDao {
	
	@Autowired
	private CustMapper custMapper;
	
	
	//수정	
	public int updateCustInfo(NewCustBean updateCustBean) {
		return custMapper.updateCustInfo(updateCustBean);
	}
	
	
	//로그인 사용자 idx 확인
	public int getLoginCustIdx(NewCustBean paramLoginCustBean) {
		return custMapper.getLoginCustIdx(paramLoginCustBean);
	}
	
	//로그인
	public NewCustBean getLoginCustDetailInfo(int paramCustIdx) {
		return custMapper.getLoginCustDetailInfo(paramCustIdx);
	}
	
	//로그인 사용자 idx 확인
	public int deleteCust(int paramCustIdx) {
		return custMapper.deleteCust(paramCustIdx);
	}
	
	//이메일 중복확인
	public int getEmailDupCheck(NewCustBean paramLoginCustBean) {
		return custMapper.getEmailDupCheck(paramLoginCustBean);
	}
	
}
	
	
	/*
	 * //이메일 중복체크 public String emaildoublecheck(String email) { 
	 * return
	 * custMapper.emaildoublecheck(email); }
	 */
	
	
