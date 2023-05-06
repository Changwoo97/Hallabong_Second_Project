package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.service.NotiService;

@Controller
@RequestMapping("/notice")
public class NotiController {
	@Autowired
	private NotiService NotiService;
	
	@GetMapping("/main")
	public String NotiMainPage(Model model) {
		
		List<NotiBean> NoticeList = NotiService.getNotiList();
		model.addAttribute("NoticeList", NoticeList);
		
		return "notice/main";
	}
	
	@GetMapping("/read")
	public String selectNoti(@RequestParam("noti_no") String no,
					Model model) {
		model.addAttribute("no", no);
		NotiBean selectNoti = NotiService.getNoti(no);
		model.addAttribute("selectNoti", selectNoti);
		
		return "notice/read";
	}
}
