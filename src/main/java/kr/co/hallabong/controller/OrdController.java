package kr.co.hallabong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hallabong.bean.CartBean;
import kr.co.hallabong.bean.CustBean;
import kr.co.hallabong.bean.OrdBean;
import kr.co.hallabong.bean.OrdDtlBean;
import kr.co.hallabong.bean.ProdBean;
import kr.co.hallabong.service.CartService;
import kr.co.hallabong.service.CustService;
import kr.co.hallabong.service.OrdDtlService;
import kr.co.hallabong.service.OrdService;
import kr.co.hallabong.service.ProdService;
import kr.co.hallabong.util.Pair;

@Controller
@RequestMapping("/order")
public class OrdController {
	@Autowired
	private CustService custService;
	
	@Autowired
	private ProdService prodService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrdService ordService;
	
	@Autowired
	private OrdDtlService ordDtlService;
	
	@Resource(name = "loginCustBean")
	private CustBean loginCustBean;
	
	@PostMapping("/order")
	public String order(Model model, 
			@ModelAttribute("ord") OrdBean ord,
			@RequestParam("cartList") String[] prod_noList) {
		CustBean cust = custService.getLoginCustDetailInfo(loginCustBean.getId());
		ord.setOrdr_name(cust.getName());
		ord.setOrdr_tel(cust.getTel());
		ord.setOrdr_addr(cust.getAddr());
		ord.setDlvy_fee(3000);
		
		List<Pair<ProdBean, Integer>> prodList = new ArrayList<>();
		List<CartBean> cartList = cartService.getCartList(loginCustBean.getId());

		for (CartBean cart : cartList) {
			for (String prod_no : prod_noList) {
				if (cart.getProd_no().equals(prod_no)) {
					ProdBean prod = prodService.getProd(cart.getProd_no());
					prodList.add(new Pair<ProdBean, Integer>(prod, cart.getQnty()));
					break;
				}
			}
		}
		
		model.addAttribute("prodList", prodList);
		
		return "order";
	}
	
	@PostMapping("/order_proc")
	public String order_proc(@ModelAttribute("ord") OrdBean ord,
			@RequestParam("prodList") String[] prodList) {
		ord.setCust_id(loginCustBean.getId());
		ord.setType("ORD");
		ordService.addOrd(ord);
		List<OrdBean> ordList = ordService.getOrdList(loginCustBean.getId());

		String ord_no = ordList.get(0).getNo();
		for (int i = 1; i < ordList.size(); i++) {
			String comp = ordList.get(i).getNo();
			if (ord_no.compareTo(comp) < 0) {
				ord_no = comp;
			}
		}

		List<CartBean> cartList = cartService.getCartList(loginCustBean.getId());
		
		for (int i = cartList.size() - 1; i >= 0; i--) {
			CartBean cart = cartList.get(i);
			
			boolean remove = true;
			for (String prod_no : prodList) {
				if (cart.getProd_no().equals(prod_no)) {
					remove = false;
					break;
				}
			}
			
			if (remove) {
				cartList.remove(cart);
			}
		}
		
		for (CartBean cart : cartList) {
			OrdDtlBean ordDtl = new OrdDtlBean();
			ProdBean prod = prodService.getProd(cart.getProd_no());
			
			ordDtl.setOrd_no(ord_no);
			ordDtl.setProd_no(cart.getProd_no());
			ordDtl.setProd_cost(prod.getCost());
			ordDtl.setProd_sp(prod.getSp());
			ordDtl.setProd_qnty(cart.getQnty());
			
			ordDtlService.addOrdDtl(ordDtl);
			
			cartService.deleteCart(loginCustBean.getId(), cart.getProd_no());
		}
		
		return "order_pro";
	}
}
