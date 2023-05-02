package kr.co.hallabong.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.dao.CustDao;
import kr.co.hallabong.dao.SHA256;
import kr.co.hallabong.service.CustService;
import kr.co.hallabong.validator.CustValidator;
/*import kr.co.hallabong.bean.UserBean;*/


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustService custService;

	
	@Resource(name = "CustBean")
	private CustBean CustBean;
	

	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") CustBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {

		model.addAttribute("fail", fail);
	
		return "user/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") CustBean tempLoginUserBean, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return "user/login";
		}
		
		model.addAttribute(CustBean);
		// 비밀번호 암호하시켜서 대조하여 로그인
//		tempLoginUserBean.setPw(SHA256.encodeSha256(tempLoginUserBean.getPw()));
		custService.getLoginCustInfo(tempLoginUserBean);
		
		if(CustBean.isCustLogin() == true) {
			return "user/login_success";
		} else {
			return "user/login_fail";
		}
	}
	
	
	@GetMapping("/join_success")
	public String join_success() {

		return "user/join_success";
	}

	@GetMapping("/not_login")
	public String not_login() {

		return "user/not_login";
	}

	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinusecuCustBean") CustBean joinusecuCustBean) {

		return "user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinusecuCustBean") CustBean joinusecuCustBean,
			BindingResult result) {
		
		//비밀번호 암호화 시켜서 데이터베이스에 저장
//		joinusecuCustBean.setPw(SHA256.encodeSha256(joinusecuCustBean.getPw()));
		joinusecuCustBean.setDob(joinusecuCustBean.getDob_year()+"-"+joinusecuCustBean.getDob_month()+"-"+joinusecuCustBean.getDob_day());
		joinusecuCustBean.setAddr(joinusecuCustBean.getAddr1()+" "+joinusecuCustBean.getAddr_detail());
		if (result.hasErrors()) {
			return "user/join";
		}
				
		custService.addjoinUserInfo(joinusecuCustBean);
		return "user/join_success";
	}

	/*
	 * @GetMapping("/modify") public String modify(@ModelAttribute(name =
	 * "modifyUserBean") UserBean modifyUserBean) { //
	 * userService.getModifyUserInfo(modifyUserBean); return "user/modify"; }
	 * 
	 * @PostMapping("/modify_pro") public String
	 * modify_pro(@Valid @ModelAttribute(name = "modifyUserBean") UserBean
	 * modifyUserBean, BindingResult result) {
	 * 
	 * if(result.hasErrors()) { return "user/modify"; } //
	 * userService.modifyUserInfo(modifyUserBean);
	 * 
	 * return "user/modify_success"; }
	 */
	@GetMapping("/logout")
	public String logout() {
		CustBean.setCustLogin(false);
		
		return "user/logout";
	}

	@GetMapping("/find_id_phone")
	public String find_id_phone(@ModelAttribute("findid") CustBean findid) {

		return "user/find_id_phone";
	}

	@PostMapping("find_id_pro")
	public String find_id_pro(@ModelAttribute("findid") CustBean findid, BindingResult result,Model model) {
		
		custService.findId(findid);
		return "user/find_id_pro";
	}

	@GetMapping("/find_pw_phone")
	public String find_pw_phone(@ModelAttribute("findpw") CustBean findpw) {

		return "user/find_pw_phone";
	}

	@PostMapping("find_pw_pro")
	public String find_pw_pro(@ModelAttribute("findpw") CustBean findpw, BindingResult result,Model model) {
		
		custService.findPw(findpw);
		return "user/find_pw_pro";
	}
	
	@GetMapping("/myInfo")
	public String myInfo() {

		return "user/myInfo";
	}

	@InitBinder
	   public void initBinder(WebDataBinder binder) {
	      CustValidator validator = new CustValidator();
	      binder.addValidators(validator);
	   }
	
	@RequestMapping("/sendSMS1.do") //jsp 페이지 넘긴 mapping 값
	@ResponseBody    
	    public String sendSMS(String tel) {
	 
	        Random rand  = new Random(); //랜덤숫자 생성하기 !!
	        String numStr = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }
	        
	        
	          custService.certifiedPhoneNumber(tel, numStr); //휴대폰 api 쪽으로 가기 !!
	// // 밑에 자세한 설명나옴
	         
	          return numStr;
	    }


}
