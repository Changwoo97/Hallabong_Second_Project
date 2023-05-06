package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.join.ProdCatBean;
import kr.co.hallabong.mapper.CatMapper;

@Repository
public class CatDAO {
	@Autowired
	private CatMapper catMapper;
	
	public List<CatBean> selectCatList() {
		return catMapper.selectCatList();
	}
	
	public CatBean selectCat(String no) {
		return catMapper.selectCat(no);
	}

	public void insertCat(CatBean bean) {
		catMapper.insertCat(bean);
	}
	
	public void updateCat(CatBean bean) {
		catMapper.updateCat(bean);
	}

	public void deleteCat(String no) {
		catMapper.deleteCat(no);
	}
	
	public List<ProdBean> getCatMainPage(String cat_no) {
		return catMapper.getCatMainPage(cat_no);
	}
}
