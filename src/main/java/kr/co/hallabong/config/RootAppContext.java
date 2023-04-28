package kr.co.hallabong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hallabong.bean.CustBean;

@Configuration
public class RootAppContext {
	@Bean("CustBean")
	@SessionScope
	public CustBean CustBean() {
		return new CustBean();
	}
}
