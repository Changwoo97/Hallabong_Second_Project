package kr.co.hallabong.bean;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentBean {
	private int content_idx;
	//NOTNULL과 같음(재목과 내용 공백x)
	@NotBlank
	private String content_subject;
	@NotBlank
	private String content_text;
	private String content_file;
	//정적파일
	private MultipartFile upload_file;
	
	private int content_writer_idx;
	private String content_writer_name;
	private int content_board_idx;
	private String content_date;
}
