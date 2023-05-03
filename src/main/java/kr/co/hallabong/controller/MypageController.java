package kr.co.hallabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.co.hallabong.bean.CartBean;
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
    
    @GetMapping("/cart")
    public String cart(@RequestParam("cust_id") String cust_id, Model model) {
    	model.addAttribute("cust_id",cust_id);
    	
    	List<CartBean> cartList=cartService.getCartList(cust_id);
    	model.addAttribute("cartList",cartList);
    	return "mypage/cart";
    }
       
    @GetMapping("/mypage")
    public String mypage() {
    	return "mypage/mypage";
    }
	
	@Autowired
	private OrdService ordService;
		
    @GetMapping("/Orders")
    public String Orders(@RequestParam("cust_id")String cust_id,Model model) {
    	model.addAttribute("cust_id",cust_id);
	
    	List<OrdBean> OrdList=ordService.getOrdList(cust_id);
    	model.addAttribute("OrdList",OrdList);

    	return "mypage/Orders";
    }
    
	@Autowired
	private QAService qaService;
		
    @GetMapping("/QAList")
    public String QAList(@RequestParam("cust_id")String cust_id, Model model) {
    	model.addAttribute("cust_id",cust_id);
    	
    	List<QABean> qaList=qaService.getQAList(cust_id);
    	model.addAttribute("qaList",qaList);
    	
    	return "mypage/QAList";
    }
    
	/*
	 * @GetMapping("/QAList_pro") public String QAList_pro(@RequestParam("no")String
	 * no,Model model) {
	 * 
	 * }
	 */
	 
}

