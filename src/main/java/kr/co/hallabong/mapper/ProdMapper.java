package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.RevBean;

public interface ProdMapper {
	@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm "
			+ "FROM (SELECT * FROM prod ORDER BY reg_tm DESC) ")
	List<ProdBean> selectProdList();
	
	@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm "
			+ "FROM prod "
			+ "WHERE no = #{no} ")
	ProdBean selectProd(String no);
	
	@Insert("INSERT INTO prod (no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm) "
			+ "VALUES (DEFAULT, #{fs}, #{name}, #{cost}, #{sp}, #{s_img}, #{l_img}, #{cat_no}, DEFAULT) ")
	void insertProd(ProdBean bean);
	
	@Update("UPDATE prod "
			+ "SET fs = #{fs}, name = #{name}, cost = #{cost}, sp = #{sp}, s_img = #{s_img}, l_img = #{l_img}, cat_no = #{cat_no} "
			+ "WHERE no = #{no} ")
	void updateProd(ProdBean bean);
	
	@Delete("DELETE FROM prod "
			+ "WHERE no = ${no} ")
	void deleteProd(String no);
	
	@Select("select * "+
			"from prod " +
			"where name LIKE '%' || #{name} || '%'")
	List<ProdBean> searchProductList(String name);
	
	@Select("select * "
			+ "from prod "
			+ "where no=#{prod_No} ")
	List<ProdBean> getProdInfoPage(String prod_No);
	
	@Select("select p.no, r.no, r.cont, r.reg_tm "
			+ "from rev r, prod p "
			+ "where r.prod_no = p.no and p.no = #{prod_No} ")
	List<RevBean> getReviewList(String prod_No);
}
