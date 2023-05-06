package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.OrdBean;

public interface OrdMapper {
	@Select("SELECT no\n" + 
			"     , cust_id\n" + 
			"     , type\n" + 
			"     , ordr_name\n" + 
			"     , ordr_tel\n" + 
			"     , ordr_addr\n" + 
			"     , recv_name\n" + 
			"     , recv_tel\n" + 
			"     , recv_addr\n" + 
			"     , pay_meth\n" + 
			"     , dlvy_fee\n" + 
			"     , cont\n" + 
			"     , reg_tm\n" + 
			"     , stlm_tm\n" + 
			"     , sta\n" + 
			"FROM ord\n" + 
			"WHERE no = #{no} ")
	OrdBean selectOrd(String no);
	
	@Update("UPDATE ord\n" + 
			"SET sta = #{sta}\n" + 
			"WHERE no = #{no} ")
	void updateOrdSta(@Param("no") String no, @Param("sta") String sta);
	
	@Select("SELECT * FROM ord WHERE cust_id = #{cust_id} ")
	 List<OrdBean> getOrdList(String cust_id);
}
