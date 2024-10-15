package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Comment;

public interface CommentDao {
	
	/**
	 * 留言列表初始化及根据条件查询
	 * @param map
	 * @return
	 */
	public List<Comment> findCommentList(Map<String,Object> map);
	
	
	/**
	 * 根据条件查询留言总数
	 * @param map
	 * @return
	 */
	public long getCommentCount(Map<String, Object> map);
	
	
	/**
	 * 保存留言
	 * @param comment
	 */
	public void saveComment(Comment comment);
	
	/**
	 * 保存留言回复内容
	 * @param comment
	 */
	public void saveReplyContent (Comment comment);
	
	
	/**
	 * 根据id查找留言
	 * @param commentId
	 * @return
	 */
	public Comment getCommentById(Integer commentId);
	
	
	/**
	 * 删除留言
	 * @param commentId
	 */
	public void delete(Integer commentId);
}
