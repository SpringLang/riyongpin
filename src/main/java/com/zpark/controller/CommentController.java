package com.zpark.controller;

import com.zpark.entity.Comment;
import com.zpark.entity.PageBean;
import com.zpark.service.CommentService;
import com.zpark.util.PageUtil;
import com.zpark.util.ResponseUtil;
import com.zpark.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 将页面字符串类型的日期转换成date类型
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		map.put("start",pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> commentList = commentService.findCommentList(map);
		Long total = commentService.getCommentCount(map);
		String pageCode = PageUtil.genPaginationNoParam(request.getContextPath()+"/comment/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("commentList", commentList);
		mav.addObject("pageCode",pageCode);
		mav.addObject("total",total);
		mav.setViewName("comment");
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(Comment comment)throws Exception{
		commentService.saveComment(comment);
		return "redirect:/comment/list.do";
	}
	
	@RequestMapping("/listComment")
	public String listComment(String content,String page,String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		map.put("content",content);
		List<Comment> commentList=commentService.findCommentList(map);
		long total=commentService.getCommentCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rowss=JSONArray.fromObject(commentList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	@RequestMapping("/loadCommentById")
	public String loadCommentById(Integer commentId,HttpServletResponse response)throws Exception{
		Comment comment=commentService.getCommentById(commentId);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONObject result=JSONObject.fromObject(comment, jsonConfig);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/replay")
	public String replay(Comment comment,HttpServletResponse response)throws Exception{
		commentService.saveReplyContent(comment);
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
			commentService.delete(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
