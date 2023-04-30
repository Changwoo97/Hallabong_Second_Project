package kr.co.hallabong.mapper;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.AdminBean;

public interface AdminMapper {
	@Select("SELECT COUNT(*) "
			+ "FROM admin "
			+ "WHERE id = #{id} AND pw = #{pw} ")
	int selectAdminCount(AdminBean bean);
}
