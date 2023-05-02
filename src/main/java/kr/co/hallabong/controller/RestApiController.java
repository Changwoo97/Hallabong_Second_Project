package kr.co.hallabong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.hallabong.service.CustService;

@RestController
public class RestApiController {

	@Autowired
	private CustService custService;
	
	@GetMapping("/user/checkUserIDExist/{id}")
	public String checkUserIDExist(@PathVariable String id) {
		boolean chk = custService.checkUserIDExist(id);
		return chk + ""; // jsp X, 데이터를 넘김
	}
	
	
}
