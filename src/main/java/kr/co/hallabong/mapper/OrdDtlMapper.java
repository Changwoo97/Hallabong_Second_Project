package kr.co.hallabong.mapper;

import org.apache.ibatis.annotations.Insert;

import kr.co.hallabong.bean.OrdDtlBean;

public interface OrdDtlMapper {
	@Insert("INSERT INTO ord_dtl (ord_no, prod_no, prod_cost, prod_sp, prod_qnty) \n" +
			"VALUES (#{ord_no}, #{prod_no}, #{prod_cost}, #{prod_sp}, #{prod_qnty}) ")
	void insertOrdDtl(OrdDtlBean bean);
}
