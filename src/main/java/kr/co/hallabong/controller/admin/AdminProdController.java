package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/prod")
public class AdminProdController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RowMapper<Pair<CatBean, Integer>> catRowMapper;
	@Autowired
	private RowMapper<Pair<ProdBean, Integer>> prodRowMapper;
	
	@GetMapping("/registration")
	public String registration(Model model) {		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		List<Pair<CatBean, Integer>> catBeans = jdbcTemplate.query(sql.toString(), catRowMapper);
		List<CatBean> cats = new ArrayList<>();
		for (Pair<CatBean, Integer> catBean : catBeans) {
			cats.add(catBean.getItem1());
		}
		
		model.addAttribute("cats", cats);
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품등록");
		model.addAttribute("path", "/admin/prod/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(ProdBean prodBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO prod (no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm) ");
		sql.append("VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, DEFAULT) ");

		String s_img = prodBean.getS_img() == null ? "NULL" : prodBean.getS_img();
		String l_img = prodBean.getL_img() == null ? "NULL" : prodBean.getL_img();
		
		int insertResult = jdbcTemplate.update(sql.toString(), prodBean.getFs(), prodBean.getName(),
				prodBean.getCost(), prodBean.getSp(), s_img, 
				l_img, prodBean.getCat_no());
		
		if (insertResult > 0) {
			model.addAttribute("message", "상품이 등록되었습니다.");
		} else {
			model.addAttribute("message", "상품 등록에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/prod/check");
		return "admin/alert";
	}
	
	@GetMapping("/check")
	public String check(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=상품번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=판매상태"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=name"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매가"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"modify\" method=\"post\">");
			sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
			sb.append("</form>");
			row.add(sb.toString());

			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "상품조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(@ModelAttribute ProdBean prodBean, Model model) {
		prodBean.setFs('Y');
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		List<Pair<CatBean, Integer>> catBeans = jdbcTemplate.query(sql.toString(), catRowMapper);
		List<CatBean> cats = new ArrayList<>();
		for (Pair<CatBean, Integer> catBean : catBeans) {
			cats.add(catBean.getItem1());
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품수정");
		model.addAttribute("path", "/admin/prod/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(@ModelAttribute ProdBean prodBean, Model model) {
		model.addAttribute("message", "상품이 수정되었습니다.");
		model.addAttribute("path", "/admin/prod/check");
		return "admin/alert";
	}
}
