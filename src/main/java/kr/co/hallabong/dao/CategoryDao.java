package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.CategoryInfoBean;
import kr.co.hallabong.mapper.CategoryMapper;

@Repository
public class CategoryDao {

	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<CategoryInfoBean> getCategoryMenuList(){
		List<CategoryInfoBean> categoryInfoList = categoryMapper.getCategoryInfoList();
		
		return categoryInfoList;
	} 
	
}
