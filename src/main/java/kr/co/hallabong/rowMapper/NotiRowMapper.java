package kr.co.hallabong.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.util.Pair;

@Component
public class NotiRowMapper implements RowMapper<Pair<NotiBean, Integer>> {

	@Override
	public Pair<NotiBean, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
		NotiBean bean = new NotiBean();
		bean.setNo(rs.getString("no"));
		bean.setTit(rs.getString("tit"));
		bean.setCont(rs.getString("cont"));
		bean.setReg_tm(rs.getString("reg_tm").substring(0, 10));
		
		return new Pair<>(bean, rowNum);
	}
	
}
