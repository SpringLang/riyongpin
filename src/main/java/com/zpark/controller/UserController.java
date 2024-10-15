package com.zpark.controller;

import com.zpark.entity.PageBean;
import com.zpark.entity.User;
import com.zpark.service.UserService;
import com.zpark.util.NavUtil;
import com.zpark.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller层
 * 
 * @author yuyang
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	
	/**
	 * 将页面字符串类型的日期转换成date类型
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }
	

	@RequestMapping("/existUserWithUserName")
	public String existUserWithUserName(@RequestParam(value = "userName", required = false) String userName,
			HttpServletResponse response) throws Exception {
		boolean exist = userService.exitUserWithUserName(userName);
		JSONObject result = new JSONObject();
		if (exist) {
			result.put("exist", true);
		} else {
			result.put("exist", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	
	@RequestMapping("/register")
	public ModelAndView register(User user)throws Exception {
		ModelAndView mav = new ModelAndView();
		userService.saveUser(user);
		mav.addObject("user",user);
		mav.setViewName("reg-result");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("imageCode") String imageCode,User user,HttpSession session)throws Exception{
		ModelAndView mav = new ModelAndView();
		User currentUser=userService.login(user);
		if(!imageCode.equals(session.getAttribute("sRand"))){
			mav.addObject("error","验证码错误！");
			
			if(user.getStatus()==2){
				mav.setViewName("admin/login");
			}else{
				mav.setViewName("login");
			}
			
		}else if(currentUser==null){
			mav.addObject("error","用户名或密码错误！");
			if(user.getStatus()==2){
				mav.setViewName("admin/login");
			}else{
				mav.setViewName("login");
			}
		}else{
			session.setAttribute("currentUser", currentUser);
			if(user.getStatus()==2){
				mav.setViewName("admin/main");
			}else{
				mav.setViewName("index");
			}
			
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session,HttpServletRequest request)throws Exception{
		session.invalidate();
		return new ModelAndView("redirect:/index.jsp");
	}
	
	@RequestMapping("/logout2")
	public ModelAndView logout2(HttpSession session,HttpServletRequest request)throws Exception{
		session.invalidate();
		return new ModelAndView("redirect:/admin/login.jsp");
	}
	
	@RequestMapping("/userCenter")
	public ModelAndView userCenter(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("navCode",NavUtil.genNavCode("个人中心",request));
		mav.addObject("mainPage","userCenter/ucDefault.jsp");
		mav.setViewName("userCenter");
		return mav;
	}
	
	@RequestMapping("/getUserInfo")
	public ModelAndView getUserInfo(HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("navCode",NavUtil.genNavCode("个人中心",request));
		mav.addObject("mainPage","userCenter/userInfo.jsp");
		mav.setViewName("userCenter");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(HttpSession session,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		User user=(User) session.getAttribute("currentUser");
		mav.addObject("navCode",NavUtil.genNavCode("个人中心",request));
		mav.addObject("mainPage","userCenter/userSave.jsp");
		mav.addObject("currentUser",user);
		mav.setViewName("userCenter");
		return mav;
	}
	
	
	@RequestMapping("/save")
	public ModelAndView save(User user,HttpSession session,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		userService.updateUser(user);
		session.setAttribute("currentUser", user);
		mav.addObject("navCode",NavUtil.genNavCode("个人中心",request));
		mav.addObject("mainPage","userCenter/userInfo.jsp");
		mav.setViewName("userCenter");
		return mav;
	}
	
	/**
	 * 获取用户分页列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(String userName,String page,String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("userName", userName);
		List<User> userList=userService.findUserList(map);
		long total=userService.getUserCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rowss=JSONArray.fromObject(userList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	} 
	
	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUser")
	public String saveUser(User user,HttpServletResponse response)throws Exception{
		if(user.getId()==0){
			userService.saveUser(user);
		}else{
			userService.updateUser(user);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/modifyPassword")
	public String modifyPassword(User user,HttpServletResponse response)throws Exception{
		User u=userService.getUserById(user.getId());
		u.setPassword(user.getPassword());
		userService.updateUser(u);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
}
