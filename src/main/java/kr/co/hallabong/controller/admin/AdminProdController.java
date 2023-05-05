package kr.co.hallabong.controller.admin;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.service.CatService;
import kr.co.hallabong.service.ProdService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/prod")
@PropertySource("/WEB-INF/properties/option.properties")
public class AdminProdController {
	@Value("${path.upload}")
	private String path_upload;
	@Autowired
	private ProdService prodService;
	@Autowired
	private CatService catService;
	
	private final int ROW_SIZE = 10;
	
	@GetMapping("/registration")
	public String registration(@ModelAttribute ProdBean prodBean , Model model) {		
		prodBean.setFs('Y');

		model.addAttribute("cats", catService.getCatList());
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품등록");
		model.addAttribute("path", "/admin/prod/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(ProdBean prodBean, BindingResult result, Model model) {
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
			prodBean.setL_img(fileName);
		}
		
		prodService.addProd(prodBean);

		model.addAttribute("message", "상품이 등록되었습니다.");
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
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		fs = fs.equals("T") ? "" : fs;
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=상품번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=판매상태&type=select&name=fs&selectValue1=Y&selectLabel1=판매중&selectValue2=N&selectLabel2=판매보류&selectEnd=2"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=name"));
		thead.add(Format.getMap("title=원가"));
		thead.add(Format.getMap("title=판매가"));
		thead.add(Format.getMap("title=등록일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=수정하기"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		List<ProdBean> prodList = prodService.getProdList();
		for (int i = prodList.size() - 1; i >= 0; i--) {
			ProdBean prod = prodList.get(i);
			if ((no.isBlank() || prod.getNo().contains(no))
					&& (fs.isBlank() || prod.getFs() == fs.charAt(0))
					&& (name.isBlank() || prod.getName().contains(name)
					&& (reg_tmBeginDate.isBlank() || prod.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || prod.getReg_tm().compareTo(reg_tmEndDate) <= 0)))
				continue;
			prodList.remove(prod);
		}
		
		int pageSize = prodList.size() / ROW_SIZE + ((prodList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (prodList.size() > 0) {
			for (int i = startRowNum; i < prodList.size(); i++) {
				if (endRowNum <= i) break;
				
				ProdBean prod = prodList.get(i);
				
				List<String> row = new ArrayList<>();
	
				row.add(prod.getNo());
				row.add(String.valueOf(prod.getFs()));
				row.add(prod.getName());
				row.add(String.valueOf(prod.getCost()));
				row.add(String.valueOf(prod.getSp()));
				row.add(prod.getReg_tm());
				
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\""+ request.getContextPath() +"/admin/prod/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + prod.getNo() + "\" />");
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
		List<CatBean> cats = catService.getCatList();

		ProdBean result = prodService.getProd(prodBean.getNo());
		
		prodBean.setNo(result.getNo());
		prodBean.setFs(result.getFs());
		prodBean.setName(result.getName());
		prodBean.setCost(result.getCost());
		prodBean.setSp(result.getSp());
		prodBean.setS_img(result.getS_img());
		prodBean.setL_img(result.getL_img());
		prodBean.setCat_no(result.getCat_no());
		prodBean.setReg_tm(result.getReg_tm());
		
		model.addAttribute("cats", cats);
		model.addAttribute("content", "/WEB-INF/views/admin/prodDetail.jsp");
		model.addAttribute("frameName", "상품수정");
		model.addAttribute("path", "/admin/prod/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(ProdBean prodBean, Model model) {
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

		prodService.setProd(prodBean);
		
		model.addAttribute("message", "상품이 수정되었습니다.");
		model.addAttribute("path", "/admin/prod/check");
		return "admin/alert";
	}
	
	private String saveUploadFile(MultipartFile uploadFile) {		
		//String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		//경로 시스템오류시
		String file_name = System.currentTimeMillis() +  "." + 
				FilenameUtils.getExtension(uploadFile.getOriginalFilename());
	
		try {
			uploadFile.transferTo(new File(path_upload + "/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	private void deleteUploadFile (String fileName) {
		File file = new File(path_upload + "/" + fileName);

		if (file.exists()) {
			file.delete();
		}
	}
	
//	private MultipartFile getUploadFile(String fileName) throws IOException {
//  File file = new File(path_upload + "/" + fileName);
//  System.out.println(file.getAbsolutePath());
//  FileItem fileItem = new DiskFileItem("originFile", 
//  		Files.probeContentType(file.toPath()), false, file.getName(), 
//  		(int) file.length(), file.getParentFile());
//
//  InputStream is = new FileInputStream(file);
//  OutputStream os = fileItem.getOutputStream();
//  IOUtils.copy(is, os);
//
//  //jpa.png -> multipart 변환
//  MultipartFile mFile = new CommonsMultipartFile(fileItem);
//  return mFile;
//}
}
