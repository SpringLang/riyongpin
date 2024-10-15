package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.User;

/**
 * �û�Service�ӿ�
 * @author yuyang
 *
 */
public interface UserService {

	/**
	 * ����û�
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * ��ѯ��ǰ�û��Ƿ����
	 * @param userName
	 * @return
	 */
	public boolean exitUserWithUserName(String userName);
	/**
	 * �û���¼
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
