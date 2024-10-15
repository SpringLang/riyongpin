package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.UserDao;
import com.zpark.entity.User;
import com.zpark.service.UserService;

/**
 * �û�Serviceʵ����
 * @author yuyang
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	/**
	 * �����û�
	 */
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
	/**
	 * ��ѯ��ǰ�û��Ƿ����
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
	 * �û���¼
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
