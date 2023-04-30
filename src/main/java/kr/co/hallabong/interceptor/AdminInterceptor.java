package kr.co.hallabong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hallabong.bean.AdminBean;

public class AdminInterceptor implements HandlerInterceptor {
	
	private AdminBean adminBean;
	
	public AdminInterceptor(AdminBean adminBean) {
		this.adminBean = adminBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!adminBean.isLogin()) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return false;
		}
		
		return true;
	}
	
}
