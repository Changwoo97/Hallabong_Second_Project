package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.dao.CatDAO;

@Service
public class CatService {
	@Autowired
	private CatDAO catDAO;
	
	public List<CatBean> getCatList() {
		return catDAO.selectCatList();
	}
	
	public CatBean getCat(String no) {
		return catDAO.selectCat(no);
	}

	public void addCat(CatBean bean) {
		catDAO.insertCat(bean);
	}
	
	public void setCat(CatBean bean) {
		catDAO.updateCat(bean);
	}

	public void removeCat(String no) {
		catDAO.deleteCat(no);
	}
	
	public List<ProdBean> getcatMainPage(String Cat_No) {
		List<ProdBean> catMainPage = catDAO.getcatMainPage(Cat_No);
		return catMainPage;
	}
}
