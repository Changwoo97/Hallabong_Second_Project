package kr.co.hallabong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@GetMapping("/main")
	public String main() {
		
		return "category/main";
	}
	
	
	

}
