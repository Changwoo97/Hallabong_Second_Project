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
		CatBean catBean = new CatBean();
		catBean.setNo(rs.getString("no"));
		catBean.setName(rs.getString("name"));
		
		return new Pair<>(catBean, rowNum);
	}
	
}
