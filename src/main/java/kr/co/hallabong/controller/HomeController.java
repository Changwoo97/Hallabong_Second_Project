package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.service.ProdService;

@Controller
public class HomeController {
    @Autowired
    private ProdService prodService;

    @GetMapping("/")
    public String main(Model model) {
        List<ProdBean> newProdList = prodService.getProdList();
        model.addAttribute("newProdList", newProdList);
        return "mainPage";
    }
}
