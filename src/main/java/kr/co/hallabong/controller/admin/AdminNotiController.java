package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.struct.Pair;

@Controller
@RequestMapping("/admin/noti")
public class AdminNotiController {
	@GetMapping("/registration") 
	public String registration(Model model) {
		return "admin/admin";
	}
	
	@GetMapping("/check") 
	public String check(Model model) {
		List<String> srcs = new ArrayList<>();
		srcs.add("/js/admin/tableCheck.js");
		
		List<Pair> thead = new ArrayList<>();
		thead.add(new Pair("<input type=\"checkbox\" id=\"checkAll\" />", "N"));
		thead.add(new Pair("등록일", "D-reg_tm"));
		thead.add(new Pair("내용", "N"));
		thead.add(new Pair("수정하기", "N"));
		thead.add(new Pair("삭제하기", "N"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			row.add("<input type=\"checkbox\" name=\"checks\" />");
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			row.add("<input type=\"submit\" value=\"수정하기\" />");
			row.add("<input type=\"submit\" value=\"삭제하기\" />");
			
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "공지사항 조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
}
