package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.join.ProdCatBean;
import kr.co.hallabong.mapper.ProdCatMapper;

@Repository
public class ProdCatDAO {
	@Autowired
	private ProdCatMapper prodCatMapper;
	
	public List<ProdCatBean> selectProdCatList() {
		return prodCatMapper.selectProdCatList();
	}
}
