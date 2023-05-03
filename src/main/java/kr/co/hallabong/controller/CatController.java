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
import kr.co.hallabong.service.CatService;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;
    
    @GetMapping("/main")
    public String catMainPage(@RequestParam("cat_No") String cat_No, Model model) {
    	
    	Map<String, String> categoryMap = new HashMap<>();
		categoryMap.put("1", "채소");
		categoryMap.put("2", "과일");
		categoryMap.put("3", "육류");
		categoryMap.put("4", "해산물");
		model.addAttribute("categoryMap", categoryMap);
    	
    	model.addAttribute("cat_No", cat_No);
    	List<ProdBean> catMainPage = catService.getcatMainPage(cat_No);
    	model.addAttribute("catMainPage", catMainPage);
    	
        return "cat/main";
    }
    
}