package kr.co.hallabong.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.bean.PageBean;
import kr.co.hallabong.dao.NotiDAO;

@Service
public class NotiService {
	@Autowired
	private NotiDAO notiDAO;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	public List<NotiBean> getNotiList() {
		return notiDAO.selectNotiList();
	}  
	
	public List<NotiBean> getNotiList2(int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		
		return notiDAO.selectNotiList2(rowBounds);
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
	
	public PageBean getNotiCnt(int currentPage) {
		//전체글의 갯수
		int noti_cnt = notiDAO.getNotiCnt();
		
		PageBean pageBean = new PageBean(noti_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageBean;
	}	
}
