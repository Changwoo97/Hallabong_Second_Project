package kr.co.hallabong.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.PageBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.RevBean;
import kr.co.hallabong.dao.ProdDAO;


@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class ProdService {
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private ProdDAO prodDAO;
		
	public List<ProdBean> getProdList() {
		return prodDAO.selectProdList();
	}
	
	
	public ProdBean getProd(String prod_No) {
		return prodDAO.selectProd(prod_No);
	}
	
	public void addProd(ProdBean bean) {
		prodDAO.insertProd(bean);
	}
	
	public void setProd(ProdBean bean) {
		prodDAO.updateProd(bean);
	}
	
	public void removeProd(String no) {
		prodDAO.deleteProd(no);
	}
	
	public List<ProdBean> searchProductList(String name) {
		return prodDAO.searchProductList(name);
	}
	
	public List<RevBean> getReviewList(String prod_no, int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		List<RevBean> ReviewList = prodDAO.getReviewList(prod_no, rowBounds);
		return ReviewList;		
	}
	
	public PageBean getReviewCnt(String prod_no, int currentPage) {
		       
		int content_cnt = prodDAO.getReviewCnt(prod_no);
		
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageBean;
	}
	
	public List<ProdBean> getNewProdList() {
		return prodDAO.getNewProdList();
	}

}
