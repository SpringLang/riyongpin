package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.CommentDao;
import com.zpark.entity.Comment;
import com.zpark.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentDao commentDao;

	public List<Comment> findCommentList(Map<String, Object> map) {
		return commentDao.findCommentList(map);
	}

	public long getCommentCount(Map<String, Object> map) {
		return commentDao.getCommentCount(map);
	}

	public void saveComment(Comment comment){
		commentDao.saveComment(comment);
	}
	
	public void saveReplyContent (Comment comment){
		commentDao.saveReplyContent(comment);
	}
	
	public Comment getCommentById(Integer commentId){
		return commentDao.getCommentById(commentId);
	}
	
	public void delete(Integer commentId){
		commentDao.delete(commentId);
	}
}
