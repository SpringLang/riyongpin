package com.zpark.controller;

import com.zpark.entity.*;
import com.zpark.service.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitController implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		PageBean pageBean=new PageBean(1,7);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		ServletContext application=servletContextEvent.getServletContext();
		
		ProductBigTypeService productBigTypeService=(ProductBigTypeService)applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		
		TagService tagService=(TagService)applicationContext.getBean("tagService");
		List<Tag> tagList=tagService.findTagList(map);
		application.setAttribute("tagList", tagList);
		
		NoticeService noticeService=(NoticeService)applicationContext.getBean("noticeService");
		List<Notice> noticeList=noticeService.findNoticeList(map);
		application.setAttribute("noticeList", noticeList);
		
		NewsService newsService=(NewsService)applicationContext.getBean("newsService");
		List<News> newsList=newsService.findNewsList(map);
		application.setAttribute("newsList", newsList);
		
		ProductService productService=(ProductService)applicationContext.getBean("productService");
		map.put("specialPrice", 1);
		List<Product> specialPriceProductList=productService.findProductList(map);
		application.setAttribute("specialPriceProductList", specialPriceProductList);

		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("start", pageBean.getStart());
		map2.put("size", pageBean.getPageSize());
		map2.put("hot", 1);
		List<Product> hotProductList=productService.findProductList(map2);
		application.setAttribute("hotProductList", hotProductList);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}

}
