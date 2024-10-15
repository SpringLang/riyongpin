package com.zpark.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zpark.entity.Product;
import com.zpark.entity.ShoppingCart;
import com.zpark.entity.ShoppingCartItem;
import com.zpark.entity.User;
import com.zpark.service.ProductService;
import com.zpark.util.NavUtil;
import com.zpark.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {
	
	@Resource
	private ProductService productService;
	
	/**
	 * ��Ʒ���빺�ﳵ
	 * @param productId
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addShoppingCartItem")
	public String addShoppingCartItem(@RequestParam(value="productId",required=false) Integer productId,HttpSession session,HttpServletResponse response)throws Exception{
		
		Product product = productService.getProductById(productId);
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();
		
		boolean flag = true;
		for (ShoppingCartItem scI : shoppingCartItemList) {
			if (scI.getProduct().getId()==product.getId()) {
				scI.setCount(scI.getCount()+1);
				flag = false;
				break;
			}
		}
		
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		if(flag){
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		JSONObject result = new JSONObject();
		result.put("success",true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * ����ť����ʵ��
	 * @param productId
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/buy")
	public ModelAndView buy(@RequestParam(value="productId",required=false) Integer productId,HttpSession session,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		
		Product product = productService.getProductById(productId);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
			User currentUser=(User) session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		boolean flag=true;
		for(ShoppingCartItem scI:shoppingCartItemList){
			if(scI.getProduct().getId()==product.getId()){
				scI.setCount(scI.getCount()+1);
				flag=false;
				break;
			}
		}
		
		ShoppingCartItem shoppingCartItem=new ShoppingCartItem();
		
		if(flag){
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		mav.addObject("mainPage","shopping/shopping.jsp");
		mav.addObject("navCode",NavUtil.genNavCode("���ﳵ",request));
		mav.setViewName("shoppingMain");
		return mav;
	}
	
	/**
	 * �޸���Ʒ���
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateShoppingCartItem")
	public String updateShoppingCartItem(@RequestParam(value="productId",required=false)Integer productId,@RequestParam(value="count",required=false)Integer count,
			HttpSession session,HttpServletResponse response)throws Exception{
		Product product = productService.getProductById(productId);
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		for(ShoppingCartItem scI:shoppingCartItemList){
			if(scI.getProduct().getId()==product.getId()){
				scI.setCount(count);
				break;
			}
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * ɾ�����ﳵ�б���Ʒ
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("removeShoppingCartItem")
	public String removeShoppingCartItem(@RequestParam(value="productId",required=false)Integer productId,HttpSession session,HttpServletRequest request)throws Exception{
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		for(int i=0;i<shoppingCartItemList.size();i++){
			if(productId==shoppingCartItemList.get(i).getProduct().getId()){
				shoppingCartItemList.remove(i);
				break;
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItemList);
		session.setAttribute("shoppingCart", shoppingCart);
		return "redirect:/shopping/list.do";
	}
	
	
	/**
	 * ���ﳵ�б�
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("mainPage","shopping/shopping.jsp");
		mav.addObject("navCode",NavUtil.genNavCode("���ﳵ",request));
		mav.setViewName("shoppingMain");
		return mav;
	}
}
