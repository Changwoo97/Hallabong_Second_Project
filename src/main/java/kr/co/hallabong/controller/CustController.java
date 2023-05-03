package kr.co.hallabong.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.service.CustService;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	private CustService custService;

	/**
	 * 개인정보 확인페이지(개인정보수정페이지 이동 전)
	 * 개인정보 수정페이지 이동전 본인 재인증
	 * @param model
	 * @return String
	 * @author 
	 * @since  
	 */
	@RequestMapping("/update_login/form") // top에서 옴(경로에 대한 요청 처리)
	public String updateLoginForm(Model model) { //"updateLoginForm" 메서드는 "Model" 객체를 매개변수로 받아서

		// 추후 아마도 session에 담긴 id 값을 가지고 내려가면 됨.
		model.addAttribute("cust_id", "tester3333"); //"custId"라는 이름으로 "tester3333" 값을 모델에 추가

		return "cust/UpdateLogin"; // 띄우는 화면 "cust/UpdateLogin" 뷰(View)를 반환 

	}

	/**
	 * 개인정보 확인페이지(본인재인증)
	 * 
	 * @param paramLoginCustBean
	 * @return Map
	 * @author
	 * @since
	 */
	@RequestMapping("/login/check") // 집주소(경로에 대한요청 처리)
	@ResponseBody  //@Responsebody 어노테이션을 사용하면 http요청 body를 자바 객체로 전달받을 수 있다.
	public Map<String, Object> loginCheck(NewCustBean paramLoginCustBean, Model model) { //Model 객체를 사용하여 View로 데이터를 전달

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int custIdx = custService.getLoginCustIdx(paramLoginCustBean); // 회원여부
			resultMap.put("cust_idx", custIdx);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}
	
	/**
	 * 개인정보 수정페이지 이동 및 개인상세정보 조회
	 * 
	 * @param model
	 * @ret
	 * @author
	 * @since
	 */
	@RequestMapping("/update/form") 
	public String updateCustForm(NewCustBean paramLoginCustBean, Model model) {

		// 추후 아마도 session에 담긴 id 값을 가지고 내려가면 됨.
		NewCustBean custInfo = custService.getLoginCustDetailInfo(paramLoginCustBean.getCust_idx());
		model.addAttribute("custInfo", custInfo);

		return "cust/Update"; // 띄우는 화면

	}
	
	/**
	 * 이메일 중복 확인
	 * 
	 * @param paramLoginCustBean
	 * @return Map
	 * @author
	 * @since
	 */
	@RequestMapping("/email/check") // 집주소
	@ResponseBody
	public Map<String, Object> emailCheck(NewCustBean paramLoginCustBean, Model model) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int emailCount = custService.getEmailDupCheck(paramLoginCustBean); 
			resultMap.put("emailCount", emailCount);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}
	
	/**
	 * 개인정보 수정
	 * 
	 * @param model
	 * @return
	 * @author
	 * @since
	 */
	@RequestMapping("/update") // 집주소
	@ResponseBody
	public Map<String, Object> updateCust(NewCustBean paramLoginCustBean, Model model) {	
		
		paramLoginCustBean.setAddr(paramLoginCustBean.getAddr1()+" / "+paramLoginCustBean.getAddr_detail());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = custService.updateCustInfo(paramLoginCustBean); 
			resultMap.put("result", result);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}
	
	/**
	 * 탈퇴하기
	 * 
	 * @param model
	 * @return
	 * @author
	 * @since
	 */
	@RequestMapping("/quit") // 집주소
	@ResponseBody
	public Map<String, Object> quitCust(NewCustBean paramLoginCustBean, Model model) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int delCount = custService.deleteCust(paramLoginCustBean.getCust_idx()); 
			resultMap.put("delCount", delCount);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}
	

	@GetMapping("/resign/check") 
	public String resigncheck(@ModelAttribute("paramLoginCustBean") NewCustBean paramLoginCustBean) {

		return "cust/Resign"; // 띄우는 화면

	}

}