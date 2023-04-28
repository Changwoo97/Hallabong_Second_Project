package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.QABean;
import kr.co.hallabong.mapper.QAMapper;

@Repository
public class QADAO {
	@Autowired
	private QAMapper qaMapper;
	
	public List<QABean> selectQAList(String sta) {
		return qaMapper.selectQAList(sta);
	}
	
	public QABean selectQA(String no) {
		return qaMapper.selectQA(no);
	}
	
	public void insertQA(QABean bean) {
		qaMapper.insertQA(bean);
	}
	
	public void updateQ(QABean bean) {
		qaMapper.updateQ(bean);
	}
	
	public void updateA(QABean bean) {
		qaMapper.updateA(bean);
	}
	
	public void deleteQA(String no) {
		qaMapper.deleteQA(no);
	}
}
