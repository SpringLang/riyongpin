package com.zpark.service;

import java.util.List;
import java.util.Map;


import com.zpark.entity.Comment;

public interface CommentService {

	public List<Comment> findCommentList(Map<String,Object> map);
	
	public long getCommentCount(Map<String, Object> map);

	public void saveComment(Comment comment);
	
	public void saveReplyContent (Comment comment);
	
	public Comment getCommentById(Integer commentId);
	
	public void delete(Integer commentId);
}
