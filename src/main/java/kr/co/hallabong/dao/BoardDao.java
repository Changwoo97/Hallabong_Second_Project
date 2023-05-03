package kr.co.hallabong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hallabong.bean.ContentBean;
import kr.co.hallabong.mapper.BoardMapper;


@Repository
public class BoardDao {

	@Autowired
	private BoardMapper boardMapper;
	//후기 등록
	public void addContentInfo(ContentBean writeContentBean) {
		boardMapper.addContentInfo(writeContentBean);
	}

	
}
