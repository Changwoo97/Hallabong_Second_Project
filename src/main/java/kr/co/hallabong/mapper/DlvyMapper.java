package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.DlvyBean;

public interface DlvyMapper {
	@Select("SELECT no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no " + 
			"FROM dlvy " +
			"WHERE no = #{no} ")
	DlvyBean selectDlvy(String no);
	
	@Select("SELECT no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no "
			+ "FROM dlvy "
			+ "WHERE sta = #{sta} ")
	List<DlvyBean> selectDlvyList(String sta);
	
	@Insert("INSERT INTO dlvy (no, send_name, send_tel, send_addr, recv_name, recv_tel, recv_addr, fee, reg_tm, dep_tm, arr_tm, sta, ord_no) "
			+ "VALUES (DEFAULT, #{send_name}, #{send_tel}, #{send_addr}, #{recv_name}, #{recv_tel}, #{recv_addr}, #{fee}, DEFAULT, NULL, NULL, 'READY', #{ord_no}) ")
	void insertDlvy(DlvyBean bean);
	
	@Update("UPDATE dlvy "
			+ "SET sta = #{sta} "
			+ "WHERE no = #{no} ")
	void updateDlvySta(@Param("no") String no, @Param("sta") String sta);
	
	@Update("UPDATE dlvy "
			+ "SET dep_tm = DEFAULT "
			+ "WHERE no = #{no} ")
	void updateDlvyDep_tm(String no);
	
	@Update("UPDATE dlvy "
			+ "SET arr_tm = DEFAULT "
			+ "WHERE no = #{no} ")
	void updateDlvyArr_tm(String no);
}
