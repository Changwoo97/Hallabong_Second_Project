package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.bean.PageBean;
import kr.co.hallabong.service.NotiService;

@Controller
@RequestMapping("/notice")
public class NotiController {
	@Autowired
	private NotiService NotiService;
	
	@GetMapping("/main")
	public String NotiMainPage(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		
		List<NotiBean> NoticeList = NotiService.getNotiList2(page);
		model.addAttribute("NoticeList", NoticeList);
		
		PageBean pageBean = NotiService.getNotiCnt(page);
		model.addAttribute("pageBean", pageBean);
		
		model.addAttribute("page", page);
		
		return "notice/main";
	}
	
	@GetMapping("/read")
	public String readNoti(@RequestParam("noti_no") String noti_no,
					Model model) {
		
		model.addAttribute("noti_no", noti_no);
		
		NotiBean notiBean = NotiService.getNoti(noti_no);
		model.addAttribute("notiBean", notiBean);
		
		return "notice/read";
	}
}
