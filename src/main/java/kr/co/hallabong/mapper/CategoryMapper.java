package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.CategoryInfoBean;

public interface CategoryMapper {

	@Select("select category_info_idx, category_info_name " +
			"from category_info_table " + 
			"order by board_info_idx")
	
	List<CategoryInfoBean> getCategoryInfoList();

}
