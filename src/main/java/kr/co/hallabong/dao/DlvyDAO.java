package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.DlvyBean;
import kr.co.hallabong.mapper.DlvyMapper;

@Repository
public class DlvyDAO {
	@Autowired
	private DlvyMapper dlvyMapper;
	
	public List<DlvyBean> selectDlvyList(String sta) {
		return dlvyMapper.selectDlvyList(sta);
	} 
	
	public DlvyBean selectDlvy(String no) {
		return dlvyMapper.selectDlvy(no);
	}
	
	public void insertDlvy(DlvyBean bean) {
		dlvyMapper.insertDlvy(bean);
	}
	
	public void updateDlvySta(String no, String sta) {
		dlvyMapper.updateDlvySta(no, sta);
	}
	
	public void updateDlvyDep_tm(String no) {
		dlvyMapper.updateDlvyDep_tm(no);
	}
	
	public void updateDlvyArr_tm(String no) {
		dlvyMapper.updateDlvyArr_tm(no);
	}
}
