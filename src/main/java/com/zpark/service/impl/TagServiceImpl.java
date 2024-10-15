package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.TagDao;
import com.zpark.entity.Tag;
import com.zpark.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService{

	@Resource
	private TagDao tagDao;
	
	public List<Tag> findTagList(Map<String,Object> map){
		return tagDao.findTagList(map);
	}
	
	public Tag getTagById(int tagId){
		return tagDao.getTagById(tagId);
	}
	
	public Long getTagCount(Map<String,Object> map){
		return tagDao.getTagCount(map);
	}
	
	public void saveTag(Tag Tag){
		tagDao.saveTag(Tag);
	}
	
	public void updateTag(Tag tag){
		tagDao.updateTag(tag);
	}
	
	public void delete(Integer id){
		tagDao.delete(id);
	}

}
