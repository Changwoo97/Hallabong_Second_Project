package kr.co.hallabong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hallabong.service.CustService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CustService orderService;
	
	
	@RequestMapping("/order/list/form") // top에서 옴(경로에 대한 요청 처리)
	public String orderListForm() { 
		return "order/orderlist"; 

	}
	
	
	
	
}
