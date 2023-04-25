package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.util.Format;

@Controller
@RequestMapping("/admin/dlvy")
public class AdminDlvyController {
	@GetMapping("/request")
	public String dlvyRequest(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
		thead.add(Format.getMap("title=요청확인"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
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
	public String dlvyReady(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
		thead.add(Format.getMap("title=준비완료"));
	
		List<List<String>> tbody = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			row.add("<input type=\"submit\" value=\"준비완료\" />");
			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "배송준비");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@GetMapping("/wait")
	public String dlvyWait(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
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
		model.addAttribute("frameName", "배송대기");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@GetMapping("/process")
	public String dlvyProcess(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=출발일&type=date&name=dep_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
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
		model.addAttribute("frameName", "배송중");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		
		return "admin/admin";
	}
	
	@GetMapping("/complete")
	public String dlvyComplete(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=출발일&type=date&name=dep_tm"));
		thead.add(Format.getMap("title=도착일&type=date&name=arr_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
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
		model.addAttribute("frameName", "배송완료");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		
		return "admin/admin";
	}
}
