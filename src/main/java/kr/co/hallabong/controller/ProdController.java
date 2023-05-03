package kr.co.hallabong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.RevBean;
import kr.co.hallabong.service.ProdService;

@Controller
@RequestMapping("/prod")
public class ProdController {

	@Autowired
	private ProdService prodService;
	
	@GetMapping("/search")

	public String searchProductList(@RequestParam("name")String name, Model model) {
		Map<String, String> categoryMap = new HashMap<>();
		categoryMap.put("1", "채소");
		categoryMap.put("2", "과일");
		categoryMap.put("3", "육류");
		categoryMap.put("4", "해산물");
		model.addAttribute("categoryMap", categoryMap);
		
		List<ProdBean> searchProductList = prodService.searchProductList(name);
		model.addAttribute("searchProductList", searchProductList);
		
		return "prod/search";
	}
	
	@GetMapping("/product")
	public String ProdInfoPage(@RequestParam("prod_No")String prod_No, 
							   @RequestParam(name = "page", defaultValue = "1") int page, 
							   Model model) {
		
		model.addAttribute("prod_No", prod_No);
		model.addAttribute("page", page);
		
		ProdBean ProdBean = prodService.getProd(prod_No);
		model.addAttribute("ProdBean", ProdBean);
		
		List<RevBean> ReviewList = prodService.getReviewList(prod_No, page);
		model.addAttribute("ReviewList", ReviewList);
    
		PageBean pageBean = prodService.getReviewCnt(prod_No, page);
		model.addAttribute("pageBean", pageBean);
		
		return "prod/product";
	}	
	
}
