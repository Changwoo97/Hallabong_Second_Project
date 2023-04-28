package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.mapper.NotiMapper;

@Repository
public class NotiDAO {
	@Autowired
	private NotiMapper notiMapper;

	public List<NotiBean> selectNotiList() {
		return notiMapper.selectNotiList();
	}
	
	public NotiBean selectNoti(String no) {
		return notiMapper.selectNoti(no);
	}
	
	public void insertNoti(NotiBean bean) {
		notiMapper.insertNoti(bean);
	}
	
	public void updateNoti(NotiBean bean) {
		notiMapper.updateNoti(bean);
	}
	
	public void deleteNoti(String no) {
		notiMapper.deleteNoti(no);
	}
}
