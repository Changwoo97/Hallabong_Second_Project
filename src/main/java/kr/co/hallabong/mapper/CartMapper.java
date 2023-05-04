package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.CartBean;

public interface CartMapper {
	@Select("SELECT * FROM cart WHERE cust_id = #{cust_id} ")
	List<CartBean> getCartList(String cust_id);
	
	@Delete(" delete from CART where cust_id = #{cust_id} AND prod_no = #{prod_no} ")
	void deleteCart(@Param("cust_id") String cust_id, @Param("prod_no") String prod_no);
}
