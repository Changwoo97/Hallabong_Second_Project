package kr.co.hallabong.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.dao.SHA256;
import kr.co.hallabong.service.CustService;
import kr.co.hallabong.validator.CustValidator;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	private CustService custService;

	@Resource(name = "loginCustBean")
	private CustBean loginCustBean;

	/**
	 * 개인정보 확인페이지(개인정보수정페이지 이동 전) 개인정보 수정페이지 이동전 본인 재인증
	 * 
	 * @param model
	 * @return String
	 * @author
	 * @since
	 */
	@RequestMapping("/update_login/form") // top에서 가려는 집주소(경로에 대한 요청 처리)
	public String updateLoginForm(Model model) { // "updateLoginForm" 메서드는 "Model" 객체를 매개변수로 받아서
		// 추후 아마도 session에 담긴 id 값을 가지고 내려가면 됨.

		if (loginCustBean.isCustLogin() == true) {
			model.addAttribute("id", loginCustBean.getId());
		}
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
	@ResponseBody // @Responsebody 어노테이션을 사용하면 http요청 body를 자바 객체로 전달받을 수 있다.
	public Map<String, Object> loginCheck(CustBean paramLoginCustBean, Model model) { // Model 객체를 사용하여 View로 데이터를 전달

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			paramLoginCustBean.setPw(SHA256.encodeSha256(paramLoginCustBean.getPw()));
			String custId = custService.getLoginCustIdx(paramLoginCustBean); // 회원여부
			resultMap.put("id", custId);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}

	/**
	 * 개인정보 수정페이지
	 * 
	 * @param model
	 * @return
	 * @author
	 * @since
	 */
	@RequestMapping("/update/form")
	public String updateCustForm(CustBean paramLoginCustBean, Model model) {
		// 추후 아마도 session에 담긴 id 값을 가지고 내려가면 됨.
		CustBean custInfo = custService.getLoginCustDetailInfo(paramLoginCustBean.getId());
		model.addAttribute("custInfo", custInfo);

		return "cust/update"; // 띄우는 화면
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
	public Map<String, Object> emailCheck(CustBean paramLoginCustBean, Model model) {
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
	public Map<String, Object> updateCust(CustBean paramLoginCustBean, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = custService.updateCustInfo(paramLoginCustBean);
			resultMap.put("result", result);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	@GetMapping("/update_login")
	public String updateLogin() {
		return "cust/UpdateLogin";
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
	public Map<String, Object> quitCust(CustBean paramLoginCustBean, Model model) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int delCount = custService.deleteCust(paramLoginCustBean.getId());
			resultMap.put("delCount", delCount);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;

	}

	@GetMapping("/resign/check")
	public String resigncheck(@ModelAttribute("paramLoginCustBean") CustBean paramLoginCustBean) {
		return "cust/Resign"; // 띄우는 화면
	}

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") CustBean tempLoginUserBean, @RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {
		model.addAttribute("fail", fail);

		return "cust/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") CustBean tempLoginUserBean, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cust/login";
		}
		tempLoginUserBean.setPw(SHA256.encodeSha256(tempLoginUserBean.getPw()));
		custService.getLoginCustInfo(tempLoginUserBean);
		// 비밀번호 암호하시켜서 대조하여 로그인
		

		if (loginCustBean.isCustLogin() == true) {
			return "cust/login_success";
		} else {
			return "cust/login_fail";
		}
	}

	@GetMapping("/join_success")
	public String join_success() {

		return "cust/join_success";
	}

	@GetMapping("/not_login")
	public String not_login() {

		return "cust/not_login";
	}

	@GetMapping("/join")
	public String join(@ModelAttribute("joinusecuCustBean") CustBean joinusecuCustBean) {
		joinusecuCustBean.setGender("X");
		return "cust/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinusecuCustBean") CustBean joinusecuCustBean, BindingResult result) {
		if (result.hasErrors()) {
			return "cust/join";
		}

		// 비밀번호 암호화 시켜서 데이터베이스에 저장
		joinusecuCustBean.setPw(SHA256.encodeSha256(joinusecuCustBean.getPw()));
		joinusecuCustBean.setDob(joinusecuCustBean.getDob_year() + "-" + joinusecuCustBean.getDob_month() + "-" + joinusecuCustBean.getDob_day());
		joinusecuCustBean.setAddr(joinusecuCustBean.getAddr1() + "," + joinusecuCustBean.getAddr_detail());

		String gender = joinusecuCustBean.getGender();
		if (gender != null && (gender.equals("M") || gender.equals("F"))) {
			joinusecuCustBean.setGender(null);
		}

		custService.addjoinUserInfo(joinusecuCustBean);
		return "cust/join_success";
	}

	@GetMapping("/logout")
	public String logout() {
		loginCustBean.setCustLogin(false);

		return "cust/logout";
	}

	@GetMapping("/find_id")
	public String find_id_phone(@ModelAttribute("findid") CustBean findid) {

		return "cust/find_id";
	}

	@PostMapping("find_id_pro")
	public String find_id_pro(@ModelAttribute("findid") CustBean findid, BindingResult result, Model model) {

		custService.findId(findid);
		return "cust/find_id_pro";
	}

	@GetMapping("/change_pw")
	public String find_pw_phone(@ModelAttribute("findpw") CustBean findpw) {

		return "cust/change_pw";
	}



	@RequestMapping("/update_pw") // 집주소
	@ResponseBody
	public Map<String, Object> updateCustPw(CustBean findpw, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			int result = custService.changePw(findpw);
			resultMap.put("result", result);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}
	   @PostMapping("change_pw_pro")
	   public String find_pw_pro(@ModelAttribute("findpw") CustBean findpw, BindingResult result,Model model) {
	      System.out.println(findpw.getTel());
	      
	      boolean pw =custService.findPw(findpw);
	      if(pw == true) {
	         return "cust/change_pw";
	      }else {
	         
	      return "cust/update_pw";
	      }
	   }

	
	@GetMapping("/myInfo")
	public String myInfo() {

		return "cust/myInfo";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustValidator validator = new CustValidator();
		binder.addValidators(validator);
	}

	@RequestMapping("/sendSMS1.do") // jsp 페이지 넘긴 mapping 값
	@ResponseBody
	public String sendSMS(String tel) {

		Random rand = new Random(); // 랜덤숫자 생성하기 !!
		String numStr = "";
		for (int i = 0; i < 4; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}

		custService.certifiedPhoneNumber(tel, numStr); // 휴대폰 api 쪽으로 가기 !!
		// // 밑에 자세한 설명나옴

		return numStr;
	}

}