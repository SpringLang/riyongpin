package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.User;

/**
 * �û�DAO�ӿ�
 */
public interface UserDao {
	
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
	public Long exitUserWithUserName(String userName);
	
	
	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ͨ���û�Id�����û�
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId);
	
	/**
	 * �޸��û���Ϣ
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * �û��б�
	 * @param map
	 * @return
	 */
	public List<User> findUserList(Map<String,Object> map);
	
	/**
	 * ��ȡ�û�����
	 * @param user
	 * @return
	 */
	public Long getUserCount(Map<String,Object> map);
	
	/**
	 * ɾ���û�
	 * @param user
	 */
	public void delete(Integer id);
}
