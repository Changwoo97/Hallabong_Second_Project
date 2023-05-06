package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.hallabong.bean.NotiBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.service.NotiService;
import kr.co.hallabong.service.ProdService;

@Controller
public class HomeController {
    @Autowired
    private ProdService prodService;

    @Autowired
    private NotiService notiService;

    @GetMapping("/")
    public String main(Model model) {
    	 List<ProdBean> newProdList = prodService.getProdList();
         model.addAttribute("newProdList", newProdList);
         
         List<ProdBean> RevProdList = prodService.getRevProdList();
         model.addAttribute("RevProdList", RevProdList);
                 
         
         List<NotiBean> notiList = notiService.getNotiList();
         model.addAttribute("notiList", notiList);

        return "mainPage";
    }
}
