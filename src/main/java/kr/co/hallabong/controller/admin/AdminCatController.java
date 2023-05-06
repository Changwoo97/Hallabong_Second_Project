package kr.co.hallabong.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.CatBean;
import kr.co.hallabong.service.CatService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/cat")
public class AdminCatController {
	@Autowired
	private CatService catService;
	
	private final int ROW_SIZE = 25;
	
	@GetMapping("/registration")
	public String registration(@ModelAttribute CatBean catBean ,Model model) {
		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 등록");
		model.addAttribute("path", "/admin/cat/registration_proc");
		model.addAttribute("submit", "등록하기");
		return "admin/admin";
	}
	
	@PostMapping("/registration_proc")
	public String registration_proc(CatBean catBean, Model model) {
		catService.addCat(catBean);
		
		model.addAttribute("message", "카테고리가 등록되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@GetMapping("/check")
	public String check(HttpServletRequest request, Model model , 
			@ModelAttribute("no") String no, 
			@ModelAttribute("name") String name, 
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=카테고리 번호&type=keyword&name=no"));
		thead.add(Format.getMap("title=카테고리 이름&type=keyword&name=name"));
		thead.add(Format.getMap("title=수정하기"));
		thead.add(Format.getMap("title=삭제하기"));
		
		List<List<String>> tbody = new ArrayList<>();
		
		List<CatBean> catList = catService.getCatList();
		for (int i = catList.size() - 1; i >= 0; i--) {
			CatBean cat = catList.get(i);
			if ((no.isBlank() || cat.getNo().contains(no))
					&& (name.isBlank() || cat.getName().contains(name)))
				continue;
			catList.remove(cat);
		}
		
		int pageSize = catList.size() / ROW_SIZE + ((catList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;

		if (catList.size() > 0) {
			for (int i = startRowNum; i < catList.size(); i++) {
				if (endRowNum <= i) break;
				
				CatBean cat = catList.get(i);
				
				List<String> row = new ArrayList<>();
				
				row.add(cat.getNo());
				row.add(cat.getName());
	
				StringBuilder sb = new StringBuilder();
				sb.append("<form action=\"" + request.getContextPath() + "/admin/cat/modify\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + cat.getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"수정하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				sb.setLength(0);
				sb.append("<form action=\"" + request.getContextPath() + "/admin/cat/delete_proc\" method=\"post\">");
				sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + cat.getNo() + "\" />");
				sb.append("\t<input type=\"submit\" value=\"삭제하기\" />");
				sb.append("</form>");
				row.add(sb.toString());
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("no", no));
		searchKeyAndValues.add(new Pair<String, String>("name", name));
		
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/cat/check");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "카테고리 조회");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
    }
	
	@PostMapping("/modify") 
	public String modify(@ModelAttribute CatBean catBean, Model model) {
		CatBean bean = catService.getCat(catBean.getNo());
		catBean.setNo(bean.getNo());
		catBean.setName(bean.getName());

		model.addAttribute("content", "/WEB-INF/views/admin/catDetail.jsp");
		model.addAttribute("frameName", "카테고리 수정");
		model.addAttribute("path", "/admin/cat/modify_proc");
		model.addAttribute("submit", "수정하기");
		return "admin/admin";
	}
	
	@PostMapping("/modify_proc") 
	public String modify_proc(CatBean catBean, Model model) {
		catService.setCat(catBean);

		model.addAttribute("message", "카테고리가 수정되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
	@PostMapping("/delete_proc") 
	public String delete_proc(CatBean catBean, Model model) {
		catService.removeCat(catBean.getNo());

		model.addAttribute("message", "카테고리가 삭제되었습니다.");
		model.addAttribute("path", "/admin/cat/check");
		return "admin/alert";
	}
	
}