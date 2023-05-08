package kr.co.hallabong.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.hallabong.bean.CustBean;
public class CustValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CustBean.class.isAssignableFrom(clazz);
	}
	
	//회원가입
	@Override
	public void validate(Object target, Errors errors) {
		
		CustBean custBean = (CustBean)target;
		String beanName = errors.getObjectName();
		// System.out.println(beanName);
		if (beanName.equals("joinusecuCustBean") ) {
			if (custBean.getPw().equals(custBean.getPw2()) == false) {
				errors.rejectValue("pw", "NoEquals");
				errors.rejectValue("pw2", "NoEquals");
			}
		}
		if (beanName.equals("joinusecuCustBean")) {
			if (custBean.isCustIdExist() == false) {
				errors.rejectValue("id", "DonCheckUserIDExist");
			}
			if (custBean.isCustTelExist() == false) {
				errors.rejectValue("tel", "DonCheckUserTelExist");
			}
		}
	}
	

}
