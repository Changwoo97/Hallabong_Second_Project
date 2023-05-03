package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.join.ODPDBean;
import kr.co.hallabong.dao.ODPDDAO;

@Service
public class ODPDService {
	@Autowired
	private ODPDDAO odpdDAO;
	
	public List<ODPDBean> getODPDListReady() {
		return odpdDAO.selectODPDList("READY");
	}
	
	public List<ODPDBean> getODPDListWait() {
		return odpdDAO.selectODPDList("WAIT");
	}
	
	public List<ODPDBean> getODPDListProcess() {
		return odpdDAO.selectODPDList("PROCESS");
	}
	
	public List<ODPDBean> getODPDListComplete() {
		return odpdDAO.selectODPDList("COMPLETE");
	}
}
