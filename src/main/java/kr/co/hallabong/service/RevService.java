package kr.co.hallabong.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.RevBean;
import kr.co.hallabong.dao.RevDAO;

@Service
public class RevService {
	@Autowired
	private RevDAO revDAO;
	
	public RevBean getRevDetail(String rev_no) {
		return revDAO.getRevDetail(rev_no);
	}
	
	public String getRevNo(RevBean revParam) {
		return revDAO.getRevNo(revParam);
	}
	
	public void addRev(RevBean bean) {
		revDAO.insertRev(bean);
	}
	
	public void setRev(RevBean bean) {
		revDAO.updateRev(bean);
	}
}
