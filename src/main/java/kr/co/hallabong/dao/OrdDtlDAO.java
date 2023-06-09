package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.OrdDtlBean;
import kr.co.hallabong.mapper.OrdDtlMapper;

@Repository
public class OrdDtlDAO {
	@Autowired
	private OrdDtlMapper ordDtlMapper;
	
	public void insertOrdDtl(OrdDtlBean bean) {
		ordDtlMapper.insertOrdDtl(bean);
	}
	
	public List<OrdDtlBean> selectOrdDtlList(String ord_no) {
		return ordDtlMapper.selectOrdDtlList(ord_no);
	}
}
