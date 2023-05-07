package kr.co.hallabong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.bean.QABean;
import kr.co.hallabong.service.CartService;
import kr.co.hallabong.service.OrdService;
import kr.co.hallabong.service.QAService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
    @Autowired
    private CartService cartService;
    
    @Resource(name = "loginCustBean")
    private CustBean loginCustBean;
    
    @GetMapping("/cart")
    public String cart(Model model) {
    	List<CartBean> cartList=cartService.getCartList(loginCustBean.getId());
    	model.addAttribute("cartList",cartList);
    	return "mypage/cart";
    }
    
    @PostMapping("/delete")
    public String delete(@RequestParam(name = "cartList") List<String> cartList) {
       for (String prod_no : cartList) {
    	   cartService.deleteCart(loginCustBean.getId(), prod_no);
       }
       
       return "mypage/delete";
    }
    
    @PostMapping("/Orders_complete")
    public String completeOrder(@ModelAttribute("no") String no) {
       ordService.setOrdStaComplete(no);
       return "redirect:/mypage/Orders";
    }
    
       
    @GetMapping("/mypage")
    public String mypage() {
    	return "mypage/mypage";
    }
	
	@Autowired
	private OrdService ordService;
		
    @GetMapping("/Orders")
    public String Orders(Model model) {
    	List<OrdBean> OrdList = ordService.getOrdList(loginCustBean.getId());
    		
    	model.addAttribute("OrdList", OrdList);

    	return "mypage/Orders";
    }
    
	@Autowired
	private QAService qaService;
		
    @GetMapping("/QAList")
    public String QAList(Model model) {
    	List<QABean> qaList = qaService.getQAList(loginCustBean.getId());
    	
    	model.addAttribute("qaList", qaList);
    	
    	return "mypage/QAList";
    }
    
	/*
	 * @GetMapping("/QAList_pro") public String QAList_pro(@RequestParam("no")String
	 * no,Model model) {
	 * 
	 * }
	 */
	 
}

