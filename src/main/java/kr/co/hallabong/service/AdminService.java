package kr.co.hallabong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.AdminBean;
import kr.co.hallabong.dao.AdminDAO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;
	
	public boolean isExist(AdminBean bean) {
		return adminDAO.selectAdminCount(bean) > 0 ? true : false;
	}
}
