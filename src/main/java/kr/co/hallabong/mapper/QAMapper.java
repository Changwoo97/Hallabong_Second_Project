package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.QABean;

public interface QAMapper {
	@Select("SELECT no, cust_id, q, TO_CHAR(q_reg_tm, 'YYYY-MM-DD') AS q_reg_tm, a, TO_CHAR(a_reg_tm, 'YYYY-MM-DD') AS a_reg_tm, sta\n"
			+ "FROM (SELECT * FROM qa WHERE sta = #{sta} ORDER BY q_reg_tm DESC) ")
	List<QABean> selectQAList(String sta);
	
	@Select("SELECT no, cust_id, q, TO_CHAR(q_reg_tm, 'YYYY-MM-DD') AS q_reg_tm, a, TO_CHAR(a_reg_tm, 'YYYY-MM-DD') AS a_reg_tm, sta\n"
			+ "FROM qa\n"
			+ "WHERE no = #{no} ")
	QABean selectQA(String no);
	
	@Insert("INSERT INTO qa (no, cust_id, q, q_reg_tm, a, a_reg_tm, sta)\n"
			+ "VALUES (DEFAULT, #{cust_id}, #{q}, DEFAULT, NULL, NULL, 'REQUEST') ")
	void insertQA(QABean bean);
	
	@Update("UPDATE qa "
			+ "SET q = #{q}, q_reg_tm = DEFAULT "
			+ "WHERE no = #{no} ")
	void updateQ(QABean bean);
	
	@Update("UPDATE qa\n"
			+ "SET a = #{a}, a_reg_tm = DEFAULT, sta = 'COMPLETE'\n"
			+ "WHERE no = #{no} ")
	void updateA(QABean bean);
	
	@Delete("DELETE FROM qa\n"
			+ "WHERE no = #{no} ")
	void deleteQA(String no);
	
	@Select("SELECT no, cust_id, q, TO_CHAR(q_reg_tm, 'YYYY-MM-DD HH24:mi:ss') AS q_reg_tm, a, TO_CHAR(a_reg_tm, 'YYYY-MM-DD HH24:mi:ss') AS a_reg_tm, sta " +
			" FROM qa WHERE cust_id= #{cust_id} ORDER BY q_reg_tm DESC ")
	List<QABean> getQAList(String cust_id);
}
