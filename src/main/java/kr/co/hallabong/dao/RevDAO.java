package kr.co.hallabong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.RevBean;
import kr.co.hallabong.mapper.RevMapper;

@Repository
public class RevDAO {
	
	@Autowired
	private RevMapper revMapper;
	
	//리뷰 번호조회(존재여부)
	public String getRevNo(RevBean revParam) {
		return revMapper.getRevNo(revParam);
	}
	
	//리뷰 상세정보조회 
	public RevBean getRevDetail(String rev_no) {
		return revMapper.getRevDetail(rev_no);
	}
	
	public void insertRev(RevBean bean) {
		revMapper.insertRev(bean);
	}
	
	public void updateRev(RevBean bean) {
		revMapper.updateRev(bean);
	}
}
