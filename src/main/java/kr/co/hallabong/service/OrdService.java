package kr.co.hallabong.service;

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
	
	public void setOrdStaProcess(String no) {
		ordDAO.updateOrdSta(no, "PROCESS");
	}
	
	public void setOrdStaComplete(String no) {
		ordDAO.updateOrdSta(no, "COMPLETE");
	}
}
