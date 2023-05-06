package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.NotiBean;

public interface NotiMapper {
	@Select("SELECT no, tit, cont, TO_CHAR(reg_tm, 'YYYY-MM_DD') AS reg_tm\n" + 
			"FROM (SELECT * FROM noti ORDER BY reg_tm DESC) ")
	public List<NotiBean> selectNotiList();
	
	@Select("SELECT no, tit, cont, TO_CHAR(reg_tm, 'YYYY-MM_DD') AS reg_tm\n" + 
			"FROM noti\n" + 
			"WHERE no = #{no} ")
	public NotiBean selectNoti(String no);
	
	@Insert("INSERT INTO noti (no, tit, cont, reg_tm)\n" + 
			"VALUES (DEFAULT, #{tit}, #{cont}, DEFAULT) ")
	public void insertNoti(NotiBean bean);
	
	@Update("UPDATE noti\n" + 
			"SET tit = #{tit}, cont = #{cont}\n" + 
			"WHERE no = #{no} ")
	public void updateNoti(NotiBean bean);
	
	@Delete("DELETE FROM noti \n" + 
			"WHERE no = #{no} ")
	public void deleteNoti(String no);
}
