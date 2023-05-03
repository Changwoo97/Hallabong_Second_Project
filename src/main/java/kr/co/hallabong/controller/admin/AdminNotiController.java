package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.service.NotiService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/noti")
public class AdminNotiController {
	@Autowired 
	NotiService notiService;
	
	private final int ROW_SIZE = 2;
	
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
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=공지번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=제목&type=keyword&name=tit"));
		thead.add(Format.getMap("title=내용&type=keyword&name=cont"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));

		List<List<String>> tbody = new ArrayList<>();
		
		List<NotiBean> notiList = notiService.getNotiList();
		for (int i = notiList.size() - 1; i >= 0; i--) {
			NotiBean noti = notiList.get(i);
			if ((no.isBlank() || noti.getNo().contains(no))
					&& (tit.isBlank() || noti.getTit().contains(tit))
					&& (cont.isBlank() || noti.getCont().contains(cont)
					&& (reg_tmBeginDate.isBlank() || noti.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || noti.getReg_tm().compareTo(reg_tmEndDate) <= 0)))
				continue;
			notiList.remove(noti);
		}
		
		int pageSize = notiList.size() / ROW_SIZE + ((notiList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (notiList.size() > 0) {	
			for (int i = startRowNum; i < notiList.size(); i++) {
				if (endRowNum <= i) break;
				
				NotiBean noti = notiList.get(i);
				
				List<String> row = new ArrayList<>();
				
				row.add(noti.getNo());
				row.add(noti.getTit());
				row.add(noti.getCont());
				row.add(noti.getReg_tm());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + noti.getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/noti/delete_proc\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + noti.getNo() + "\" />");
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
