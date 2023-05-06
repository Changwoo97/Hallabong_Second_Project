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
    
    public int addCart(CartBean bean) {
    	List<CartBean> cartList = cartDAO.getCartList(bean.getCust_id());
    	
    	int qnty = 0;
    	for (CartBean cart : cartList) {
    		if (cart.getProd_no().equals(bean.getProd_no())) {
    			qnty += cart.getQnty();
    		}
    	}
    	
    	if (qnty > 0) {
    		bean.setQnty(qnty + bean.getQnty());
    		return cartDAO.updateCart(bean);
    	}
    	
    	return cartDAO.insertCart(bean);
    }
    
    public void deleteCart(String cust_id, String prod_no) {
        cartDAO.deleteCart(cust_id, prod_no);
    }
}



