package kr.co.hallabong.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.util.Pair;

@Component
public class CatRowMapper implements RowMapper<Pair<CatBean, Integer>> {

	@Override
	public Pair<CatBean, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatBean bean = new CatBean();
		bean.setNo(rs.getString("no"));
		bean.setName(rs.getString("name"));
		
		return new Pair<>(bean, rowNum);
	}
	
}
