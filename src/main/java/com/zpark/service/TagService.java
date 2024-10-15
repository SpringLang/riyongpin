package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Tag;

public interface TagService {

	public List<Tag> findTagList(Map<String,Object> map);
	
	public Tag getTagById(int tagId);
	
	public Long getTagCount(Map<String,Object> map);
	
	public void saveTag(Tag Tag);
	
	public void updateTag(Tag tag);
	
	public void delete(Integer id);

}
