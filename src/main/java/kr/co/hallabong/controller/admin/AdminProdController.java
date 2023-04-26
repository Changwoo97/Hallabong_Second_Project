package kr.co.hallabong.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		sql.append("VALUES ( ");
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
		sql.append("DEFAULT ) ");
		
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
	public String check(Model model) {
		List<String> srcs = new ArrayList<>();
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=상품번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=판매상태"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=name"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매가"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, fs, name, cost, sp, reg_tm ");
		sql.append("FROM prod ");
		
		for (int i = 0; i < 100; i++) {
			List<String> row = new ArrayList<>();
			
			for (int j = 0; j < 11; j++) {
				row.add(i + "-" + j);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("<form action=\"modify\" method=\"post\">");
			sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
			sb.append("</form>");
			row.add(sb.toString());

			tbody.add(row);
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("srcs", srcs);
		model.addAttribute("frameName", "상품조회");
		model.addAttribute("pageSize", 9);
		model.addAttribute("selectedPageNum", 7);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/modify") 
	public String modify(@ModelAttribute ProdBean prodBean, Model model) {
		prodBean.setFs('Y');
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT no, name ");
		sql.append("FROM cat ");
		
		List<Pair<CatBean, Integer>> catBeans = jdbcTemplate.query(sql.toString(), catRowMapper);
		List<CatBean> cats = new ArrayList<>();
		for (Pair<CatBean, Integer> catBean : catBeans) {
			cats.add(catBean.getItem1());
		}
		
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품수정");
		model.addAttribute("path", "/admin/prod/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(@ModelAttribute ProdBean prodBean, Model model) {
		model.addAttribute("message", "상품이 수정되었습니다.");
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
}
