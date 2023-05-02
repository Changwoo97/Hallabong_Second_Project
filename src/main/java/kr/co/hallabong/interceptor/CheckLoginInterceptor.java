package kr.co.hallabong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hallabong.bean.CustBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	private CustBean CustBean;
	
	public CheckLoginInterceptor(CustBean CustBean) {
		this.CustBean=CustBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인을 하지 않음
		if(CustBean.isCustLogin() == false) {
			//로그인하지 않은 상태에서의 url호출
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/user/not_login");
			//다음 단계로 이동하지 않음
			return false;
		}
		
		return true;
	}
}
