package kr.co.hallabong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.service.CartService;
import kr.co.hallabong.service.CustService;

@RestController
public class RestApiController {

	@Autowired
	private CustService custService;
	
	@Autowired
	private CartService cartService;
	
	@Resource(name = "loginCustBean")
	private CustBean loginCustBean;
	
	@GetMapping("/cust/checkUserIDExist/{id}")
	public String checkUserIDExist(@PathVariable String id) {
		boolean chk = custService.checkUserIDExist(id);
		return chk + ""; // jsp X, 데이터를 넘김
	}
	
	@PostMapping("/cart/register")
	public Map<String, Object> registerProd(@RequestParam("prod_no") String prod_no, @RequestParam("qnty") int qnty) {
		Map<String, Object> map = new HashMap<>();
		boolean result = true;
		
		CartBean cart = new CartBean();
		cart.setCust_id(loginCustBean.getId());
		cart.setProd_no(prod_no);
		cart.setQnty(qnty);
		
		int insertResult = cartService.addCart(cart);
		
		if (insertResult < 0) {
			result = false;
		}
		
		map.put("success", result);
		return map;
	}
}
