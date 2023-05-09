package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.RevBean;

public interface ProdMapper {
	@Select("WITH\n" +
			"    p AS (SELECT no\n" +
			"               , fs\n" +
			"               , name\n" +
			"               , cost\n" +
			"               , sp\n" +
			"               , s_img\n" +
			"               , l_img\n" +
			"               , cat_no\n" +
			"               , reg_tm\n" +
			"          FROM prod\n" +
			"          ORDER BY reg_tm DESC)\n" +
			"SELECT no\n" + 
			"     , fs\n" +
			"     , name\n" +
			"     , cost\n" +
			"     , sp\n" +
			"     , s_img\n" +
			"     , l_img\n" +
			"     , cat_no\n" +
			"     , TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm\n" +
			"FROM p ")
	List<ProdBean> selectProdList();
	
	/*public interface ProdMapper {
		@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm "
				+ "FROM (SELECT * FROM prod ORDER BY reg_tm DESC) ")
		List<ProdBean> selectProdList();*/
		
	@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm\n" + 
			"FROM prod\n" + 
			"WHERE no = #{prod_no} ")
	ProdBean selectProd(String prod_no);
	
	/*@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm "
			+ "FROM prod "
			+ "WHERE no = #{prod_No} ")
	ProdBean selectProd(String prod_No);
	*/
	@Insert("INSERT INTO prod (no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm)\n"
			+ "VALUES (DEFAULT, #{fs}, #{name}, #{cost}, #{sp}, #{s_img}, #{l_img}, #{cat_no}, DEFAULT) ")
	void insertProd(ProdBean bean);
	
	@Update("UPDATE prod\n"
			+ "SET fs = #{fs}, name = #{name}, cost = #{cost}, sp = #{sp}, s_img = #{s_img}, l_img = #{l_img}, cat_no = #{cat_no}\n"
			+ "WHERE no = #{no} ")
	void updateProd(ProdBean bean);
	
	@Delete("DELETE FROM prod WHERE no = ${no} ")
	void deleteProd(String no);
	
	   @Select(" SELECT * "
		         + "  FROM prod "
		         + "  WHERE LOWER(name) LIKE '%' || LOWER(#{name}) || '%'")
		   List<ProdBean> searchProductList(String name);
	
	
	@Select("select r.no, r.cust_id, r.no, r.cont, r.reg_tm "
			+ "from rev r, prod p "
			+ "where r.prod_no = p.no and p.no = #{prod_no} ")
	List<RevBean> getReviewList(String prod_no, RowBounds rowBounds);

	@Select("select count(*) "
			+ "from rev r, prod p "
			+ "where r.prod_no = p.no and p.no = #{prod_no} " )
	int getReviewCnt(String prod_no);
	
	@Select("SELECT no, name, cost, s_img, TO_DATE(reg_tm, 'YYYY-MM-DD') AS reg_tm "
			+ "FROM prod "
			+ "WHERE ROWNUM <= 6 "
			+ "ORDER BY reg_tm DESC ")
	List<ProdBean> getNewProdList();
	
	@Select("SELECT p.no, p.name, p.cost, p.s_img, COUNT(r.cust_id) as review_count "
			+ "FROM prod p "
			+ "LEFT JOIN rev r ON p.no = r.prod_no "
			+ "GROUP BY p.no, p.fs, p.name, p.cost, p.sp, p.s_img, p.l_img, p.cat_no, p.reg_tm "
			+ "ORDER BY review_count DESC ")
	List<ProdBean> getRevProdList();
}