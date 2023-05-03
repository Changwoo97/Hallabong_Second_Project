package kr.co.hallabong.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
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
	
	/*public ProdBean selectProd(String no) {
		return prodMapper.selectProd(no);
	}*/
	
	public ProdBean selectProd(String prod_No) {
		return prodMapper.selectProd(prod_No);
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
	
	
	public List<RevBean> getReviewList(String prod_No, RowBounds rowBounds) {
		List<RevBean> ReviewList = prodMapper.getReviewList(prod_No, rowBounds);
		return ReviewList;
	}
	
	public int getReviewCnt(String Prod_No) {
		return prodMapper.getReviewCnt(Prod_No);
	}
	
	public List<ProdBean> getNewProdList() {
		return prodMapper.getNewProdList();
	}
	
}
