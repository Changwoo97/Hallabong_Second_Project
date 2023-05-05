package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.join.ProdCatBean;
import kr.co.hallabong.service.CatService;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;
    
    @GetMapping("/main")
    public String catMainPage(@RequestParam("cat_no") String cat_no, Model model) {
    	model.addAttribute("cat_no", cat_no);
    	
    	List<ProdCatBean> catMainPage = catService.getCatMainPage(cat_no);
    	model.addAttribute("catMainPage", catMainPage);
    	
        return "cat/main";
    }
    
}