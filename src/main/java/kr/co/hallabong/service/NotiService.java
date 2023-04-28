package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.dao.NotiDAO;

@Service
public class NotiService {
	@Autowired
	private NotiDAO notiDAO;
	
	public List<NotiBean> getNotiList() {
		return notiDAO.selectNotiList();
	}  
	
	public NotiBean getNoti(String no) {
		return notiDAO.selectNoti(no);
	}
	
	public void addNoti(NotiBean bean) {
		notiDAO.insertNoti(bean);
	}
	
	public void setNoti(NotiBean bean) {
		notiDAO.updateNoti(bean);
	}
	
	public void removeNoti(String no) {
		notiDAO.deleteNoti(no);
	}
}
