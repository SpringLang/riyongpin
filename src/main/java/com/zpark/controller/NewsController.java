package com.zpark.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zpark.entity.News;
import com.zpark.entity.PageBean;
import com.zpark.service.NewsService;
import com.zpark.util.NavUtil;
import com.zpark.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Resource
	private NewsService newsService;
	
	/**
	 * 将页面字符串类型的日期转换成date类型
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }
	
	@RequestMapping("/showNews/{id}")
	public ModelAndView showNews(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		News news = newsService.getNewsById(id);
		mav.addObject("news",news);
		mav.addObject("mainPage","news/newsDetails.jsp");
		mav.addObject("navCode",NavUtil.genNavCode("新闻信息",request));
		mav.setViewName("newsMain");
		return mav;
	}
	
	@RequestMapping("/list")
	public String list(String title,String page,String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		map.put("title",title);
		List<News> newsList=newsService.findNewsList(map);
		long total=newsService.getNewsCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rowss=JSONArray.fromObject(newsList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/saveorupdate")
	public String save(News news,HttpServletResponse response)throws Exception{
		if(news.getId()==0){
			newsService.saveNews(news);
		}else{
			newsService.updateNews(news);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			newsService.delete(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
}
