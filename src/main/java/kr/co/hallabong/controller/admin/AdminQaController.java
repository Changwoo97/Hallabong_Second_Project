package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.QABean;
import kr.co.hallabong.service.QAService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/qa")
public class AdminQaController {
	@Autowired
	private QAService qaService;
	
	private final int ROW_SIZE = 2;
	
	@GetMapping("/request")
	public String request(HttpServletRequest request, Model model,
			@ModelAttribute("no") String no,
			@ModelAttribute("cust_id") String cust_id,
			@ModelAttribute("q_reg_tmBeginDate") String q_reg_tmBeginDate,
			@ModelAttribute("q_reg_tmEndDate") String q_reg_tmEndDate,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {		
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=문의번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=작성자&type=keyword&name=cust_id"));
		thead.add(Format.getMap("title=작성일&type=date&name=q_reg_tm"));
		thead.add(Format.getMap("title=문의내용"));
		thead.add(Format.getMap("title=상세보기"));
		thead.add(Format.getMap("title=답변하기"));
		
		List<List<String>> tbody = new ArrayList<>();
		
		List<QABean> qaList = qaService.getRequestQAList();
		for (int i = qaList.size() - 1; i >=0; i--) {
			QABean qa = qaList.get(i);
			if ((no.isBlank() || qa.getNo().contains(no)) 
				&& (cust_id.isBlank() || qa.getCust_id().contains(cust_id)) 
				&& (q_reg_tmBeginDate.isBlank() || qa.getQ_reg_tm().compareTo(q_reg_tmBeginDate) >= 0)
				&& (q_reg_tmEndDate.isBlank() || qa.getQ_reg_tm().compareTo(q_reg_tmEndDate) <= 0))
				continue;
			
				qaList.remove(qa);
		}
	
		int pageSize = qaList.size() / ROW_SIZE + ((qaList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (qaList.size() > 0) {
			for (int i = startRowNum; i < qaList.size(); i++) {
				if (endRowNum <= i) break;
				
				QABean qa = qaList.get(i);
				
				List<String> row = new ArrayList<>();
				
				row.add(qa.getNo());
				row.add(qa.getCust_id());
				row.add(qa.getQ_reg_tm());
				row.add(qa.getQ().length() > 15 ? qa.getQ().substring(0, 12) + "..." : qa.getQ());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/qa/detail\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + qa.getNo() + "\" />");
				sb.append("\t<input type=\"hidden\" name=\"path\" value=\"/admin/qa/request\" />");
				sb.append("\t<input type=\"submit\" value=\"상세보기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/qa/reply\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + qa.getNo() + "\" />");
				sb.append("\t<input type=\"hidden\" name=\"showAnswerArea\" value=\"true\" />");
				sb.append("\t<input type=\"submit\" value=\"답변하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<String>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("no", no));
		searchKeyAndValues.add(new Pair<String, String>("cust_id", cust_id));
		searchKeyAndValues.add(new Pair<String, String>("q_reg_tmBeginDate", q_reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("q_reg_tmEndDate", q_reg_tmEndDate));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/qa/request");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "문의요청");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/request")
	public String request_post() {
		return "redirect:/admin/qa/request";
	}
	
	@GetMapping("/complete")
	public String complete(HttpServletRequest request, Model model,
			@ModelAttribute("no") String no,
			@ModelAttribute("cust_id") String cust_id,
			@ModelAttribute("q_reg_tmBeginDate") String q_reg_tmBeginDate,
			@ModelAttribute("q_reg_tmEndDate") String q_reg_tmEndDate,
			@ModelAttribute("a_reg_tmBeginDate") String a_reg_tmBeginDate,
			@ModelAttribute("a_reg_tmEndDate") String a_reg_tmEndDate,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=문의번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=작성자&type=keyword&name=cust_id"));
		thead.add(Format.getMap("title=작성일&type=date&name=q_reg_tm"));
		thead.add(Format.getMap("title=문의내용"));
		thead.add(Format.getMap("title=답변일&type=date&name=a_reg_tm"));
		thead.add(Format.getMap("title=답변내용"));
		thead.add(Format.getMap("title=상세보기"));
		thead.add(Format.getMap("title=수정하기"));
		
		List<List<String>> tbody = new ArrayList<>();
		
		List<QABean> qaList = qaService.getCompleteQAList();
		for (int i = qaList.size() - 1; i >= 0; i--) {
			QABean qa = qaList.get(i);
			if ((no.isBlank() || qa.getNo().contains(no)) 
				&& (cust_id.isBlank() || qa.getCust_id().contains(cust_id)) 
				&& (q_reg_tmBeginDate.isBlank() || qa.getQ_reg_tm().compareTo(q_reg_tmBeginDate) >= 0)
				&& (q_reg_tmEndDate.isBlank() || qa.getQ_reg_tm().compareTo(q_reg_tmEndDate) <= 0)
				&& (a_reg_tmBeginDate.isBlank() || qa.getA_reg_tm().compareTo(a_reg_tmBeginDate) >= 0)
				&& (a_reg_tmEndDate.isBlank() || qa.getA_reg_tm().compareTo(a_reg_tmEndDate) <= 0)) 
				continue;
			qaList.remove(qa);
		}
	
		int pageSize = qaList.size() / ROW_SIZE + ((qaList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (qaList.size() > 0) {
			for (int i = startRowNum; i < qaList.size(); i++) {
				if (endRowNum <= i) break;
				
				QABean qa = qaList.get(i);
				
				List<String> row = new ArrayList<>();
				
				row.add(qa.getNo());
				row.add(qa.getCust_id());
				row.add(qa.getQ_reg_tm());
				row.add(qa.getQ().length() > 15 ? qa.getQ().substring(0, 12) + "..." : qa.getQ());
				row.add(qa.getA_reg_tm());
				row.add(qa.getA().length() > 15 ? qa.getA().substring(0, 12) + "..." : qa.getA());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/qa/detail\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + qa.getNo() + "\" />");
				sb.append("\t<input type=\"hidden\" name=\"path\" value=\"/admin/qa/complete\" />");
				sb.append("\t<input type=\"hidden\" name=\"showAnswerArea\" value=\"true\" />");
				sb.append("\t<input type=\"submit\" value=\"상세보기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/qa/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + qa.getNo() + "\" />");
				sb.append("\t<input type=\"hidden\" name=\"showAnswerArea\" value=\"true\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<String>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("no", no));
		searchKeyAndValues.add(new Pair<String, String>("cust_id", cust_id));
		searchKeyAndValues.add(new Pair<String, String>("q_reg_tmBeginDate", q_reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("q_reg_tmEndDate", q_reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("a_reg_tmBeginDate", a_reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("a_reg_tmEndDate", a_reg_tmEndDate));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/qa/complete");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "문의완료");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/complete") 
	public String complete_post() {
		return "redirect:/admin/qa/complete";
	}
	
	@PostMapping("/detail")
	public String detail(Model model, 
			@RequestParam("path") String path,
			@RequestParam(name = "showAnswerArea", defaultValue = "false") boolean showAnswerArea,
			@ModelAttribute("qaBean") QABean qaBean) {
		QABean bean = qaService.getQA(qaBean.getNo());
		qaBean.setNo(bean.getNo());
		qaBean.setCust_id(bean.getCust_id());
		qaBean.setQ(bean.getQ());
		qaBean.setQ_reg_tm(bean.getQ_reg_tm());
		qaBean.setA(bean.getA());
		qaBean.setA_reg_tm(bean.getA_reg_tm());
		qaBean.setSta(bean.getSta());
		
		model.addAttribute("content", "/WEB-INF/views/admin/qaDetail.jsp");
		model.addAttribute("frameName", "문의상세");
		model.addAttribute("path", path);
		model.addAttribute("showAnswerArea", showAnswerArea);
		model.addAttribute("isModifyMode", false);
		model.addAttribute("submit", "목록보기");
		return "admin/admin";
	}
	
	@PostMapping("/reply")
	public String reply(Model model, 
			@ModelAttribute("qaBean") QABean qaBean) {
		QABean bean = qaService.getQA(qaBean.getNo());
		qaBean.setNo(bean.getNo());
		qaBean.setCust_id(bean.getCust_id());
		qaBean.setQ(bean.getQ());
		qaBean.setQ_reg_tm(bean.getQ_reg_tm());
		qaBean.setA(bean.getA());
		qaBean.setA_reg_tm(bean.getA_reg_tm());
		qaBean.setSta(bean.getSta());
		
		model.addAttribute("content", "/WEB-INF/views/admin/qaDetail.jsp");
		model.addAttribute("frameName", "문의답변");
		model.addAttribute("path", "/admin/qa/reply_proc");
		model.addAttribute("showAnswerArea", true);
		model.addAttribute("isModifyMode", true);
		model.addAttribute("submit", "답변하기");
		return "admin/admin";
	}
	
	@PostMapping("reply_proc")
	public String reply_proc(Model model, QABean qaBean, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "답변 등록에 실패했습니다.");
			model.addAttribute("path", "/admin/qa/request");
		} else {
			qaService.setA(qaBean);
			model.addAttribute("message", "답변이 등록되었습니다.");
			model.addAttribute("path", "/admin/qa/complete");
		}
		return "admin/alert";
	}
	
	@PostMapping("/modify") 
	public String modify(Model model,
			@ModelAttribute("qaBean") QABean qaBean) {
		QABean bean = qaService.getQA(qaBean.getNo());
		qaBean.setNo(bean.getNo());
		qaBean.setCust_id(bean.getCust_id());
		qaBean.setQ(bean.getQ());
		qaBean.setQ_reg_tm(bean.getQ_reg_tm());
		qaBean.setA(bean.getA());
		qaBean.setA_reg_tm(bean.getA_reg_tm());
		qaBean.setSta(bean.getSta());
		
		model.addAttribute("content", "/WEB-INF/views/admin/qaDetail.jsp");
		model.addAttribute("frameName", "답변수정");
		model.addAttribute("path", "/admin/qa/modify_proc");
		model.addAttribute("showAnswerArea", true);
		model.addAttribute("isModifyMode", true);
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(Model model, 
			QABean qaBean, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "문의 수정에 실패했습니다.");
		} else {
			qaService.setA(qaBean);
			model.addAttribute("message", "문의가 수정되었습니다.");
		}
		model.addAttribute("path", "/admin/qa/complete");
		return "admin/alert";
	}
}
