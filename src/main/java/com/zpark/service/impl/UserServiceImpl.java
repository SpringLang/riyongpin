package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.UserDao;
import com.zpark.entity.User;
import com.zpark.service.UserService;

/**
 * 用户Service实现类
 * @author yuyang
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	/**
	 * 增加用户
	 */
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
	/**
	 * 查询当前用户是否存在
	 * @param userName
	 * @return
	 */
	public boolean exitUserWithUserName(String userName){
		long count = userDao.exitUserWithUserName(userName);
		 if(count>0){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	/**
	 * 用户登录
	 */
	public User login(User user) {
		return userDao.login(user);
	}

	public List<User> findUserList(Map<String,Object> map){
		return userDao.findUserList(map);
	}

	public Long getUserCount(Map<String,Object> map){
		return userDao.getUserCount(map);
	}

	public void delete(Integer id) {
		userDao.delete(id);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}


}
