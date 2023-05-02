package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.ProdBean;

public interface CatMapper {
	@Select("SELECT no, name FROM cat ORDER BY name ")
	List<CatBean> selectCatList();
	
	@Select("SELECT no, name FROM cat WHERE no = #{no} ")
	CatBean selectCat(String no);

	@Insert("INSERT INTO cat(no, name) VALUES(DEFAULT, #{name}) ")
	void insertCat(CatBean bean);
	
	@Update("UPDATE cat SET name = #{name} WHERE no = #{no} ")
	void updateCat(CatBean bean);
	
	@Delete("DELETE FROM cat WHERE no = #{no} ")
	void deleteCat(String no);
	
	@Select("select cat_no, prod.no, cat.name, prod.name, prod.cost, prod.fs as, prod.reg_tm, prod.s_img "
			+ "from cat, prod "
			+ "where cat.no=prod.cat_no and cat_no=#{cat_No} ")
	List<ProdBean> getcatMainPage(String cat_No);
}
