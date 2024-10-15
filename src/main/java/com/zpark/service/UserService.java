package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.User;

/**
 * 用户Service接口
 * @author yuyang
 *
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 查询当前用户是否存在
	 * @param userName
	 * @return
	 */
	public boolean exitUserWithUserName(String userName);
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	public List<User> findUserList(Map<String,Object> map);
	
	public Long getUserCount(Map<String,Object> map);
	
	public void delete(Integer id);
	
	public User getUserById(int id);
	
	public void updateUser(User user);
}
