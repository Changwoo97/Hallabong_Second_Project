package kr.co.hallabong.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.struct.Pair;

@Controller
@RequestMapping("/admin/stlm")
public class AdminStlmController {
	@GetMapping("/check")
	public String check(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Pair> thead = new ArrayList<>();
		thead.add(new Pair("신청일", "D-reg_tm"));
		thead.add(new Pair("정산일", "D-stlm_tm"));
		thead.add(new Pair("주문번호", "K-ord_no"));
		thead.add(new Pair("배송료", "N"));
		thead.add(new Pair("차감배송료", "N"));
		thead.add(new Pair("원가", "N"));
		thead.add(new Pair("판매금액", "N"));
		thead.add(new Pair("순수익", "N"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "정산조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
}
