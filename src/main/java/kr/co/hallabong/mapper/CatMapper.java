package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.join.ProdCatBean;

public interface CatMapper {
	@Select("SELECT no, name FROM cat ORDER BY name ")
	List<CatBean> selectCatList();
	
	@Select("SELECT no, name FROM cat WHERE no = #{no} ")
	CatBean selectCat(String no);

	@Insert("INSERT INTO cat (no, name) VALUES (DEFAULT, #{name}) ")
	void insertCat(CatBean bean);
	
	@Update("UPDATE cat SET name = #{name} WHERE no = #{no} ")
	void updateCat(CatBean bean);
	
	@Delete("DELETE FROM cat WHERE no = #{no} ")
	void deleteCat(String no);
	
	@Select("SELECT p.no                            AS no       " + 
			"     , p.fs                            AS fs       " + 
			"     , p.name                          AS name     " + 
			"     , p.cost                          AS cost     " + 
			"     , p.sp                            AS sp       " + 
			"     , p.s_img                         AS s_img    " + 
			"     , p.l_img                         AS l_img    " + 
			"     , c.no                            AS cat_no   " + 
			"     , c.name                          AS cat_name " + 
			"     , TO_CHAR(p.reg_tm, 'YYYY-MM-DD') AS reg_tm   " + 
			"FROM prod p INNER JOIN cat c ON p.cat_no = c.no    " + 
			"WHERE cat_no = #{cat_no} AND p.fs = 'Y'            ")
	List<ProdCatBean> getCatMainPage(String cat_no);
}
