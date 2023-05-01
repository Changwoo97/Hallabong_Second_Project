package kr.co.hallabong.controller.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;
import oracle.sql.DATE;

@Controller
@RequestMapping("/admin/stlm")
public class AdminStlmController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/check")
	public String check(Model model,
			@ModelAttribute("ord_no") String ord_no, 
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate, 
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate, 
			@ModelAttribute("stlm_tmBeginDate") String stlm_tmBeginDate, 
			@ModelAttribute("stlm_tmEndDate") String stlm_tmEndDate) {		
		System.out.println("aa " + reg_tmBeginDate);
		if (reg_tmBeginDate.length() + reg_tmEndDate.length()
			+ stlm_tmBeginDate.length() + stlm_tmEndDate.length() <= 0) {
			LocalDateTime today = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			reg_tmBeginDate = today.minusMonths(1).format(formatter);
			reg_tmEndDate = today.format(formatter);
			
			model.addAttribute("reg_tmBeginDate", reg_tmBeginDate);
			model.addAttribute("reg_tmEndDate", reg_tmEndDate);
		}
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=신청일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=정산일&type=date&name=stlm_tm"));
		thead.add(Format.getMap("title=배송료"));
		thead.add(Format.getMap("title=차감배송료"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매금액"));
		thead.add(Format.getMap("title=순수익"));
	
		List<List<String>> tbody = new ArrayList<>();
		List<String> tfoot = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("WITH                                                                    ");
		sql.append("    ORD_DLVY AS (SELECT ord.no                 AS ord_no                ");
		sql.append("                      , ord.reg_tm             AS reg_tm                ");
		sql.append("                      , ord.stlm_tm            AS stlm_tm               ");
		sql.append("                      , dlvy.fee               AS fee                   ");
		sql.append("                      , ord.dlvy_fee           AS deducted_fee          ");
		sql.append("                      , SUM(ord_dtl.prod_cost) AS cost                  ");
		sql.append("                      , SUM(ord_dtl.prod_sp)   AS sp                    ");
		sql.append("                 FROM ord INNER JOIN ord_dtl ON ord.no = ord_dtl.ord_no ");
		sql.append("                          INNER JOIN dlvy    ON ord.no = dlvy.ord_no    ");
		sql.append("                 WHERE ord.sta = 'COMPLETE'                             ");
		sql.append("                 GROUP BY ord.no                                        ");
		sql.append("                        , ord.reg_tm                                    ");
		sql.append("                        , ord.stlm_tm                                   ");
		sql.append("                        , dlvy.fee                                      ");
		sql.append("                        , ord.dlvy_fee                                  ");
		sql.append("                 ORDER BY reg_tm DESC)                                  ");
		sql.append("SELECT ord_no                                                           ");
		sql.append("     , TO_CHAR(reg_tm, 'YYYY-MM-DD')  AS reg_tm                         ");
		sql.append("     , TO_CHAR(stlm_tm, 'YYYY-MM-DD') AS stlm_tm                        ");
		sql.append("     , fee                                                              ");
		sql.append("     , deducted_fee                                                     ");
		sql.append("     , cost                                                             ");
		sql.append("     , sp                                                               ");
		sql.append("     , cost - sp - fee + deducted_fee AS net_income                     ");
		sql.append("FROM ORD_DLVY                                                           ");

		List<Map<String, String>> selectResults = jdbcTemplate.query(sql.toString(), new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> map = new HashMap<>();
				map.put("ord_no", rs.getString("ord_no"));
				map.put("reg_tm", rs.getString("reg_tm"));
				map.put("stlm_tm", rs.getString("stlm_tm"));
				map.put("fee", rs.getString("fee"));
				map.put("deducted_fee", rs.getString("deducted_fee"));
				map.put("cost", rs.getString("cost"));
				map.put("sp", rs.getString("sp"));
				map.put("net_income", rs.getString("net_income"));
				
				return map;
			}
		});
		
		for (int i = selectResults.size() - 1; i >= 0; i--) {
			Map<String, String> selectResult = selectResults.get(i);
			if ((ord_no.isBlank() || selectResult.get("ord_no").contains(ord_no))
					&& (reg_tmBeginDate.isBlank() || selectResult.get("reg_tm").compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || selectResult.get("reg_tm").compareTo(reg_tmEndDate) <= 0)
					&& (stlm_tmBeginDate.isBlank() || selectResult.get("stlm_tm").compareTo(stlm_tmBeginDate) >= 0)
					&& (stlm_tmEndDate.isBlank() || selectResult.get("stlm_tm").compareTo(stlm_tmEndDate) <= 0))
				continue;
			selectResults.remove(selectResult);
		}

		if (selectResults.size() > 0) {
			Integer feeSum = 0, deducted_feeSum = 0, costSum = 0, spSum = 0, net_incomeSum = 0;
			for (Map<String, String> selectResult : selectResults) {
				List<String> row = new ArrayList<>();
				
				row.add(selectResult.get("ord_no"));
				row.add(selectResult.get("reg_tm"));
				row.add(selectResult.get("stlm_tm"));
				row.add(selectResult.get("fee"));
				row.add(selectResult.get("deducted_fee"));
				row.add(selectResult.get("cost"));
				row.add(selectResult.get("sp"));
				row.add(selectResult.get("net_income"));
				
				try { feeSum += Integer.parseInt(selectResult.get("fee")); } catch(Exception e) {}
				try { deducted_feeSum += Integer.parseInt(selectResult.get("deducted_fee")); } catch(Exception e) {}
				try { costSum += Integer.parseInt(selectResult.get("cost")); } catch(Exception e) {}
				try { spSum += Integer.parseInt(selectResult.get("sp")); } catch(Exception e) {}
				try { net_incomeSum += Integer.parseInt(selectResult.get("net_income")); } catch(Exception e) {}
				
				tbody.add(row);
			}
			
			tfoot.add("");
			tfoot.add("");
			tfoot.add("합계");
			tfoot.add(feeSum.toString());
			tfoot.add(deducted_feeSum.toString());
			tfoot.add(costSum.toString());
			tfoot.add(spSum.toString());
			tfoot.add(net_incomeSum.toString());
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
			
			tfoot.add("");
			tfoot.add("");
			tfoot.add("합계");
			tfoot.add("배송료");
			tfoot.add("차감배송료");
			tfoot.add("원가");
			tfoot.add("판매금액");
			tfoot.add("순수익");
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("stlm_tmBeginDate", stlm_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("stlm_tmEndDate", stlm_tmEndDate));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "정산조회");
		model.addAttribute("pageSize", 0);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		model.addAttribute("tfoot", tfoot);
		return "admin/admin";
	}
}
