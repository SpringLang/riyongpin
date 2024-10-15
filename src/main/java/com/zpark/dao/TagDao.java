package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Tag;

public interface TagDao {
	
	/**
	 * 初始化标签列表及根据条件查询标签信息
	 * @param map
	 * @return
	 */
	public List<Tag> findTagList(Map<String,Object> map);
	
	/**
	 * 根据id查询标签信息
	 * @param tagId
	 * @return
	 */
	public Tag getTagById(int tagId);
	
	/**
	 * 根据条件查询标签数量
	 * @param map
	 * @return
	 */
	public Long getTagCount(Map<String,Object> map);
	
	/**
	 * 保存标签信息
	 * @param Tag
	 */
	public void saveTag(Tag Tag);
	
	/**
	 * 修改标签信息
	 * @param tag
	 */
	public void updateTag(Tag tag);
	
	/**
	 * 删除标签信息
	 * @param id
	 */
	public void delete(Integer id);
}
