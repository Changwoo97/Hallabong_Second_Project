package kr.co.hallabong.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/prod")
@PropertySource("/WEB-INF/properties/option.properties")
public class AdminProdController {
	@Value("${path.upload}")
	private String path_upload;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RowMapper<Pair<CatBean, Integer>> catRowMapper;
	@Autowired
	private RowMapper<Pair<ProdBean, Integer>> prodRowMapper;
	
	@GetMapping("/registration")
	public String registration(@ModelAttribute ProdBean prodBean , Model model) {		
		prodBean.setFs('Y');
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		List<Pair<CatBean, Integer>> catBeans = jdbcTemplate.query(sql.toString(), catRowMapper);
		List<CatBean> cats = new ArrayList<>();
		for (Pair<CatBean, Integer> catBean : catBeans) {
			cats.add(catBean.getItem1());
		}
		
		model.addAttribute("cats", cats);
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품등록");
		model.addAttribute("path", "/admin/prod/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(@ModelAttribute ProdBean prodBean, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "상품 등록에 실패했습니다.");
			model.addAttribute("path", "/admin/prod/check");
			return "admin/alert";
		}
		
		MultipartFile s_img_file = prodBean.getS_img_file();
		if (s_img_file.getSize() > 0) {
			String fileName = saveUploadFile(s_img_file);
			prodBean.setS_img(fileName);
		}
		MultipartFile l_img_file = prodBean.getL_img_file();
		if (l_img_file.getSize() > 0) {
			String fileName = saveUploadFile(l_img_file);
			prodBean.setS_img(fileName);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO prod (no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm) ");
		sql.append("VALUES (");
		sql.append("DEFAULT, ");
		sql.append("'" + prodBean.getFs() + "', ");
		sql.append("'" + prodBean.getName() + "', ");
		sql.append(prodBean.getCost() + ", ");
		sql.append(prodBean.getSp() + ", ");
		if (prodBean.getS_img() != null) {
			sql.append("'" + prodBean.getS_img() + "', ");
		} else {
			sql.append("NULL, ");
		}
		if (prodBean.getL_img() != null) {
			sql.append("'" + prodBean.getL_img() + "', ");
		} else {
			sql.append("NULL, ");
		}
		sql.append("'" + prodBean.getCat_no() + "', ");
		sql.append("DEFAULT) ");
		
		int insertResult = jdbcTemplate.update(sql.toString());
		
		if (insertResult > 0) {
			model.addAttribute("message", "상품이 등록되었습니다.");
		} else {
			model.addAttribute("message", "상품 등록에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/prod/check");
		return "admin/alert";
	}
	
	@GetMapping("/check")
	public String check(HttpServletRequest request, Model model, 
			@ModelAttribute("no") String no, 
			@ModelAttribute("fs") String fs, 
			@ModelAttribute("name") String name, 
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate, 
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate, 
			@ModelAttribute("selectedPageNum") String selectedPageNum) {
		final int ROW_SIZE = 2;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=상품번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=판매상태&type=select&name=fs&selectValue1=Y&selectLabel1=판매중&selectValue2=N&selectLabel2=판매보류&selectEnd=2"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=name"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매가"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm ");
		sql.append("FROM prod ");

		no = (no == null) ? "" : no.trim();
		fs = (fs == null || fs.equals("T")) ? "" : fs;
		name = (name == null) ? "" : name.trim();
		reg_tmBeginDate = (reg_tmBeginDate == null) ? "" : reg_tmBeginDate;
		reg_tmEndDate = (reg_tmEndDate == null) ? "" : reg_tmEndDate;
		
		if (no.length() + fs.length() + name.length() 
			+ reg_tmBeginDate.length() + reg_tmEndDate.length() > 0) {
			sql.append("WHERE ");
			if (no.length() > 0) {
				sql.append("no LIKE '%" + no + "%' ");
				sql.append("AND ");
			}
			if (fs.length() > 0) {
				sql.append("fs = '" + fs + "' ");
				sql.append("AND ");
			}
			if (name.length() > 0) {
				sql.append("name LIKE '%" + name + "%' ");
				sql.append("AND ");
			}
			if (reg_tmBeginDate.length() > 0) {
				sql.append("reg_tm >= TO_DATE('" + reg_tmBeginDate + "', 'YYYY-MM-DD') ");
				sql.append("AND ");
			}
			if (reg_tmEndDate.length() > 0) {
				sql.append("reg_tm < TO_DATE('" + reg_tmEndDate + "', 'YYYY-MM-DD') + 1 ");
				sql.append("AND ");
			}
			
			int andIndex = sql.lastIndexOf("AND ");
			if (andIndex > -1) {
				sql.replace(andIndex, sql.length(), "");
			}
		}
		
		int parseSelectedPageNum;
		try {
			parseSelectedPageNum = Integer.parseInt(selectedPageNum);
			parseSelectedPageNum = (parseSelectedPageNum > 0) ? parseSelectedPageNum : 0;
		} catch (Exception e) {
			parseSelectedPageNum = 1;
		}
		selectedPageNum = String.valueOf(parseSelectedPageNum);
		
		List<Pair<ProdBean, Integer>> selectResults = jdbcTemplate.query(sql.toString(), prodRowMapper);
		int pageSize = selectResults.size() / ROW_SIZE + ((selectResults.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (parseSelectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (parseSelectedPageNum) * ROW_SIZE;
		
		if (selectResults.size() > 0) {
			for (Pair<ProdBean, Integer> selectResult : selectResults) {
				if (selectResult.getItem2() < startRowNum) {
					continue;
				}
	
				if (endRowNum <= selectResult.getItem2()) {
					break;
				}
				
				List<String> row = new ArrayList<>();
	
				row.add(selectResult.getItem1().getNo());
				row.add(String.valueOf(selectResult.getItem1().getFs()));
				row.add(selectResult.getItem1().getName());
				row.add(String.valueOf(selectResult.getItem1().getCost()));
				row.add(String.valueOf(selectResult.getItem1().getSp()));
				row.add(selectResult.getItem1().getReg_tm());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\""+ request.getContextPath() +"/admin/prod/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + selectResult.getItem1().getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
	
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<String>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("no", no));
		searchKeyAndValues.add(new Pair<String, String>("fs", fs));
		searchKeyAndValues.add(new Pair<String, String>("name", name));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("selectEnd", 2);
		model.addAttribute("searchPath", "/admin/prod/check");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "상품조회");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(@ModelAttribute ProdBean prodBean, Model model) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		List<Pair<CatBean, Integer>> catSelectResults = jdbcTemplate.query(sql.toString(), catRowMapper);
		List<CatBean> cats = new ArrayList<>();
		for (Pair<CatBean, Integer> catSelectResult : catSelectResults) {
			cats.add(catSelectResult.getItem1());
		}
		
		sql.setLength(0);
		
		sql.append("SELECT no, fs, name, cost, sp, s_img, l_img, cat_no, reg_tm ");
		sql.append("FROM prod ");
		sql.append("WHERE no = '" + prodBean.getNo() + "' ");

		List<Pair<ProdBean, Integer>> selectResults = jdbcTemplate.query(sql.toString(), prodRowMapper);
		ProdBean selectResult = selectResults.get(0).getItem1();
		
		prodBean.setNo(selectResult.getNo());
		prodBean.setFs(selectResult.getFs());
		prodBean.setName(selectResult.getName());
		prodBean.setCost(selectResult.getCost());
		prodBean.setSp(selectResult.getSp());
		prodBean.setS_img(selectResult.getS_img());
		prodBean.setL_img(selectResult.getL_img());
		prodBean.setCat_no(selectResult.getCat_no());
		prodBean.setReg_tm(selectResult.getReg_tm());
		
		model.addAttribute("cats", cats);
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품수정");
		model.addAttribute("path", "/admin/prod/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(@ModelAttribute ProdBean prodBean, Model model) {
		MultipartFile s_img_file = prodBean.getS_img_file();
		if (s_img_file.getSize() > 0) {
			deleteUploadFile(prodBean.getS_img());
			prodBean.setS_img(saveUploadFile(s_img_file));
		}
		
		MultipartFile l_img_file = prodBean.getL_img_file();
		if (l_img_file.getSize() > 0) {
			deleteUploadFile(prodBean.getL_img());
			prodBean.setL_img(saveUploadFile(l_img_file));
		}

		StringBuilder sql = new StringBuilder();
		sql = new StringBuilder();
		sql.append("UPDATE prod ");
		sql.append("SET fs = '" + prodBean.getFs() + "', ");
		sql.append("name = '" + prodBean.getName() + "', ");
		sql.append("cost = '" + prodBean.getCost() + "', ");
		sql.append("sp = '" + prodBean.getSp() + "', ");
		sql.append("s_img = '" + prodBean.getS_img() + "', ");
		sql.append("l_img = '" + prodBean.getL_img() + "', ");
		sql.append("cat_no = '" + prodBean.getCat_no() + "' ");
		sql.append("WHERE no = '" + prodBean.getNo() + "' ");
		
		int updateResult = jdbcTemplate.update(sql.toString());
		if (updateResult > 0) {
			model.addAttribute("message", "상품이 수정되었습니다.");
		} else {
			model.addAttribute("message", "상품 수정에 실패했습니다.");
		}
		model.addAttribute("path", "/admin/prod/check");
		return "admin/alert";
	}
	
	private String saveUploadFile(MultipartFile uploadFile) {		
		//String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();

		//경로 시스템오류시
		String file_name = System.currentTimeMillis() + "_" +  
		FilenameUtils.getBaseName(uploadFile.getOriginalFilename()) + "." + 
				FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		
		try {
			uploadFile.transferTo(new File(path_upload + "/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
//	private MultipartFile getUploadFile(String fileName) throws IOException {
//        File file = new File(path_upload + "/" + fileName);
//        System.out.println(file.getAbsolutePath());
//        FileItem fileItem = new DiskFileItem("originFile", 
//        		Files.probeContentType(file.toPath()), false, file.getName(), 
//        		(int) file.length(), file.getParentFile());
//
//        InputStream is = new FileInputStream(file);
//        OutputStream os = fileItem.getOutputStream();
//        IOUtils.copy(is, os);
//
//        //jpa.png -> multipart 변환
//        MultipartFile mFile = new CommonsMultipartFile(fileItem);
//        return mFile;
//    }
	
	private void deleteUploadFile (String fileName) {
		File file = new File(path_upload + "/" + fileName);

		if (file.exists()) {
			file.delete();
		}
	}
}
