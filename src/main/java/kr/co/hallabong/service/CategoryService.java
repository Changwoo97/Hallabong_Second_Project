package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.dao.CategoryDao;
import kr.co.hallabong.bean.CategoryInfoBean;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryInfoBean> getCategoryMenuList(){
		List<CategoryInfoBean> categoryInfoList = categoryDao.getCategoryMenuList();
		
		return categoryInfoList;
	}

}
