package kr.co.hallabong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hallabong.bean.CustBean;


@Configuration
public class RootAppContext {
	
	@Bean("loginCustBean") //@Bean : 빈을 생성하는 객체(=loginUserBean)를 의미 
	@SessionScope
	public CustBean loginCustBean() {
		return new CustBean();
		
	}

}
