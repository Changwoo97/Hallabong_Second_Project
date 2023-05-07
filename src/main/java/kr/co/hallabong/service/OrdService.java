package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.dao.OrdDAO;

@Service
public class OrdService {
	@Autowired
	private OrdDAO ordDAO;
	
	public OrdBean getOrd(String no) {
		return ordDAO.selectOrd(no);
	}
	
	public void addOrd(OrdBean bean) {
		ordDAO.insertOrd(bean);
	}
	
	public void setOrdStaProcess(String no) {
		ordDAO.updateOrdSta(no, "PROCESS");
	}
	
	public void setOrdStaSemiComplete(String no) {
		ordDAO.updateOrdSta(no, "SEMI-COMPLETE");
	}
	
	public void setOrdStaComplete(String no) {
		ordDAO.updateOrdSta(no, "COMPLETE");
	}
	
	public List<OrdBean> getOrdList(String cust_id) {
		return ordDAO.getOrdList(cust_id);
	}
}
