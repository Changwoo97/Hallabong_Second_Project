package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.join.ProdCatBean;
import kr.co.hallabong.dao.ProdCatDAO;

@Service
public class ProdCatService {
	@Autowired
	private ProdCatDAO prodCatDAO;
	
	public List<ProdCatBean> getProdCatList() {
		return prodCatDAO.selectProdCatList();
	}
}
