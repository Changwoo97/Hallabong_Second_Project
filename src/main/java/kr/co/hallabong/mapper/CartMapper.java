package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.CartBean;

public interface CartMapper {
	@Select("SELECT * FROM cart WHERE cust_id = #{cust_id} ")
	List<CartBean> getCartList(String cust_id);
}
