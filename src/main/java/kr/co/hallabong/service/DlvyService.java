package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.DlvyBean;
import kr.co.hallabong.dao.DlvyDAO;

@Service
public class DlvyService {
	@Autowired
	private DlvyDAO dlvyDAO;
	
	public DlvyBean getDlvy(String no) {
		return dlvyDAO.selectDlvy(no);
	}
	
	public DlvyBean getDlvyByOrdNo(String ord_no) {
		return dlvyDAO.selectDlvyByOrdNo(ord_no);
	}
	
	public List<DlvyBean> getDlvyListReady() {
		return dlvyDAO.selectDlvyList("READY");
	}
	
	public List<DlvyBean> getDlvyListWait() {
		return dlvyDAO.selectDlvyList("WAIT");
	}
	
	public List<DlvyBean> getDlvyListProcess() {
		return dlvyDAO.selectDlvyList("PROCESS");
	}
	
	public List<DlvyBean> getDlvyListComplete() {
		return dlvyDAO.selectDlvyList("COMPLETE");
	}
	
	public void addDlvy(DlvyBean bean) {
		dlvyDAO.insertDlvy(bean);
	}
	
	public void setDlvyReady(String no) {
		dlvyDAO.updateDlvySta(no, "READY");
	}
	
	public void setDlvyWait(String no) {
		dlvyDAO.updateDlvySta(no, "WAIT");
	}
	
	public void setDlvyProcess(String no) {
		dlvyDAO.updateDlvySta(no, "PROCESS");
		dlvyDAO.updateDlvyDep_tm(no);
	}
	
	public void setDlvyComplete(String no) {
		dlvyDAO.updateDlvySta(no, "COMPLETE");
		dlvyDAO.updateDlvyArr_tm(no);
	}
}
