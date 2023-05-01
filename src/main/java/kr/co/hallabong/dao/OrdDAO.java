package kr.co.hallabong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.mapper.OrdMapper;

@Repository
public class OrdDAO {
	@Autowired
	private OrdMapper ordMapper;
	
	public OrdBean selectOrd(String no) {
		return ordMapper.selectOrd(no);
	}
	
	public void updateOrdSta(String no, String sta) {
		ordMapper.updateOrdSta(no, sta);
	}
}
