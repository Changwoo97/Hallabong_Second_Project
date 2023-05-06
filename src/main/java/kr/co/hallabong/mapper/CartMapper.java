package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CartBean;

public interface CartMapper {
	@Select("SELECT * FROM cart WHERE cust_id = #{cust_id} ")
	List<CartBean> getCartList(String cust_id);
	
	@Insert("INSERT INTO cart (cust_id, prod_no, qnty, reg_tm)\n" + 
			"VALUES (#{cust_id}, #{prod_no}, #{qnty}, DEFAULT) ")
	int insertCart(CartBean bean);
	
	@Update("UPDATE cart\n" +
			"SET qnty = #{qnty}\n" +
			"WHERE cust_id = #{cust_id} AND prod_no = #{prod_no} ")
	int updateCart(CartBean bean);
	
	@Delete("DELETE FROM cart " + 
			"WHERE cust_id = #{cust_id} AND prod_no = #{prod_no} ")
	void deleteCart(@Param("cust_id") String cust_id, @Param("prod_no") String prod_no);
}
