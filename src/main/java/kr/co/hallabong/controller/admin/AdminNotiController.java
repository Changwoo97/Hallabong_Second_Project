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
import kr.co.hallabong.service.NotiService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/noti")
public class AdminNotiController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	NotiRowMapper notiRowMapper;
	@Autowired 
	NotiService notiService;
	
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
		notiService.addNoti(notiBean);

		model.addAttribute("message", "공지사항이 등록되었습니다.");
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
	
	@GetMapping("/check") 
	public String check(HttpServletRequest request, Model model,
			@ModelAttribute("no") String no, 
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate, 
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("tit") String tit, 
			@ModelAttribute("cont") String cont, 
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		final int ROW_SIZE = 2;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=공지번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=제목&type=keyword&name=tit"));
		thead.add(Format.getMap("title=내용&type=keyword&name=cont"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));

		List<List<String>> tbody = new ArrayList<>();

		no = (no == null) ? "" : no.trim();
		tit = (tit == null) ? "" : tit.trim();
		cont = (cont == null) ? "" : cont.trim();
		reg_tmBeginDate = (reg_tmBeginDate == null) ? "" : reg_tmBeginDate;
		reg_tmEndDate = (reg_tmEndDate == null) ? "" : reg_tmEndDate;
		
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
			if (reg_tmBeginDate.length() > 0) {
				sql.append("reg_tm >= TO_DATE('" + reg_tmBeginDate + "', 'YYYY-MM-DD') ");
				sql.append("AND ");
			}
			if (reg_tmEndDate.length() > 0) {
				sql.append("reg_tm < TO_DATE('" + reg_tmEndDate + "', 'YYYY-MM-DD') + 1 ");
				sql.append("AND ");
			}
			
			int andIndex = sql.lastIndexOf("AND ");
			if (andIndex > -1) {
				sql.replace(andIndex, sql.length(), "");
			}
		}
		sql.append("ORDER BY reg_tm DESC ");

		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Pair<NotiBean, Integer>> selectResults = jdbcTemplate.query(sql.toString(), notiRowMapper);
		int pageSize = selectResults.size() / ROW_SIZE + ((selectResults.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (selectResults.size() > 0) {	
			for (Pair<NotiBean, Integer> selectResult : selectResults) {
				if (selectResult.getItem2() < startRowNum) {
					continue;
				}
	
				if (endRowNum <= selectResult.getItem2()) {
					break;
				}
				
				List<String> row = new ArrayList<>();
				
				row.add(selectResult.getItem1().getNo());
				row.add(selectResult.getItem1().getTit());
				row.add(selectResult.getItem1().getCont());
				row.add(selectResult.getItem1().getReg_tm());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + selectResult.getItem1().getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/delete_proc\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + selectResult.getItem1().getNo() + "\" />");
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
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("no", no));
		searchKeyAndValues.add(new Pair<String, String>("tit", tit));
		searchKeyAndValues.add(new Pair<String, String>("cont", cont));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/noti/check");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "공지사항 조회");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(@ModelAttribute NotiBean notiBean, Model model) {
		NotiBean bean = notiService.getNoti(notiBean.getNo());

		notiBean.setNo(bean.getNo());
		notiBean.setTit(bean.getTit());
		notiBean.setCont(bean.getCont());
		notiBean.setReg_tm(bean.getReg_tm());
		
		model.addAttribute("content", "/WEB-INF/views/admin/notiDetail.jsp");
		model.addAttribute("frameName", "공지사항 수정");
		model.addAttribute("path", "/admin/noti/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(NotiBean notiBean, Model model) {
		notiService.setNoti(notiBean);

		model.addAttribute("message", "공지사항이 수정되었습니다.");
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
	
	@PostMapping("/delete_proc") 
	public String delete_proc(NotiBean notiBean, Model model) {
		notiService.removeNoti(notiBean.getNo());

		model.addAttribute("message", "공지사항이 삭제되었습니다.");
		model.addAttribute("path", "/admin/noti/check");
		return "admin/alert";
	}
}
