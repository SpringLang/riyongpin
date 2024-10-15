package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.User;

/**
 * 用户DAO接口
 */
public interface UserDao {
	
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
	public Long exitUserWithUserName(String userName);
	
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 通过用户Id查找用户
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 用户列表
	 * @param map
	 * @return
	 */
	public List<User> findUserList(Map<String,Object> map);
	
	/**
	 * 获取用户总数
	 * @param user
	 * @return
	 */
	public Long getUserCount(Map<String,Object> map);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(Integer id);
}
