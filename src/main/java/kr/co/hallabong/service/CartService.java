package kr.co.hallabong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.dao.CartDAO;

@Service
public class CartService {
	
    @Autowired
    private CartDAO cartDAO;
 
    public List<CartBean> getCartList(String cust_id) {
		return cartDAO.getCartList(cust_id);
	}
    
}



