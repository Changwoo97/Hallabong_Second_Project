package kr.co.hallabong.controller.admin;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("")
	public String login() {
		return "admin/login";
	}
	
	@PostMapping("/login_proc")
	public String login_proc() {
		return "admin/admin";
	}
	
	@GetMapping("/logout_proc")
	public String logout_proc() {
		return "redirect:/admin";
	}
}
