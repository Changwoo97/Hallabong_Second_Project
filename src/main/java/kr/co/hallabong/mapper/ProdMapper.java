package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.ProdBean;

public interface ProdMapper {
	@Select("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm\n"
			+ "FORM prod")
	List<ProdBean> selectProdList();
	
	@Insert("INSERT INTO prod(no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm)\n"
			+ "VALUES (DEFALUT, #{fs}, #{name}, #{cost}, #{sp}, #{s_img}, #{l_img}, #{cat_no}, DEFALUT)")
	void insertProd(ProdBean bean);
}
