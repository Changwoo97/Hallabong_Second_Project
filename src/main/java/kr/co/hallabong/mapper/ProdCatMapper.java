package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.join.ProdCatBean;

public interface ProdCatMapper {
	@Select("WITH\n" +
			"    pc AS (SELECT p.no\n" + 
			"                , p.fs\n" +
			"                , p.name\n" +
			"                , p.cost\n" +
			"                , p.sp\n" +
			"                , p.s_img\n" +
			"                , p.l_img\n" +
			"                , p.cat_no\n" +
			"                , c.name AS cat_name\n" +
			"                , p.reg_tm\n" +
			"           FROM prod p, cat c\n" +
			"           WHERE p.cat_no = c.no\n" +
			"           ORDER BY reg_tm DESC)\n" +
			"SELECT no\n" + 
			"     , fs\n" +
			"     , name\n" +
			"     , cost\n" +
			"     , sp\n" +
			"     , s_img\n" +
			"     , l_img\n" +
			"     , cat_no\n" +
			"     , cat_name\n" +
			"     , TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm\n" +
			"FROM pc")
	List<ProdCatBean> selectProdCatList();
}
