package com.zpark.controller;


import com.zpark.entity.PageBean;
import com.zpark.entity.Product;
import com.zpark.entity.ProductBigType;
import com.zpark.entity.ProductSmallType;
import com.zpark.service.ProductService;
import com.zpark.util.NavUtil;
import com.zpark.util.PageUtil;
import com.zpark.util.ResponseUtil;
import com.zpark.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Resource
	private ProductService productService;

	@Resource
	private InitController initController;
	
	
	/**
	 * 将页面字符串类型的日期转换成date类型
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }
	
	
	/*
	 * 请求主页
	 */
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,@RequestParam(value="bigTypeId",required=false) String bigTypeId,@RequestParam(value="smallTypeId",required=false) String smallTypeId,@RequestParam(value="name",required=false) String name,HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pageBean=new PageBean(Integer.parseInt(page),8);
		map.put("bigTypeId", bigTypeId);
		map.put("smallTypeId", smallTypeId);
		/*if(name != null){
			map.put("name","%"+name+"%");
		}else{
			map.put("name", name);
		}*/
		map.put("name", name);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Product> productList=productService.findProductList(map);
		Long total=productService.getProductCount(map);
		StringBuffer param = new StringBuffer();
		if(StringUtil.isNotEmpty(bigTypeId)){
			param.append("bigTypeId="+bigTypeId+"&");
		}
		if(StringUtil.isNotEmpty(smallTypeId)){
			param.append("smallTypeId="+smallTypeId+"&");
		}
		if(StringUtil.isNotEmpty(name)){
			param.append("name="+name+"&");
		}

		mav.addObject("bigTypeId", bigTypeId);
		mav.addObject("smallTypeId", smallTypeId);
		mav.addObject("productList",productList);
		mav.addObject("total",total);
		mav.addObject("mainPage","product/productList.jsp");
		mav.addObject("navCode",NavUtil.genNavCode("商品列表",request));
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/product/list.do", total, Integer.parseInt(page), 8, param.toString()));
		mav.setViewName("productMain");
		
		return mav;
	}
	
	/**
	 * 获取商品详情信息
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showProduct/{id}")
	public ModelAndView showProduct(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		Product product = productService.getProductById(id);
		mav.addObject("product",product);
		saveCurrentBrowse(product, request);;
		mav.addObject("navCode",NavUtil.genNavCode("商品详情",request));
		mav.addObject("mainPage","product/productDetails.jsp");
		mav.setViewName("productMain");
		return mav;
	}
	
	
	@RequestMapping("/saveCurrentBrowse")
	private void saveCurrentBrowse(Product product,HttpServletRequest request)throws Exception{
		
		HttpSession session=request.getSession();
		List<Product> currentBrowseProduct=(List<Product>)session.getAttribute("currentBrowse");
		if(currentBrowseProduct==null){
			currentBrowseProduct=new LinkedList<Product>();
		}
		
		boolean flag=true;
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag=false;
				break;
			}
		}
		
		if(flag){
			currentBrowseProduct.add(0,product);
		}
		
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	
	@RequestMapping("/adminlist")
	public String adminlist(String productName,String page,String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("name", productName);
		List<Product> productList=productService.findProductList(map);
		long total=productService.getProductCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(ProductBigType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
		JSONArray rowss=JSONArray.fromObject(productList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/saveorupdate")
	public String save(ProductBigType bigType,ProductSmallType smallType,MultipartFile pPic,Integer bigTypeId,Integer smallTypeId,Product product,HttpServletResponse response,HttpServletRequest request)throws Exception{
		if(pPic!=null && pPic.getSize()>0){
			String realPath=request.getServletContext().getRealPath("/");
			pPic.transferTo(new File(realPath+"images/product/"+pPic.getOriginalFilename()));
			product.setProPic("images/product/"+pPic.getOriginalFilename());
		}else if(StringUtil.isEmpty(product.getProPic())){
			product.setProPic("");
		}
		
		bigType.setId(bigTypeId);
		smallType.setId(smallTypeId);
		product.setBigType(bigType);
		product.setSmallType(smallType);
		
		if(product.getId()==0){
			productService.saveProduct(product);
		}else{
			productService.updateProduct(product);
		}
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.deleteProduct(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	//设置热卖
	@RequestMapping("/setProductWithHot")
	public String setProductWithHot(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithHot(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	//取消热卖
	@RequestMapping("/delProductWithHot")
	public String delProductWithHot(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.delProductWithHot(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	//设置特价
	@RequestMapping("setProductWithSpecialPrice")
	public String setProductWithSpecialPrice(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithSpecialPrice(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	//取消特价
	@RequestMapping("delProductWithSpecialPrice")
	public String delProductWithSpecialPrice(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.delProductWithSpecialPrice(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
