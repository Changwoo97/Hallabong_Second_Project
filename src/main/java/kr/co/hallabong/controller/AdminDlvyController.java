package kr.co.hallabong.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.struct.Pair;

@Controller
@RequestMapping("/admin/dlvy")
public class AdminDlvyController {
	@GetMapping("/request")
	public String dlvyRequest(Model model) {
		List<String> srcs = new ArrayList<>();
		srcs.add("/js/admin/tableCheck.js");
		
		List<Pair> thead = new ArrayList<>();
		thead.add(new Pair("<input type=\"checkbox\" id=\"checkAll\" />", "N"));
		thead.add(new Pair("주문일", "D-reg_tm"));
		thead.add(new Pair("주문번호", "K-ord_no"));
		thead.add(new Pair("제품번호", "K-prod_no"));
		thead.add(new Pair("제품명", "K-prod_name"));
		thead.add(new Pair("수량", "N"));
		thead.add(new Pair("주문인", "K-ordr_name"));
		thead.add(new Pair("주문인 전화번호", "K-ordr_tel"));
		thead.add(new Pair("주문인 주소", "K-ordr_addr"));
		thead.add(new Pair("수령인", "K-recv_name"));
		thead.add(new Pair("수령인 전화번호", "K-recv_name"));
		thead.add(new Pair("수령인 주소", "K-recv_addr"));
		thead.add(new Pair("<input type=\"submit\" value=\"요청확인\" />", "N"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			row.add("<input type=\"checkbox\" name=\"checks\" />");
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			row.add("<input type=\"submit\" value=\"요청확인\" />");
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "배송요청");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@GetMapping("/ready")
	public String dlvyReady() {
		
		return "admin/admin";
	}
	
	@GetMapping("/wait")
	public String dlvyWait() {
		
		return "admin/admin";
	}
	
	@GetMapping("/process")
	public String dlvyProcess() {
		
		return "admin/admin";
	}
	
	@GetMapping("/complete")
	public String dlvyComplete() {
		
		return "admin/admin";
	}
}
