package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.hallabong.bean.CatBean;

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
}