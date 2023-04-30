package kr.co.hallabong.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBean {
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]")
	@NotBlank
	private String id;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]")
	@NotBlank
	private String pw;
	
	private boolean login = false;
}
