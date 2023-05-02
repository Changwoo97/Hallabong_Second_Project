package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.ODPDBean;
import kr.co.hallabong.mapper.ODPDMapper;

@Repository
public class ODPDDAO {
	@Autowired
	private ODPDMapper odpdMapper;
	
	public List<ODPDBean> selectODPDList(String sta) {
		return odpdMapper.selectODPDList(sta);
	}
}
