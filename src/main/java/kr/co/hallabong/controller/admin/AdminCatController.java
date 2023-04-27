package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/cat")
public class AdminCatController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RowMapper<Pair<CatBean, Integer>> catRowMapper;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 등록");
		model.addAttribute("path", "/admin/cat/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(CatBean catBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cat (no, name) ");
		sql.append("VALUES (DEFAULT, ?) ");
		int result = jdbcTemplate.update(sql.toString(), catBean.getName());
		
		if (result > 0) {
			model.addAttribute("message", "카테고리가 등록되었습니다.");
		} else {
			model.addAttribute("message", "카테고리가 등록에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@GetMapping("/check")
	public String check(HttpServletRequest request, String no, String name, 
				@ModelAttribute("selectedPageNum") String selectedPageNum, Model model) {
		final int ROW_SIZE = 2;

		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=카테고리 번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=카테고리 이름&type=keyword&name=name"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		no = (no == null) ? "" : no.trim();
		name = (name == null) ? "" : name.trim();
		if (no.length() + name.length() > 0) {
			sql.append("WHERE ");
			if (no.length() > 0) {
				sql.append("no LIKE '%" + no + "%' ");
				sql.append("AND ");
			}
			if (name.length() > 0) {
				sql.append("name LIKE '%" + name + "%' ");
				sql.append("AND ");
			}
			
		}
		
		int andIndex = sql.lastIndexOf("AND ");
		if (andIndex > -1) {
			sql.replace(andIndex, sql.length(), "");
		}
		
		int parseSelectedPageNum;
		try {
			parseSelectedPageNum = Integer.parseInt(selectedPageNum);
			parseSelectedPageNum = (parseSelectedPageNum > 0) ? parseSelectedPageNum : 1;
		} catch (Exception e) {
			parseSelectedPageNum = 1;
		}

		List<Pair<CatBean, Integer>> catBeans = jdbcTemplate.query(sql.toString(), catRowMapper);
		int pageSize = catBeans.size() / ROW_SIZE + ((catBeans.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (parseSelectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (parseSelectedPageNum) * ROW_SIZE;

		for (Pair<CatBean, Integer> catBean : catBeans) {

			if (catBean.getItem2() < startRowNum) {
				continue;
			}

			if (endRowNum <= catBean.getItem2()) {
				break;
			}
			
			List<String> row = new ArrayList<>();
			
			row.add(catBean.getItem1().getNo());
			row.add(catBean.getItem1().getName());

			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"" + request.getContextPath() + "/admin/cat/modify\" method=\"post\">");
			sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + catBean.getItem1().getNo() + "\" />");
			sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
			sb.append("</form>");
			row.add(sb.toString());
			
			sb.setLength(0);
			sb.append("<form action=\"" + request.getContextPath() + "/admin/cat/delete_proc\" method=\"post\">");
			sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + catBean.getItem1().getNo() + "\" />");
			sb.append("\t<input type=\"submit\" value=\"삭제하기\" />");
			sb.append("</form>");
			row.add(sb.toString());
			
			tbody.add(row);
		}
		
		model.addAttribute("searchPath", "/admin/cat/check");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "카테고리 조회");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", parseSelectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
    }
	
	@PostMapping("/modify") 
	public String modify(CatBean catBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		sql.append("WHERE no = '" + catBean.getNo() + "' ");
		
		List<Pair<CatBean, Integer>> selectResults = jdbcTemplate.query(sql.toString(), catRowMapper);
		CatBean selectResult = selectResults.get(0).getItem1();
		
		model.addAttribute("no", selectResult.getNo());
		model.addAttribute("name", selectResult.getName());
		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 수정");
		model.addAttribute("path", "/admin/cat/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(CatBean catBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cat ");
		sql.append("SET name = '" + catBean.getName() + "' ");
		sql.append("WHERE no = '" + catBean.getNo() + "' ");
		
		int updateResult = jdbcTemplate.update(sql.toString());
		if (updateResult > 0) {
			model.addAttribute("message", "카테고리가 수정되었습니다.");
		} else {
			model.addAttribute("message", "카테고리 수정에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@PostMapping("/delete_proc") 
	public String delete_proc(CatBean catBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cat ");
		sql.append("WHERE no = '" + catBean.getNo() + "' ");
		
		int deleteResult = jdbcTemplate.update(sql.toString());
		
		if (deleteResult > 0) {
			model.addAttribute("message", "카테고리가 삭제되었습니다.");
		} else {
			model.addAttribute("message", "카테고리 삭제에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
}