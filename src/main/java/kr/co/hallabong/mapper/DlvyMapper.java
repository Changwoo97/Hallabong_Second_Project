package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.DlvyBean;

public interface DlvyMapper {
	@Select("SELECT no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no\n" + 
			"FROM dlvy\n" +
			"WHERE no = #{no} ")
	DlvyBean selectDlvy(String no);
	
	@Select("SELECT no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no\n" + 
			"FROM dlvy\n" +
			"WHERE ord_no = #{ord_no} ")
	DlvyBean selectDlvyByOrdNo(String ord_no);
	
	@Select("SELECT no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no\n" + 
			"FROM dlvy\n" + 
			"WHERE sta = #{sta}\n" + 
			"ORDER BY reg_tm DESC")
	List<DlvyBean> selectDlvyList(String sta);
	
	@Insert("INSERT INTO dlvy (no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no)\n" + 
			"VALUES (DEFAULT, #{send_name}, #{send_tel}, #{send_addr}, #{recv_name}, #{recv_tel}, #{recv_addr}, #{fee}, DEFAULT, NULL, NULL, 'READY', #{ord_no}) ")
	void insertDlvy(DlvyBean bean);
	
	@Update("UPDATE dlvy\n"
			+ "SET sta = #{sta}\n"
			+ "WHERE no = #{no} ")
	void updateDlvySta(@Param("no") String no, @Param("sta") String sta);
	
	@Update("UPDATE dlvy\n"
			+ "SET dep_tm = DEFAULT\n"
			+ "WHERE no = #{no} ")
	void updateDlvyDep_tm(String no);
	
	@Update("UPDATE dlvy\n"
			+ "SET arr_tm = DEFAULT\n"
			+ "WHERE no = #{no} ")
	void updateDlvyArr_tm(String no);
}
