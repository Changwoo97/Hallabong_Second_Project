package kr.co.hallabong.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.service.CustService;

@Controller
@RequestMapping("/cust")
public class CustController {
	
	@Autowired
	private CustService custService;
	
	
	  @PostMapping("/login/check") 
	  public String loginCheck(@ModelAttribute("paramCustBean") CustBean paramCustBean) {
		  
	 
			/*
			 * CustBean custInfo = custService.getLoginCustInfo(paramCustBean);//회원 여부
			 * 
			 * if(custInfo != null) { return "cust/UpdateForm"; }else { return "/";
			 * 
			 * }
			 */
		 return "cust/UpdateForm"; 
	
	  
	  }
	  
	  
		 
	@GetMapping("/update_login")
	public String updateLogin() {
		
		return "cust/UpdateLogin";
		
	}
}