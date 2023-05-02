package kr.co.hallabong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hallabong.bean.AdminBean;
import kr.co.hallabong.bean.CustBean;

@Configuration
public class RootAppContext {
	@Bean("loginCustBean")
	@SessionScope
	public CustBean loginCustBean() {
		return new CustBean();
	}
	
	@Bean
	@SessionScope
	public AdminBean getAdminBean() {
		return new AdminBean();
	}
}
