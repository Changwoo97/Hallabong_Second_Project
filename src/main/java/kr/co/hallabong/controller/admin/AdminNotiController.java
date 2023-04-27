package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.rowMapper.NotiRowMapper;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/noti")
public class AdminNotiController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	NotiRowMapper notiRowMapper;
	
	@GetMapping("/registration") 
	public String registration(@ModelAttribute NotiBean notiBean, Model model) {
		model.addAttribute("content", "/WEB-INF/views/admin/notiDetail.jsp");
		model.addAttribute("frameName", "공지사항 등록");
		model.addAttribute("path", "/admin/noti/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc") 
	public String registration_proc(NotiBean notiBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO noti (no, tit, cont, reg_tm) ");
		sql.append("VALUES (");
		sql.append("DEFAULT, ");
		if (notiBean.getTit() != null) {
			sql.append("'" + notiBean.getTit() + "', ");
		} else {
			sql.append("NULL, ");
		}
		if (notiBean.getCont() != null) {
			sql.append("'" + notiBean.getCont() + "', ");
		} else {
			sql.append("NULL, ");
		}
		sql.append("DEFAULT) ");
		
		int insertResult = jdbcTemplate.update(sql.toString());
		
		if (insertResult > 0) {
			model.addAttribute("message", "공지사항이 등록되었습니다.");
		} else {
			model.addAttribute("message", "공지사항 등록에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
	
	@GetMapping("/check") 
	public String check(HttpServletRequest request, Model model,
			@RequestParam(name = "no", defaultValue = "") String no, 
			@RequestParam(name = "reg_tmBeginDate", defaultValue = "") String reg_tmBeginDate, 
			@RequestParam(name = "reg_tmEndDate", defaultValue = "") String reg_tmEndDate,
			@RequestParam(name = "tit", defaultValue = "") String tit, 
			@RequestParam(name = "cont", defaultValue = "") String cont, 
			@RequestParam(name = "selectedPageNum", defaultValue = "1") String selectedPageNum) {
		final int ROW_SIZE = 2;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=공지번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=제목&type=keyword&name=tit"));
		thead.add(Format.getMap("title=내용&type=keyword&name=cont"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));

		List<List<String>> tbody = new ArrayList<>();

		no = no.trim();
		tit = tit.trim();
		cont = cont.trim();
		if (reg_tmBeginDate.isBlank()) {
			reg_tmBeginDate = reg_tmEndDate;
		}
		if (reg_tmEndDate.isBlank()) {
			reg_tmEndDate = reg_tmBeginDate;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, tit, cont, reg_tm ");
		sql.append("FROM noti ");
		
		if (no.length() + tit.length() + cont.length() 
			+ reg_tmBeginDate.length() + reg_tmEndDate.length() > 0) {
			sql.append("WHERE ");
			if (no.length() > 0) {
				sql.append("no LIKE '%" + no + "%' ");
				sql.append("AND ");
			}
			if (tit.length() > 0) {
				sql.append("tit LIKE '%" + tit + "%' ");
				sql.append("AND ");
			}
			if (cont.length() > 0) {
				sql.append("cont LIKE '%" + cont + "%' ");
				sql.append("AND ");
			}
			if (reg_tmBeginDate.length() > 0 && reg_tmEndDate.length() > 0) {
				sql.append("reg_tm BETWEEN TO_DATE('"+ reg_tmBeginDate +"', 'YYYY-MM-DD') "
						+ "AND TO_DATE('" + reg_tmEndDate + "', 'YYYY-MM-DD') ");
				sql.append("AND ");
			}
			
			int andIndex = sql.lastIndexOf("AND ");
			if (andIndex > -1) {
				sql.replace(andIndex, sql.length(), "");
			}
		}

		int parseSelectedPageNum;
		try {
			parseSelectedPageNum = Integer.parseInt(selectedPageNum);
			parseSelectedPageNum = (parseSelectedPageNum > 0) ? parseSelectedPageNum : 1;
		} catch (Exception e) {
			parseSelectedPageNum = 1;
		}
		
		List<Pair<NotiBean, Integer>> notiBeans = jdbcTemplate.query(sql.toString(), notiRowMapper);
		int pageSize = notiBeans.size() / ROW_SIZE + ((notiBeans.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (parseSelectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (parseSelectedPageNum) * ROW_SIZE;
		
		if (notiBeans.size() > 0) {	
			for (Pair<NotiBean, Integer> notiBean : notiBeans) {
				if (notiBean.getItem2() < startRowNum) {
					continue;
				}
	
				if (endRowNum <= notiBean.getItem2()) {
					break;
				}
				
				List<String> row = new ArrayList<>();
				
				row.add(notiBean.getItem1().getNo());
				row.add(notiBean.getItem1().getTit());
				row.add(notiBean.getItem1().getCont());
				row.add(notiBean.getItem1().getReg_tm());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + notiBean.getItem1().getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/delete_proc\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + notiBean.getItem1().getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"삭제하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		model.addAttribute("searchPath", "/admin/noti/check");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "공지사항 조회");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", parseSelectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(NotiBean notiBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, tit, cont, reg_tm ");
		sql.append("FROM noti ");
		sql.append("WHERE no = '" + notiBean.getNo() + "' ");
		
		List<Pair<NotiBean, Integer>> selectResults = jdbcTemplate.query(sql.toString(), notiRowMapper);
		NotiBean selectResult = selectResults.get(0).getItem1();
		notiBean.setNo(selectResult.getNo());
		notiBean.setTit(selectResult.getTit());
		notiBean.setCont(selectResult.getCont());
		notiBean.setReg_tm(selectResult.getReg_tm());
		
		model.addAttribute("content", "/WEB-INF/views/admin/notiDetail.jsp");
		model.addAttribute("frameName", "공지사항 수정");
		model.addAttribute("path", "/admin/noti/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(NotiBean notiBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE noti ");
		sql.append("SET tit = '" + notiBean.getTit() + "', ");
		sql.append("cont = '" + notiBean.getCont() + "' ");
		sql.append("WHERE no = '" + notiBean.getNo() + "' ");
		
		int updateResult = jdbcTemplate.update(sql.toString());
		if (updateResult > 0) {
			model.addAttribute("message", "공지사항이 수정되었습니다.");
		} else {
			model.addAttribute("message", "공지사항 수정에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
	
	@PostMapping("/delete_proc") 
	public String delete_proc(NotiBean notiBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM noti ");
		sql.append("WHERE no = '" + notiBean.getNo() + "' ");
		
		int deleteResult = jdbcTemplate.update(sql.toString());
		if (deleteResult > 0) {
			model.addAttribute("message", "공지사항이 삭제되었습니다.");
		} else {
			model.addAttribute("message", "공지사항 삭제에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
}
