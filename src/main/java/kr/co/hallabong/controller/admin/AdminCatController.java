package kr.co.hallabong.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cat")
public class AdminCatController {
	@GetMapping("/registration")
	public String registration(Model model) {
		return "admin/admin";
	}
	
	@GetMapping("/check")
	public String check(Model model) {
		return "admin/admin";
	}
}
