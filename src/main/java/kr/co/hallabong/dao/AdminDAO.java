package kr.co.hallabong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.AdminBean;
import kr.co.hallabong.mapper.AdminMapper;

@Repository
public class AdminDAO {
	@Autowired
	private AdminMapper adminMapper;
	
	public int selectAdminCount(AdminBean bean) {
		return adminMapper.selectAdminCount(bean);
	}
}
