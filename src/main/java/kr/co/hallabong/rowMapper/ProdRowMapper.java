package kr.co.hallabong.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.util.Pair;

@Component
public class ProdRowMapper implements RowMapper<Pair<ProdBean, Integer>> {

	@Override
	public Pair<ProdBean, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdBean bean = new ProdBean();
		bean.setNo(rs.getString("no"));
		bean.setFs(rs.getString("fs").charAt(0));
		bean.setName(rs.getString("name"));
		bean.setCost(rs.getInt("cost"));
		bean.setSp(rs.getInt("sp"));
		bean.setS_img(rs.getString("s_img"));
		bean.setL_img(rs.getString("l_img"));
		bean.setCat_no(rs.getString("cat_no"));
		bean.setReg_tm(rs.getString("reg_tm").substring(0, 10));
		
		return new Pair<>(bean, rowNum);
	}
	
}
