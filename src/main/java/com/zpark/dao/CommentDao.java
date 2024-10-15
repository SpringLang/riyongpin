package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Comment;

public interface CommentDao {
	
	/**
	 * �����б��ʼ��������������ѯ
	 * @param map
	 * @return
	 */
	public List<Comment> findCommentList(Map<String,Object> map);
	
	
	/**
	 * ����������ѯ��������
	 * @param map
	 * @return
	 */
	public long getCommentCount(Map<String, Object> map);
	
	
	/**
	 * ��������
	 * @param comment
	 */
	public void saveComment(Comment comment);
	
	/**
	 * �������Իظ�����
	 * @param comment
	 */
	public void saveReplyContent (Comment comment);
	
	
	/**
	 * ����id��������
	 * @param commentId
	 * @return
	 */
	public Comment getCommentById(Integer commentId);
	
	
	/**
	 * ɾ������
	 * @param commentId
	 */
	public void delete(Integer commentId);
}
