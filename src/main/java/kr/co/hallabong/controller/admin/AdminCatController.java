package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.format.Format;

@Controller
@RequestMapping("/admin/cat")
public class AdminCatController {
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 등록");
		model.addAttribute("path", "/admin/cat/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(Model model) {
		model.addAttribute("message", "카테고리가 등록되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@GetMapping("/check")
	public String check(Model model) {
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=카테고리 번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=카테고리 이름&type=keyword&name=name"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));
	
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
			
			sb.setLength(0);
			sb.append("<form action=\"delete_proc\" method=\"post\">");
			sb.append("\t<input type=\"submit\" value=\"삭제하기\" />");
			sb.append("</form>");
			row.add(sb.toString());
			
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "카테고리 조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(Model model) {
		
		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 수정");
		model.addAttribute("path", "/admin/cat/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(Model model) {
		
		model.addAttribute("message", "카테고리가 수정되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@PostMapping("/delete_proc") 
	public String delete_proc(Model model) {
		
		model.addAttribute("message", "카테고리가 삭제되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
}
