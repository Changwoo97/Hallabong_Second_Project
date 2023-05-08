package kr.co.hallabong.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.bean.DlvyBean;
import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.bean.OrdDtlBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.bean.QABean;
import kr.co.hallabong.service.CartService;
import kr.co.hallabong.service.DlvyService;
import kr.co.hallabong.service.OrdDtlService;
import kr.co.hallabong.service.OrdService;
import kr.co.hallabong.service.ProdService;
import kr.co.hallabong.service.QAService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProdService prodService;
    
    @Autowired
    private OrdDtlService ordDrlService;
    
    @Autowired
    private DlvyService dlvyService;
    
    @Resource(name = "loginCustBean")
    private CustBean loginCustBean;
    
    @GetMapping("/cart")
    public String cart(Model model) {
    	List<Map<String, String>> cartList = new ArrayList<>();
    	
    	List<CartBean> carts = cartService.getCartList(loginCustBean.getId());
    	
    	for (CartBean cart : carts) {
    		Map<String, String> map = new HashMap<>();
    		
    		ProdBean prod = prodService.getProd(cart.getProd_no());
    		
    		map.put("no", cart.getProd_no());
    		map.put("s_img", prod.getS_img());
    		map.put("name", prod.getName());
    		map.put("sp", Format.numComma(prod.getSp()));
    		map.put("qnty", Format.numComma(cart.getQnty()));
    		
    		cartList.add(map);
    	}
    	
    	model.addAttribute("cartList", cartList);
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
    	List<Pair<Map<String, String>, List<Map<String, String>>>> pairList = new ArrayList<>();
    	
    	List<OrdBean> ordList = ordService.getOrdList(loginCustBean.getId());
    	
    	for (OrdBean ord : ordList) {
    		Pair<Map<String, String>, List<Map<String, String>>> pair = new Pair<>();

    		Map<String, String> item1 = new HashMap<>();
    		item1.put("ord_no", ord.getNo());
    		item1.put("reg_tm", ord.getReg_tm());
    		
    		List<Map<String, String>> item2 = new ArrayList<>();
    		List<OrdDtlBean> ordDtlList = ordDrlService.getOrdDtlList(ord.getNo());
    		for (OrdDtlBean ordDtl : ordDtlList) {
    			Map<String, String> map = new HashMap<>();
    			
    			ProdBean prod = prodService.getProd(ordDtl.getProd_no());
    			DlvyBean dlvy = dlvyService.getDlvyByOrdNo(ordDtl.getOrd_no());
    			
    			String dlvySta = "배송요청";
    			if (dlvy != null) {
	    			switch (dlvy.getSta()) {
	    			case "READY":
	    				dlvySta = "배송준비";
	    				break;
	    			case "WAIT":
	    				dlvySta = "배송대기";
	    				break;
	    			case "PROCESS":
	    				dlvySta = "배송중";
	    				break;
	    			case "COMPLETE":
	    				dlvySta = "배송완료";
	    				break;
	    			}
    			}
    			
    			map.put("no", ordDtl.getProd_no());
    			map.put("s_img", prod.getS_img());
    			map.put("name", prod.getName());
    			map.put("sp", Format.numComma(ordDtl.getProd_sp()));
    			map.put("qnty", Format.numComma(ordDtl.getProd_qnty()));
    			map.put("dlvy", dlvySta);
    			
    			item2.add(map);
    		}
    		
    		pair.setItem1(item1);
    		pair.setItem2(item2);
    		
    		pairList.add(pair);
    	}
    		
    	model.addAttribute("ordList", pairList);

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
    
    @GetMapping("/QAwrite")
    public String QAwrite() {

    	return "mypage/QAwrite";
    }
    
    @PostMapping("/QAwrite_pro")
    public String QAwrite_pro(@RequestParam("content_text") String q) {
    	QABean qa = new QABean();
    	qa.setCust_id(loginCustBean.getId());
    	qa.setQ(q);
    	qaService.addQA(qa);
    	
    	return "mypage/QAwrite_pro";
    }
    
    /**
     * 리뷰 등록/수정페이지
     * 
     * @param model
     * @return String
     * @author
     * @since
     */
    @RequestMapping("/review/form") 
    public String reviewForm(Model model) { 
             
       if(loginCustBean.isCustLogin() == true) {
          model.addAttribute("id", loginCustBean.getId());
       } 
       //return "review/write";
       return "cust/UpdateLogin"; // 띄우는 화면 "cust/UpdateLogin" 뷰(View)를 반환
    }
    
}

