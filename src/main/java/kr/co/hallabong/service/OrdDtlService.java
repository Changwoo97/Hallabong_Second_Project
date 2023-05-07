package kr.co.hallabong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.OrdDtlBean;
import kr.co.hallabong.dao.OrdDtlDAO;

@Service
public class OrdDtlService {
	@Autowired
	private OrdDtlDAO ordDtlDAO;
	
	public void addOrdDtl(OrdDtlBean bean) {
		ordDtlDAO.insertOrdDtl(bean);
	}
}
