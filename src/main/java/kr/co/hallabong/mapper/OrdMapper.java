package kr.co.hallabong.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.OrdBean;

public interface OrdMapper {
	@Select("SELECT no, cust_id, type, ordr_name, ordr_tel, ordr_addr, recv_name, recv_tel, recv_addr, pay_meth, dlvy_fee, cont, reg_tm, stlm_tm, sta "
			+ "FROM ord "
			+ "WHERE no = #{no} ")
	OrdBean selectOrd(String no);
	
	@Update("UPDATE ord "
			+ "SET sta = #{sta} "
			+ "WHERE no = #{no} ")
	void updateOrdSta(String no, String sta);
}
