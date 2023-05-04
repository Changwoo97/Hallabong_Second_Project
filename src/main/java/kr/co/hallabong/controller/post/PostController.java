package kr.co.hallabong.controller.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.DlvyBean;
import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.service.DlvyService;
import kr.co.hallabong.service.OrdService;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private DlvyService dlvyService;
	
	@Autowired
	private OrdService ordService;
	
	@GetMapping("") 
	public String post() {
		return "redirect:/post/wait";
	}
	
	@GetMapping("/wait")
	public String postWait(HttpServletRequest request, Model model) {
		List<String> thead = new ArrayList<>();
		thead.add("등록일");
		thead.add("배송번호");
		thead.add("주문인");
		thead.add("주문인 전화번호");
		thead.add("주문인 주소");
		thead.add("수령인");
		thead.add("수령인 전화번호");
		thead.add("수령인 주소");
		thead.add("택배수거");
		
		List<List<String>> tbody = new ArrayList<>();
		List<DlvyBean> dlvyList = dlvyService.getDlvyListWait();
		
		for (DlvyBean dlvy : dlvyList) {
			List<String> row = new ArrayList<>();
			
			row.add(dlvy.getReg_tm());
			row.add(dlvy.getNo());
			row.add(dlvy.getSend_name());
			row.add(dlvy.getSend_tel());
			row.add(dlvy.getSend_addr());
			row.add(dlvy.getRecv_name());
			row.add(dlvy.getRecv_tel());
			row.add(dlvy.getRecv_addr());
			
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"" + request.getContextPath() + "/post/wait_proc\" method=\"post\">\n");
			sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + dlvy.getNo() + "\" />");
			sb.append("\t<input type=\"submit\" value=\"수거하기\" />");
			sb.append("</form>\n");
			row.add(sb.toString());
			
			tbody.add(row);
		}
		
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "post/post";
	}
	
	@PostMapping("/wait_proc")
	public String postWait_proc(@RequestParam(name = "no") String no, Model model) {
		dlvyService.setDlvyProcess(no);
		
		model.addAttribute("message", "택배수거가 완료되었습니다.");
		model.addAttribute("path", "/admin/post/wait");
		return "admin/alert";
	}
	
	@GetMapping("/process")
	public String postProcess(HttpServletRequest request, Model model) {
		List<String> thead = new ArrayList<>();
		thead.add("등록일");
		thead.add("수거일");
		thead.add("배송번호");
		thead.add("주문인");
		thead.add("주문인 전화번호");
		thead.add("주문인 주소");
		thead.add("수령인");
		thead.add("수령인 전화번호");
		thead.add("수령인 주소");
		thead.add("배송완료");
		
		List<List<String>> tbody = new ArrayList<>();
		List<DlvyBean> dlvyList = dlvyService.getDlvyListWait();
		
		for (DlvyBean dlvy : dlvyList) {
			List<String> row = new ArrayList<>();
			
			row.add(dlvy.getReg_tm());
			row.add(dlvy.getDep_tm());
			row.add(dlvy.getNo());
			row.add(dlvy.getSend_name());
			row.add(dlvy.getSend_tel());
			row.add(dlvy.getSend_addr());
			row.add(dlvy.getRecv_name());
			row.add(dlvy.getRecv_tel());
			row.add(dlvy.getRecv_addr());
			
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"" + request.getContextPath() + "/post/process_proc\" method=\"post\">\n");
			sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + dlvy.getNo() + "\" />");
			sb.append("\t<input type=\"submit\" value=\"완료하기\" />");
			sb.append("</form>\n");
			row.add(sb.toString());
			
			tbody.add(row);
		}
		
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "post/post";
	}
	
	@PostMapping("/process_proc")
	public String postProcess_proc(@RequestParam(name = "no") String no, Model model) {
		dlvyService.setDlvyComplete(no);
		
		DlvyBean tempDlvy = dlvyService.getDlvy(no);
		ordService.setOrdStaSemiComplete(tempDlvy.getOrd_no());
		
		model.addAttribute("message", "택배배송이 완료되었습니다.");
		model.addAttribute("path", "/admin/post/process");
		return "admin/alert";
	}
	
	@GetMapping("/complete")
	public String postComplete(HttpServletRequest request, Model model) {
		List<String> thead = new ArrayList<>();
		thead.add("등록일");
		thead.add("수거일");
		thead.add("완료일");
		thead.add("배송번호");
		thead.add("주문인");
		thead.add("주문인 전화번호");
		thead.add("주문인 주소");
		thead.add("수령인");
		thead.add("수령인 전화번호");
		thead.add("수령인 주소");
		
		List<List<String>> tbody = new ArrayList<>();
		List<DlvyBean> dlvyList = dlvyService.getDlvyListWait();
		
		for (DlvyBean dlvy : dlvyList) {
			List<String> row = new ArrayList<>();
			
			row.add(dlvy.getReg_tm());
			row.add(dlvy.getDep_tm());
			row.add(dlvy.getArr_tm());
			row.add(dlvy.getNo());
			row.add(dlvy.getSend_name());
			row.add(dlvy.getSend_tel());
			row.add(dlvy.getSend_addr());
			row.add(dlvy.getRecv_name());
			row.add(dlvy.getRecv_tel());
			row.add(dlvy.getRecv_addr());

			tbody.add(row);
		}
		
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "post/post";
	}
}
