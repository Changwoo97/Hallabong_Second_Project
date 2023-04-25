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
		ProdBean prodBean = new ProdBean();
		prodBean.setNo(rs.getString("no"));
		prodBean.setFs(rs.getString("fs").charAt(0));
		prodBean.setName(rs.getString("name"));
		prodBean.setCost(rs.getInt("cost"));
		prodBean.setSp(rs.getInt("sp"));
		prodBean.setS_img(rs.getString("s_img"));
		prodBean.setL_img(rs.getString("l_img"));
		prodBean.setCat_no(rs.getString("cat_no"));
		prodBean.setReg_tm(rs.getString("reg_tm"));
		
		return new Pair<>(prodBean, rowNum);
	}
	
}
