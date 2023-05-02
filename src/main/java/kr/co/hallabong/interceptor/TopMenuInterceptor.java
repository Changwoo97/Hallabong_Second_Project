package kr.co.hallabong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hallabong.bean.CustBean;

public class TopMenuInterceptor implements HandlerInterceptor{
	
	private CustBean CustBean;
	
	public TopMenuInterceptor(CustBean loginCustBean) {
		this.CustBean=loginCustBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		request.setAttribute("loginCustBean", CustBean);
		return true;
	}
	
}
