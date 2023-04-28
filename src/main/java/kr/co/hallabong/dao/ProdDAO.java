package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.ProdBean;
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
}
