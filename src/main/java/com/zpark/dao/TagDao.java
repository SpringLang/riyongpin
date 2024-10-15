package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Tag;

public interface TagDao {
	
	/**
	 * ��ʼ����ǩ�б�����������ѯ��ǩ��Ϣ
	 * @param map
	 * @return
	 */
	public List<Tag> findTagList(Map<String,Object> map);
	
	/**
	 * ����id��ѯ��ǩ��Ϣ
	 * @param tagId
	 * @return
	 */
	public Tag getTagById(int tagId);
	
	/**
	 * ����������ѯ��ǩ����
	 * @param map
	 * @return
	 */
	public Long getTagCount(Map<String,Object> map);
	
	/**
	 * �����ǩ��Ϣ
	 * @param Tag
	 */
	public void saveTag(Tag Tag);
	
	/**
	 * �޸ı�ǩ��Ϣ
	 * @param tag
	 */
	public void updateTag(Tag tag);
	
	/**
	 * ɾ����ǩ��Ϣ
	 * @param id
	 */
	public void delete(Integer id);
}
