package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.QABean;
import kr.co.hallabong.dao.QADAO;

@Service
public class QAService {
	@Autowired
	private QADAO qaDAO;
	
	public List<QABean> getRequestQAList() {
		return qaDAO.selectQAList("REQUEST");
	} 
	
	public List<QABean> getCompleteQAList() {
		return qaDAO.selectQAList("COMPLETE");
	}
	
	public void addQA(QABean bean) {
		qaDAO.insertQA(bean);
	}
	
	public QABean getQA(String no) {
		return qaDAO.selectQA(no);
	}
	
	public void setQ(QABean bean) {
		qaDAO.updateQ(bean);
	}
	
	public void setA(QABean bean) {
		qaDAO.updateA(bean);
	}
	
	public List<QABean> getQAList(String cust_id) {
		return qaDAO.getQAList(cust_id);
	 }
}
