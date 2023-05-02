package kr.co.hallabong.controller.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.DlvyBean;
import kr.co.hallabong.bean.ODPDBean;
import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.service.DlvyService;
import kr.co.hallabong.service.ODPDService;
import kr.co.hallabong.service.OrdService;
import kr.co.hallabong.util.Format;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/admin/dlvy")
public class AdminDlvyController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DlvyService dlvyService;
	
	@Autowired 
	private OrdService ordService;
	
	@Autowired
	private ODPDService odpdService;
	
	private final int ROW_SIZE = 2;
	
	@GetMapping("/request")
	public String dlvyRequest(HttpServletRequest request, Model model,
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate,
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("ord_no") String ord_no,
			@ModelAttribute("prod_no") String prod_no,
			@ModelAttribute("prod_name") String prod_name,
			@ModelAttribute("ordr_name") String ordr_name,
			@ModelAttribute("ordr_tel") String ordr_tel,
			@ModelAttribute("ordr_addr") String ordr_addr, 
			@ModelAttribute("recv_name") String recv_name,
			@ModelAttribute("recv_tel") String recv_tel,
			@ModelAttribute("recv_addr") String recv_addr,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		selectedPageNum = (selectedPageNum > 0) ? selectedPageNum : 1;
		
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_tel"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
		thead.add(Format.getMap("title=요청확인"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("WITH                                                           ");
		sql.append("    odp AS (SELECT o.reg_tm    AS reg_tm                       ");
		sql.append("                 , o.no        AS ord_no                       ");
		sql.append("                 , d.prod_no   AS prod_no                      ");
		sql.append("                 , p.name      AS prod_name                    ");
		sql.append("                 , d.prod_qnty AS prod_qnty                    ");
		sql.append("                 , o.ordr_name AS ordr_name                    ");
		sql.append("                 , o.ordr_tel  AS ordr_tel                     ");
		sql.append("                 , o.ordr_addr AS ordr_addr                    ");
		sql.append("                 , o.recv_name AS recv_name                    ");
		sql.append("                 , o.recv_tel  AS recv_tel                     ");
		sql.append("                 , o.recv_addr AS recv_addr                    ");
		sql.append("           FROM ord o INNER JOIN ord_dtl d ON o.no = d.ord_no  ");
		sql.append("                      INNER JOIN prod p    ON d.prod_no = p.no ");
		sql.append("           WHERE sta = 'REQUEST'                               ");
		sql.append("           ORDER BY reg_tm DESC)                               ");
		sql.append("SELECT TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm                 ");
		sql.append("     , ord_no                                                  ");
		sql.append("     , prod_no                                                 ");
		sql.append("     , prod_name                                               ");
		sql.append("     , prod_qnty                                               ");
		sql.append("     , ordr_name                                               ");
		sql.append("     , ordr_tel                                                ");
		sql.append("     , ordr_addr                                               ");
		sql.append("     , recv_name                                               ");
		sql.append("     , recv_tel                                                ");
		sql.append("     , recv_addr                                               ");
		sql.append("FROM odp                                                       ");
		
		List<Map<String, String>> selectResults = jdbcTemplate.query(sql.toString(), new RowMapper<Map<String, String>>() {

			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, String> map = new HashMap<>();

				map.put("reg_tm", rs.getString("reg_tm"));
				map.put("ord_no", rs.getString("ord_no"));
				map.put("prod_no", rs.getString("prod_no"));
				map.put("prod_name", rs.getString("prod_name"));
				map.put("prod_qnty", rs.getString("prod_qnty"));
				map.put("ordr_name", rs.getString("ordr_name"));
				map.put("ordr_tel", rs.getString("ordr_tel"));
				map.put("ordr_addr", rs.getString("ordr_addr"));
				map.put("recv_name", rs.getString("recv_name"));
				map.put("recv_tel", rs.getString("recv_tel"));
				map.put("recv_addr", rs.getString("recv_addr"));

				return map;
			}  
			
		});
		
		for (int i = selectResults.size() - 1; i >= 0; i--) {
			Map<String, String> selectResult = selectResults.get(i);
			if ((reg_tmBeginDate.isBlank() || selectResult.get("reg_tm").compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || selectResult.get("reg_tm").compareTo(reg_tmEndDate) <= 0)
					&& (ord_no.isBlank() || selectResult.get("ord_no").contains(ord_no))
					&& (prod_no.isBlank() || selectResult.get("prod_no").contains(prod_no))
					&& (prod_name.isBlank() || selectResult.get("prod_name").contains(prod_name))
					&& (ordr_name.isBlank() || selectResult.get("ordr_name").contains(ordr_name))
					&& (ordr_tel.isBlank() || selectResult.get("ordr_tel").contains(ordr_tel))
					&& (ordr_addr.isBlank() || selectResult.get("ordr_addr").contains(ordr_addr))
					&& (recv_name.isBlank() || selectResult.get("recv_name").contains(recv_name))
					&& (recv_tel.isBlank() || selectResult.get("recv_tel").contains(recv_tel))
					&& (recv_addr.isBlank() || selectResult.get("recv_addr").contains(recv_addr)))
				continue;
			selectResults.remove(selectResult);
		}
		
		int pageSize = selectResults.size() / ROW_SIZE + ((selectResults.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (selectResults.size() > 0) {
			String tempNo = "";
			
			for (int i = startRowNum; i < selectResults.size(); i++) {
				if (endRowNum <= i) break;
				
				Map<String, String> selectResult = selectResults.get(i);
				
				List<String> row = new ArrayList<>();
				
				if (tempNo.equals(selectResult.get("ord_no"))) {
					row.add("");
					row.add("");
					row.add(selectResult.get("prod_no"));
					row.add(selectResult.get("prod_name"));
					row.add(selectResult.get("prod_qnty"));
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				} else {
					row.add(selectResult.get("reg_tm"));
					row.add(selectResult.get("ord_no"));
					row.add(selectResult.get("prod_no"));
					row.add(selectResult.get("prod_name"));
					row.add(selectResult.get("prod_qnty"));
					row.add(selectResult.get("ordr_name"));
					row.add(selectResult.get("ordr_tel"));
					row.add(selectResult.get("ordr_addr"));
					row.add(selectResult.get("recv_name"));
					row.add(selectResult.get("recv_tel"));
					row.add(selectResult.get("recv_addr"));
					
					StringBuilder sb = new StringBuilder();
					sb.append("<form action=\"" + request.getContextPath() + "/admin/dlvy/request_proc\" method=\"post\">");
					sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + selectResult.get("ord_no") + "\" />");
					sb.append("\t<input type=\"submit\" value=\"요청확인\" />");
					sb.append("</form>");
					row.add(sb.toString());
					
					tempNo = selectResult.get("ord_no");
				}
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_no", prod_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_name", prod_name));
		searchKeyAndValues.add(new Pair<String, String>("ordr_name", ordr_name));
		searchKeyAndValues.add(new Pair<String, String>("ordr_tel", ordr_tel));
		searchKeyAndValues.add(new Pair<String, String>("ordr_addr", ordr_addr));
		searchKeyAndValues.add(new Pair<String, String>("recv_name", recv_name));
		searchKeyAndValues.add(new Pair<String, String>("recv_tel", recv_tel));
		searchKeyAndValues.add(new Pair<String, String>("recv_addr", recv_addr));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/dlvy/request");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "배송요청");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/request_proc")
	public String dlvyRequest_proc(Model model, String no) {
		OrdBean ordBean = ordService.getOrd(no);
		ordService.setOrdStaProcess(no);
		
		DlvyBean dlvyBean = new DlvyBean();
		dlvyBean.setSend_name(ordBean.getOrdr_name());
		dlvyBean.setSend_tel(ordBean.getOrdr_tel());
		dlvyBean.setSend_addr(ordBean.getOrdr_addr());
		dlvyBean.setRecv_name(ordBean.getRecv_name());
		dlvyBean.setRecv_tel(ordBean.getRecv_tel());
		dlvyBean.setRecv_addr(ordBean.getRecv_addr());
		dlvyBean.setFee(ordBean.getDlvy_fee());
		dlvyBean.setOrd_no(ordBean.getNo());
		dlvyService.addDlvy(dlvyBean);
		
		model.addAttribute("message", "배송요청이 완료되었습니다.");
		model.addAttribute("path", "/admin/dlvy/request");
		return "admin/alert";
	}
	
	@GetMapping("/ready")
	public String dlvyReady(HttpServletRequest request, Model model,
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate,
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("dlvy_no") String dlvy_no,
			@ModelAttribute("ord_no") String ord_no,
			@ModelAttribute("prod_no") String prod_no,
			@ModelAttribute("prod_name") String prod_name,
			@ModelAttribute("send_name") String send_name,
			@ModelAttribute("send_tel") String send_tel,
			@ModelAttribute("send_addr") String send_addr, 
			@ModelAttribute("recv_name") String recv_name,
			@ModelAttribute("recv_tel") String recv_tel,
			@ModelAttribute("recv_addr") String recv_addr,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=배송번호&type=keyword&name=dlvy_no"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=send_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=send_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=send_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_tel"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
		thead.add(Format.getMap("title=준비완료"));

		List<List<String>> tbody = new ArrayList<>();
		
		List<ODPDBean> odpdList = odpdService.getODPDListReady();
		for (int i = odpdList.size() - 1; i >= 0; i--) {
			ODPDBean odpd = odpdList.get(i);
			if ((reg_tmBeginDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmEndDate) <= 0)
					&& (dlvy_no.isBlank() || odpd.getDlvy_no().contains(dlvy_no))
					&& (ord_no.isBlank() || odpd.getOrd_no().contains(ord_no))
					&& (prod_no.isBlank() || odpd.getProd_no().contains(prod_no))
					&& (prod_name.isBlank() || odpd.getProd_name().contains(prod_name))
					&& (send_name.isBlank() || odpd.getSend_name().contains(send_name))
					&& (send_tel.isBlank() || odpd.getSend_tel().contains(send_tel))
					&& (send_addr.isBlank() || odpd.getSend_addr().contains(send_addr))
					&& (recv_name.isBlank() || odpd.getRecv_name().contains(recv_name))
					&& (recv_tel.isBlank() || odpd.getRecv_tel().contains(recv_tel))
					&& (recv_addr.isBlank() || odpd.getRecv_addr().contains(recv_addr)))
				continue;
			odpdList.remove(odpd);
		}
		
		int pageSize = odpdList.size() / ROW_SIZE + ((odpdList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (odpdList.size() > 0) {
			String tempNo = "";
			
			for (int i = startRowNum; i <= odpdList.size(); i++) {
				if (endRowNum <= i) break;
				
				ODPDBean odpd = odpdList.get(i);
				
				List<String> row = new ArrayList<>();
				
				if (tempNo.equals(odpd.getDlvy_no())) {
					row.add("");
					row.add("");
					row.add("");
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				} else {
					row.add(odpd.getReg_tm());
					row.add(odpd.getDlvy_no());
					row.add(odpd.getOrd_no());
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add(odpd.getSend_name());
					row.add(odpd.getSend_tel());
					row.add(odpd.getSend_addr());
					row.add(odpd.getRecv_name());
					row.add(odpd.getRecv_tel());
					row.add(odpd.getRecv_addr());
					
					StringBuilder sb = new StringBuilder();
					sb.append("<form action=\"" + request.getContextPath() + "/admin/dlvy/ready_proc\" method=\"post\">");
					sb.append("\t<input type=\"hidden\" name=\"no\" value=\"" + odpd.getDlvy_no() + "\" />");
					sb.append("\t<input type=\"submit\" value=\"준비완료\" />");
					sb.append("</form>");
					row.add(sb.toString());
					
					tempNo = odpd.getDlvy_no();
				}
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		 
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dlvy_no", dlvy_no));
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_no", prod_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_name", prod_name));
		searchKeyAndValues.add(new Pair<String, String>("send_name", send_name));
		searchKeyAndValues.add(new Pair<String, String>("send_tel", send_tel));
		searchKeyAndValues.add(new Pair<String, String>("send_addr", send_addr));
		searchKeyAndValues.add(new Pair<String, String>("recv_name", recv_name));
		searchKeyAndValues.add(new Pair<String, String>("recv_tel", recv_tel));
		searchKeyAndValues.add(new Pair<String, String>("recv_addr", recv_addr));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/dlvy/ready");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "배송준비");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@PostMapping("/ready_proc")
	public String dlvyReady_proc(Model model, String no) {		
		dlvyService.setDlvyWait(no);
		
		model.addAttribute("message", "배송준비가 완료되었습니다.");
		model.addAttribute("path", "/admin/dlvy/ready");
		return "admin/alert";
	}
	
	@GetMapping("/wait")
	public String dlvyWait(HttpServletRequest request, Model model,
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate,
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("dlvy_no") String dlvy_no,
			@ModelAttribute("ord_no") String ord_no,
			@ModelAttribute("prod_no") String prod_no,
			@ModelAttribute("prod_name") String prod_name,
			@ModelAttribute("send_name") String send_name,
			@ModelAttribute("send_tel") String send_tel,
			@ModelAttribute("send_addr") String send_addr, 
			@ModelAttribute("recv_name") String recv_name,
			@ModelAttribute("recv_tel") String recv_tel,
			@ModelAttribute("recv_addr") String recv_addr,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {

		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=배송번호&type=keyword&name=dlvy_no"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=send_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=send_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=send_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_tel"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
		List<List<String>> tbody = new ArrayList<>();
		
		List<ODPDBean> odpdList = odpdService.getODPDListWait();
		for (int i = odpdList.size() - 1; i >= 0; i--) {
			ODPDBean odpd = odpdList.get(i);
			if ((reg_tmBeginDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmEndDate) <= 0)
					&& (dlvy_no.isBlank() || odpd.getDlvy_no().contains(dlvy_no))
					&& (ord_no.isBlank() || odpd.getOrd_no().contains(ord_no))
					&& (prod_no.isBlank() || odpd.getProd_no().contains(prod_no))
					&& (prod_name.isBlank() || odpd.getProd_name().contains(prod_name))
					&& (send_name.isBlank() || odpd.getSend_name().contains(send_name))
					&& (send_tel.isBlank() || odpd.getSend_tel().contains(send_tel))
					&& (send_addr.isBlank() || odpd.getSend_addr().contains(send_addr))
					&& (recv_name.isBlank() || odpd.getRecv_name().contains(recv_name))
					&& (recv_tel.isBlank() || odpd.getRecv_tel().contains(recv_tel))
					&& (recv_addr.isBlank() || odpd.getRecv_addr().contains(recv_addr)))
				continue;
			odpdList.remove(odpd);
		}
		
		int pageSize = odpdList.size() / ROW_SIZE + ((odpdList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (odpdList.size() > 0) {
			String tempNo = "";
			
			for (int i = startRowNum; i <= odpdList.size(); i++) {
				if (endRowNum <= i) break;
				
				ODPDBean odpd = odpdList.get(i);
				
				List<String> row = new ArrayList<>();
				
				if (tempNo.equals(odpd.getDlvy_no())) {
					row.add("");
					row.add("");
					row.add("");
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				} else {
					row.add(odpd.getReg_tm());
					row.add(odpd.getDlvy_no());
					row.add(odpd.getOrd_no());
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add(odpd.getSend_name());
					row.add(odpd.getSend_tel());
					row.add(odpd.getSend_addr());
					row.add(odpd.getRecv_name());
					row.add(odpd.getRecv_tel());
					row.add(odpd.getRecv_addr());

					tempNo = odpd.getDlvy_no();
				}
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		 
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dlvy_no", dlvy_no));
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_no", prod_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_name", prod_name));
		searchKeyAndValues.add(new Pair<String, String>("send_name", send_name));
		searchKeyAndValues.add(new Pair<String, String>("send_tel", send_tel));
		searchKeyAndValues.add(new Pair<String, String>("send_addr", send_addr));
		searchKeyAndValues.add(new Pair<String, String>("recv_name", recv_name));
		searchKeyAndValues.add(new Pair<String, String>("recv_tel", recv_tel));
		searchKeyAndValues.add(new Pair<String, String>("recv_addr", recv_addr));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/dlvy/wait");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "배송대기");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		return "admin/admin";
	}
	
	@GetMapping("/process")
	public String dlvyProcess(HttpServletRequest request, Model model,
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate,
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("dep_tmBeginDate") String dep_tmBeginDate,
			@ModelAttribute("dep_tmEndDate") String dep_tmEndDate,
			@ModelAttribute("dlvy_no") String dlvy_no,
			@ModelAttribute("ord_no") String ord_no,
			@ModelAttribute("prod_no") String prod_no,
			@ModelAttribute("prod_name") String prod_name,
			@ModelAttribute("send_name") String send_name,
			@ModelAttribute("send_tel") String send_tel,
			@ModelAttribute("send_addr") String send_addr, 
			@ModelAttribute("recv_name") String recv_name,
			@ModelAttribute("recv_tel") String recv_tel,
			@ModelAttribute("recv_addr") String recv_addr,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=출발일&type=date&name=dep_tm"));
		thead.add(Format.getMap("title=배송번호&type=keyword&name=dlvy_no"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=send_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=send_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=send_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_tel"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
		List<ODPDBean> odpdList = odpdService.getODPDListProcess();
		for (int i = odpdList.size() - 1; i >= 0; i--) {
			ODPDBean odpd = odpdList.get(i);
			if ((reg_tmBeginDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmEndDate) <= 0)
					&& (dep_tmBeginDate.isBlank() || odpd.getDep_tm().compareTo(dep_tmBeginDate) >= 0)
					&& (dep_tmEndDate.isBlank() || odpd.getDep_tm().compareTo(dep_tmEndDate) <= 0)
					&& (dlvy_no.isBlank() || odpd.getDlvy_no().contains(dlvy_no))
					&& (ord_no.isBlank() || odpd.getOrd_no().contains(ord_no))
					&& (prod_no.isBlank() || odpd.getProd_no().contains(prod_no))
					&& (prod_name.isBlank() || odpd.getProd_name().contains(prod_name))
					&& (send_name.isBlank() || odpd.getSend_name().contains(send_name))
					&& (send_tel.isBlank() || odpd.getSend_tel().contains(send_tel))
					&& (send_addr.isBlank() || odpd.getSend_addr().contains(send_addr))
					&& (recv_name.isBlank() || odpd.getRecv_name().contains(recv_name))
					&& (recv_tel.isBlank() || odpd.getRecv_tel().contains(recv_tel))
					&& (recv_addr.isBlank() || odpd.getRecv_addr().contains(recv_addr)))
				continue;
			odpdList.remove(odpd);
		}
		
		List<List<String>> tbody = new ArrayList<>();
		int pageSize = odpdList.size() / ROW_SIZE + ((odpdList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (odpdList.size() > 0) {
			String tempNo = "";
			
			for (int i = startRowNum; i <= odpdList.size(); i++) {
				if (endRowNum <= i) break;
				
				ODPDBean odpd = odpdList.get(i);
				
				List<String> row = new ArrayList<>();
				
				if (tempNo.equals(odpd.getDlvy_no())) {
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				} else {
					row.add(odpd.getReg_tm());
					row.add(odpd.getDep_tm());
					row.add(odpd.getDlvy_no());
					row.add(odpd.getOrd_no());
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add(odpd.getSend_name());
					row.add(odpd.getSend_tel());
					row.add(odpd.getSend_addr());
					row.add(odpd.getRecv_name());
					row.add(odpd.getRecv_tel());
					row.add(odpd.getRecv_addr());

					tempNo = odpd.getDlvy_no();
				}
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		 
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dep_tmBeginDate", dep_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("dep_tmEndDate", dep_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dlvy_no", dlvy_no));
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_no", prod_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_name", prod_name));
		searchKeyAndValues.add(new Pair<String, String>("send_name", send_name));
		searchKeyAndValues.add(new Pair<String, String>("send_tel", send_tel));
		searchKeyAndValues.add(new Pair<String, String>("send_addr", send_addr));
		searchKeyAndValues.add(new Pair<String, String>("recv_name", recv_name));
		searchKeyAndValues.add(new Pair<String, String>("recv_tel", recv_tel));
		searchKeyAndValues.add(new Pair<String, String>("recv_addr", recv_addr));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/dlvy/process");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "배송중");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		
		return "admin/admin";
	}
	
	@GetMapping("/complete")
	public String dlvyComplete(HttpServletRequest request, Model model,
			@ModelAttribute("reg_tmBeginDate") String reg_tmBeginDate,
			@ModelAttribute("reg_tmEndDate") String reg_tmEndDate,
			@ModelAttribute("dep_tmBeginDate") String dep_tmBeginDate,
			@ModelAttribute("dep_tmEndDate") String dep_tmEndDate,
			@ModelAttribute("arr_tmBeginDate") String arr_tmBeginDate,
			@ModelAttribute("arr_tmEndDate") String arr_tmEndDate,
			@ModelAttribute("dlvy_no") String dlvy_no,
			@ModelAttribute("ord_no") String ord_no,
			@ModelAttribute("prod_no") String prod_no,
			@ModelAttribute("prod_name") String prod_name,
			@ModelAttribute("send_name") String send_name,
			@ModelAttribute("send_tel") String send_tel,
			@ModelAttribute("send_addr") String send_addr, 
			@ModelAttribute("recv_name") String recv_name,
			@ModelAttribute("recv_tel") String recv_tel,
			@ModelAttribute("recv_addr") String recv_addr,
			@RequestParam(name = "selectedPageNum", defaultValue = "1") int selectedPageNum) {
		List<Map<String, String>> thead = new ArrayList<>();
		thead.add(Format.getMap("title=주문일&type=date&name=reg_tm"));
		thead.add(Format.getMap("title=출발일&type=date&name=dep_tm"));
		thead.add(Format.getMap("title=도착일&type=date&name=arr_tm"));
		thead.add(Format.getMap("title=배송번호&type=keyword&name=dlvy_no"));
		thead.add(Format.getMap("title=주문번호&type=keyword&name=ord_no"));
		thead.add(Format.getMap("title=상품번호&type=keyword&name=prod_no"));
		thead.add(Format.getMap("title=상품명&type=keyword&name=prod_name"));
		thead.add(Format.getMap("title=수량"));
		thead.add(Format.getMap("title=주문인&type=keyword&name=ordr_name"));
		thead.add(Format.getMap("title=주문인 전화번호&type=keyword&name=ordr_tel"));
		thead.add(Format.getMap("title=주문인 주소&type=keyword&name=ordr_addr"));
		thead.add(Format.getMap("title=수령인&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 전화번호&type=keyword&name=recv_name"));
		thead.add(Format.getMap("title=수령인 주소&type=keyword&name=recv_addr"));
	
		List<List<String>> tbody = new ArrayList<>();
		List<ODPDBean> odpdList = odpdService.getODPDListReady();
		for (int i = odpdList.size() - 1; i >= 0; i--) {
			ODPDBean odpd = odpdList.get(i);
			if ((reg_tmBeginDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmBeginDate) >= 0)
					&& (reg_tmEndDate.isBlank() || odpd.getReg_tm().compareTo(reg_tmEndDate) <= 0)
					&& (dep_tmBeginDate.isBlank() || odpd.getDep_tm().compareTo(dep_tmBeginDate) >= 0)
					&& (dep_tmEndDate.isBlank() || odpd.getDep_tm().compareTo(dep_tmEndDate) <= 0)
					&& (arr_tmBeginDate.isBlank() || odpd.getArr_tm().compareTo(arr_tmBeginDate) >= 0)
					&& (arr_tmEndDate.isBlank() || odpd.getArr_tm().compareTo(arr_tmEndDate) <= 0)
					&& (dlvy_no.isBlank() || odpd.getDlvy_no().contains(dlvy_no))
					&& (ord_no.isBlank() || odpd.getOrd_no().contains(ord_no))
					&& (prod_no.isBlank() || odpd.getProd_no().contains(prod_no))
					&& (prod_name.isBlank() || odpd.getProd_name().contains(prod_name))
					&& (send_name.isBlank() || odpd.getSend_name().contains(send_name))
					&& (send_tel.isBlank() || odpd.getSend_tel().contains(send_tel))
					&& (send_addr.isBlank() || odpd.getSend_addr().contains(send_addr))
					&& (recv_name.isBlank() || odpd.getRecv_name().contains(recv_name))
					&& (recv_tel.isBlank() || odpd.getRecv_tel().contains(recv_tel))
					&& (recv_addr.isBlank() || odpd.getRecv_addr().contains(recv_addr)))
				continue;
			odpdList.remove(odpd);
		}
		
		int pageSize = odpdList.size() / ROW_SIZE + ((odpdList.size() % ROW_SIZE) > 0 ? 1 : 0); 
		int startRowNum = (selectedPageNum - 1) * ROW_SIZE;
		int endRowNum = (selectedPageNum) * ROW_SIZE;
		
		if (odpdList.size() > 0) {
			String tempNo = "";
			
			for (int i = startRowNum; i <= odpdList.size(); i++) {
				if (endRowNum <= i) break;
				
				ODPDBean odpd = odpdList.get(i);
				
				List<String> row = new ArrayList<>();
				
				if (tempNo.equals(odpd.getDlvy_no())) {
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				} else {
					row.add(odpd.getReg_tm());
					row.add(odpd.getDep_tm());
					row.add(odpd.getArr_tm());
					row.add(odpd.getDlvy_no());
					row.add(odpd.getOrd_no());
					row.add(odpd.getProd_no());
					row.add(odpd.getProd_name());
					row.add(String.valueOf(odpd.getProd_qnty()));
					row.add(odpd.getSend_name());
					row.add(odpd.getSend_tel());
					row.add(odpd.getSend_addr());
					row.add(odpd.getRecv_name());
					row.add(odpd.getRecv_tel());
					row.add(odpd.getRecv_addr());
					
					tempNo = odpd.getDlvy_no();
				}
				
				tbody.add(row);
			}
		} else {
			List<String> row = new ArrayList<>();
			row.add("결과없음");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			tbody.add(row);
		}
		 
		List<Pair<String, String>> searchKeyAndValues = new ArrayList<>();
		searchKeyAndValues.add(new Pair<String, String>("reg_tmBeginDate", reg_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("reg_tmEndDate", reg_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dep_tmBeginDate", dep_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("dep_tmEndDate", dep_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("arr_tmBeginDate", arr_tmBeginDate));
		searchKeyAndValues.add(new Pair<String, String>("arr_tmEndDate", arr_tmEndDate));
		searchKeyAndValues.add(new Pair<String, String>("dlvy_no", dlvy_no));
		searchKeyAndValues.add(new Pair<String, String>("ord_no", ord_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_no", prod_no));
		searchKeyAndValues.add(new Pair<String, String>("prod_name", prod_name));
		searchKeyAndValues.add(new Pair<String, String>("send_name", send_name));
		searchKeyAndValues.add(new Pair<String, String>("send_tel", send_tel));
		searchKeyAndValues.add(new Pair<String, String>("send_addr", send_addr));
		searchKeyAndValues.add(new Pair<String, String>("recv_name", recv_name));
		searchKeyAndValues.add(new Pair<String, String>("recv_tel", recv_tel));
		searchKeyAndValues.add(new Pair<String, String>("recv_addr", recv_addr));
		
		model.addAttribute("searchKeyAndValues", searchKeyAndValues);
		model.addAttribute("searchPath", "/admin/dlvy/complete");
		model.addAttribute("content", "/WEB-INF/views/admin/table.jsp");
		model.addAttribute("frameName", "배송완료");
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("selectedPageNum", selectedPageNum);
		model.addAttribute("thead", thead);
		model.addAttribute("tbody", tbody);
		
		return "admin/admin";
	}
}
