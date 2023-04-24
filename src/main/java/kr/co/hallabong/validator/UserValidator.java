package kr.co.hallabong.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.hallabong.bean.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;

		String beanName = errors.getObjectName();

		if (beanName.equals("joinuserBean") || beanName.equals("modifyUserBean")) {
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NoEquals");
				errors.rejectValue("user_pw2", "NoEquals");
			}
		}
		if (beanName.equals("joinuserBean")) {
			if (userBean.isUserIdExist() == false) {

				errors.rejectValue("user_id", "DonCheckUserIDExist");
			}
		}
	}
}
