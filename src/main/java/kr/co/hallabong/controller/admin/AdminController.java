package kr.co.hallabong.controller.admin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.bean.AdminBean;
import kr.co.hallabong.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminBean adminBean;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@GetMapping("")
	public String login(@ModelAttribute("tempAdminBean") AdminBean tempAdminBean) {
		if (adminBean.isLogin()) {
			return "admin/admin";
		}
		
		return "admin/login";
	}
	
	@PostMapping("/login_proc")
	public String login_proc(@Valid @ModelAttribute("tempAdminBean") AdminBean tempAdminBean, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			StringBuilder messages = new StringBuilder();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				String code = error.getCode() + ".tempAdminBean." + error.getField();
				String message = messageSource.getMessage(code, null, Locale.ROOT);
				messages.append(message + "\\n");
			}
			model.addAttribute("message", messages);
			model.addAttribute("path", "/admin");
			return "admin/alert";
		}
		
		if (!adminService.isExist(tempAdminBean)) {
			model.addAttribute("message", "존재하지 않는 아이디 또는 비밀번호 입니다.");
			model.addAttribute("path", "/admin");
			return "admin/alert";
		}
		
		adminBean.setLogin(true);
		return "admin/admin";
	}
	
	@GetMapping("/logout_proc")
	public String logout_proc() {
		adminBean.setLogin(false);
		return "redirect:/admin";
	}
}
