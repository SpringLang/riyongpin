package com.zpark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zpark.entity.News;
import com.zpark.entity.Notice;
import com.zpark.entity.PageBean;
import com.zpark.entity.Product;
import com.zpark.entity.ProductBigType;
import com.zpark.entity.Tag;
import com.zpark.service.NewsService;
import com.zpark.service.NoticeService;
import com.zpark.service.ProductBigTypeService;
import com.zpark.service.ProductService;
import com.zpark.service.TagService;
import com.zpark.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/system")
public class SystemController{

	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private NoticeService noticeService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private ProductService productService;

	
	/**
	 * Ë¢ÐÂÏµÍ³»º´æ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		PageBean pageBean=new PageBean(1,7);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		
		List<Tag> tagList=tagService.findTagList(map);
		application.setAttribute("tagList", tagList);
		
		List<Notice> noticeList=noticeService.findNoticeList(map);
		application.setAttribute("noticeList", noticeList);
		
		List<News> newsList=newsService.findNewsList(map);
		application.setAttribute("newsList", newsList);
		
		map.put("specialPrice", 1);
		List<Product> specialPriceProductList=productService.findProductList(map);
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		map.put("hotProduct", 1);
		List<Product> hotProductList=productService.findProductList(map);
		application.setAttribute("hotProductList", hotProductList);
		
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}