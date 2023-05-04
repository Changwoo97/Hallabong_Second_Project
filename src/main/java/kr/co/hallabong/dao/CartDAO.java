package kr.co.hallabong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.mapper.CartMapper;

@Repository
public class CartDAO {
	@Autowired
    private CartMapper cartMapper;
  
    public List<CartBean> getCartList(String cust_id) {
		return cartMapper.getCartList(cust_id);			
	}
    
    public void deleteCart(String cust_id, String prod_no){
        cartMapper.deleteCart(cust_id, prod_no);
    }
}
