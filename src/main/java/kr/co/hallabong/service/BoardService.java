package kr.co.hallabong.service;


import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hallabong.bean.ContentBean;
import kr.co.hallabong.bean.NewCustBean;
import kr.co.hallabong.dao.BoardDao;


@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	
	@Resource(name = "loginCustBean")
	private NewCustBean loginCustBean;

	@Value("${path.upload}")
	private String path_upload;
	
	
    @Value("${page.listcnt}")
    private int page_listcnt;
    
    @Value("${page.paginationcnt}")
    private int page_paginationcnt;

	
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		String file_name = System.currentTimeMillis()+"_"+
				FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "." + 
				FilenameUtils.getExtension(upload_file.getOriginalFilename());
		
		try {
			upload_file.transferTo(new File(path_upload+ "/"+ file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
		
	}
	//후기 등록(업로드 처리)
	public void addContentInfo(ContentBean writeContentBean) {
		
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name=saveUploadFile(upload_file);
			
			//첨부파일 호출
			//System.out.println(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginCustBean.getCust_idx());
		boardDao.addContentInfo(writeContentBean);
	}
	
	
	
}
