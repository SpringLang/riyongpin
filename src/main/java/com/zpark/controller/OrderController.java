package com.zpark.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zpark.entity.Order;
import com.zpark.entity.OrderProduct;
import com.zpark.entity.PageBean;
import com.zpark.entity.Product;
import com.zpark.entity.ShoppingCart;
import com.zpark.entity.ShoppingCartItem;
import com.zpark.entity.User;
import com.zpark.service.OrderService;
import com.zpark.util.DateUtil;
import com.zpark.util.NavUtil;
import com.zpark.util.ResponseUtil;
import com.zpark.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource
	private OrderService orderService;

	@RequestMapping("/save")
	public ModelAndView save(HttpSession session,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		Order order=new Order();
		User currentUsre=(User)session.getAttribute("currentUser");
		order.setUser(currentUsre);
		order.setOrderNo(DateUtil.getCurrentDateStr());
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		float cost=0;
		List<OrderProduct> orderProductList=new LinkedList<OrderProduct>();
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			Product product=shoppingCartItem.getProduct();
			OrderProduct orderProduct=new OrderProduct();
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			cost+=product.getPrice()*shoppingCartItem.getCount();
			orderProductList.add(orderProduct);
		}
		order.setOrderProductList(orderProductList);
		order.setCost(cost);
		
		orderService.saveOrder(order);
		
		for (OrderProduct orderProduct : orderProductList) {
			orderProduct.setOrder(order);
		}
		orderService.saveOrderProduct(orderProductList);
		
		mav.addObject("navCode",NavUtil.genNavCode("购物",request));
		mav.addObject("mainPage","shopping/shopping-result.jsp");
		mav.setViewName("shoppingMain");
		session.removeAttribute("shoppingCart");
		
		return mav;
	}
	
	@RequestMapping("/findOrder")
	public ModelAndView findOrder(String orderNo,Order order,HttpSession session,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String, Object>();
		User currentUser=(User) session.getAttribute("currentUser");
		if(order==null){
			order=new Order();
		}
		order.setUser(currentUser);
		map.put("order",order);
		map.put("orderUser",order.getUser());
		map.put("orderUserId",order.getUser().getId());
		map.put("orderNo",orderNo);
		List<Order> orderList=orderService.findOrder(map);
		mav.addObject("navCode",NavUtil.genNavCode("个人中心",request));
		mav.addObject("mainPage","userCenter/orderList.jsp");
		mav.addObject("currentUser",currentUser);
		mav.addObject("orderList",orderList);
		mav.setViewName("userCenter");
		return mav;
	}
	
	@RequestMapping("/confirmReceive")
	public String confirmReceive(int status,String orderNo,HttpServletResponse response)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("orderNo",orderNo);
		orderService.updateOrderStatus(map);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/list")
	public String list(String page,String rows,String orderNo,String userName,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		map.put("orderNo",orderNo);
		map.put("userName",userName);
		List<Order> orderList=orderService.findOrder(map);
		long total=orderService.getOrderCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(User.class,new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
		JSONArray rowss=JSONArray.fromObject(orderList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/findProductListByOrderId")
	public String findProductListByOrderId(String id,HttpServletResponse response)throws Exception{
		if(StringUtil.isEmpty(id)){
			return null;
		}
		Order order=orderService.getOrderById(Integer.parseInt(id));
		List<OrderProduct> orderProductList=order.getOrderProductList();
		JSONObject result=new JSONObject();
		JSONArray rows=new JSONArray();
		for(OrderProduct orderProduct:orderProductList){
			Product product=orderProduct.getProduct();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("productName", product.getName());
			jsonObject.put("proPic", product.getProPic());
			jsonObject.put("price", product.getPrice());
			jsonObject.put("num", orderProduct.getNum());
			jsonObject.put("subtotal", product.getPrice()*orderProduct.getNum());
			rows.add(jsonObject);
		}
		result.put("rows", rows);
		result.put("total", rows.size());
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/modifyOrderStatus")
	public String modifyOrderStatus(String orderNos,int status,HttpServletResponse response)throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		String []orderNoStr=orderNos.split(",");
		for(int i=0;i<orderNoStr.length;i++){
			map.put("status", status);
			map.put("orderNo", orderNoStr[i]);
			orderService.updateOrderStatus(map);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
}
