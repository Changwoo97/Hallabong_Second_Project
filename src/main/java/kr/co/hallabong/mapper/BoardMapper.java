package kr.co.hallabong.mapper;

import org.apache.ibatis.annotations.Insert;

import kr.co.hallabong.bean.ContentBean;

public interface BoardMapper {
	
	//jdbcType=VARCHAR :NULL값을 허용하도록
	//jdbcType=VARCHAR:MyBatis에서 null값을 문자로 인지하도록한다
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
			"content_file, content_writer_idx, content_board_idx, content_date) " +
			"values (content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);


}