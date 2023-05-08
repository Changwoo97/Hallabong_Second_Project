package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.OrdDtlBean;

public interface OrdDtlMapper {
	@Insert("INSERT INTO ord_dtl (ord_no, prod_no, prod_cost, prod_sp, prod_qnty) \n" +
			"VALUES (#{ord_no}, #{prod_no}, #{prod_cost}, #{prod_sp}, #{prod_qnty}) ")
	void insertOrdDtl(OrdDtlBean bean);
	
	@Select("SELECT ord_no, prod_no, prod_cost, prod_sp, prod_qnty\n" +
			"FROM ord_dtl\n" + 
			"WHERE ord_no = #{ord_no} ")
	List<OrdDtlBean> selectOrdDtlList(String ord_no);
}
