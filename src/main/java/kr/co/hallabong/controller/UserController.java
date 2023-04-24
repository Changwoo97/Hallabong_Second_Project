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
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.UserBean;
import kr.co.hallabong.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {


//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
//	
//	@Autowired
//	private UserService userService;
//	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value = "fail",defaultValue = "false") boolean fail, Model model) {
		model.addAttribute("fail",fail);
		//fail에 true면 실패했다
		return "user/login";
	}
//	
//	@PostMapping("/login_pro")
//	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
//		if(result.hasErrors()) {
//			return "user/login";
//		}
//		
//		userService.getLoginUserInfo(tempLoginUserBean);
//		
//		if(loginUserBean.isUserLogin() == true) {
//			return"user/login_success";
//		}else {
//			return "user/login_fail";
//		}
//		
//	}
	
	@GetMapping("/join_success")
	public String join_success() {
		
		return "user/join_success";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		
		return "user/not_login";
	}
	
	@GetMapping("/kakaoRedirectFrom")
	public String kakaoRedirectFrom() {
		
		return "user/kakaoRedirectFrom";
	}
	
	@GetMapping("/kakaologin")
	public String kakaologin() {
		
		return "user/kakaologin";
	}


	@GetMapping("/join")
	public String join(@ModelAttribute("joinuserBean") UserBean joinuserBean) {
		
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinuserBean") UserBean joinuserBean,BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/join";
		}
		// userService.addUserInfo(joinuserBean);
		return "user/join_success";
	}
	
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute(name = "modifyUserBean") UserBean modifyUserBean) {
		// userService.getModifyUserInfo(modifyUserBean);
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute(name = "modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/modify";
		}
		// userService.modifyUserInfo(modifyUserBean);

		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		// loginUserBean.setUserLogin(false);
		return "user/logout";
	}
	
	
	@GetMapping("/find_id_phone")
	public String find_id_phone() {
		
		return "user/find_id_phone";
	}
	
	
	@GetMapping("/find_id_email")
	public String find_id_email() {
		
		return "user/find_id_email";
	}
	
	
	@GetMapping("/find_pw_phone")
	public String find_pw_phone() {
		
		return "user/find_pw_phone";
	}
	
	@GetMapping("/myInfo")
	public String myInfo() {
		
		return "user/myInfo";
	}

	@GetMapping("/kakaologout")
	public String kakaologout() {
		
		return "user/kakaologout";
	}
	
	
	@GetMapping("/find_pw_email")
	public String find_pw_email() {
		
		return "user/find_pw_email";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// UserValidator validator = new UserValidator();
		// binder.addValidators(validator);
	}
	
	
}
 