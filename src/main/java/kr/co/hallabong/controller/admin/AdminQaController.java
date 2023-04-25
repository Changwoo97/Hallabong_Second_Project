package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.util.Format;

@Controller
@RequestMapping("/admin/qa")
public class AdminQaController {
	@GetMapping("/request")
	public String request(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=작성일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=작성자&type=keyword&name=cust_id"));
		thead.add(Format.getMap("title=문의내용"));
		thead.add(Format.getMap("title=답변하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"registration\" method=\"post\">");
			sb.append("\t<input type=\"submit\" value=\"답변하기\" />");
			sb.append("</form>");
			row.add(sb.toString());
			
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "문의요청");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@GetMapping("/complete")
	public String complete(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=작성일&type=date&name=q_reg_tm"));
		thead.add(Format.getMap("title=답변일&type=date&name=a_reg_tm"));
		thead.add(Format.getMap("title=작성자&type=keyword&name=cust_id"));
		thead.add(Format.getMap("title=문의내용"));
		thead.add(Format.getMap("title=답변내용"));
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
		model.addAttribute("frameName", "문의완료");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("content", "/WEB-INF/views/admin/qaDetail.jsp");
		model.addAttribute("frameName", "문의처리");
		model.addAttribute("path", "/admin/qa/registration_proc");
		model.addAttribute("submit", "답변하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(Model model) {
		model.addAttribute("message", "문의가 처리되었습니다.");
		model.addAttribute("path", "/admin/qa/complete");
		return "admin/alert";
	}
	
	@PostMapping("/modify") 
	public String modify(Model model) {
		
		model.addAttribute("content", "/WEB-INF/views/admin/qaDetail.jsp");
		model.addAttribute("frameName", "문의수정");
		model.addAttribute("path", "/admin/qa/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(Model model) {
		
		model.addAttribute("message", "문의가 수정되었습니다.");
		model.addAttribute("path", "/admin/qa/complete");
		return "admin/alert";
	}
}
