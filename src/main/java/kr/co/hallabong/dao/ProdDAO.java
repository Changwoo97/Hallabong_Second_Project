package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.RevBean;
import kr.co.hallabong.mapper.ProdMapper;

@Repository
public class ProdDAO {
	@Autowired
	private ProdMapper prodMapper;
	
	public List<ProdBean> selectProdList() {
		return prodMapper.selectProdList();
	}
	
	public ProdBean selectProd(String no) {
		return prodMapper.selectProd(no);
	}
	
	public void insertProd(ProdBean bean) {
		prodMapper.insertProd(bean);
	}
	
	public void updateProd(ProdBean bean) {
		prodMapper.updateProd(bean);
	}
	
	public void deleteProd(String no) {
		prodMapper.deleteProd(no);
	}
	
	public List<ProdBean> searchProductList(String name) {
		List<ProdBean> searchProductList = prodMapper.searchProductList(name);
		return searchProductList;
	}
	
	public List<ProdBean> getProdInfoPage(String prod_No) {
		List<ProdBean> ProdInfoPage = prodMapper.getProdInfoPage(prod_No);
		return ProdInfoPage;
	}
	
	public List<RevBean> getReviewList(String prod_No) {
		List<RevBean> ReviewList = prodMapper.getReviewList(prod_No);
		return ReviewList;
	}
}
