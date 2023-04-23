package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.format.Format;

@Controller
@RequestMapping("/admin/stlm")
public class AdminStlmController {
	@GetMapping("/check")
	public String check(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=신청일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=정산일&type=date&name=stlm_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=배송료"));
		thead.add(Format.getMap("title=차감배송료"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매금액"));
		thead.add(Format.getMap("title=순수익"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			
			tbody.add(row);
		}
		
		List<String> tfoot = new ArrayList<>();
		tfoot.add("");
		tfoot.add("");
		tfoot.add("합계");
		tfoot.add("배송료");
		tfoot.add("차감배송료");
		tfoot.add("원가");
		tfoot.add("판매금액");
		tfoot.add("순수익");
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "정산조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		model.addAttribute("tfoot", tfoot);
		return "admin/admin";
	}
}
