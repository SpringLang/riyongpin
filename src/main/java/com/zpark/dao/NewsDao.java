package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.News;

public interface NewsDao {
	
	/**
	 * ��ʼ�������б�����������������
	 * @param map
	 * @return
	 */
	public List<News> findNewsList(Map<String,Object> map);
	
	/**
	 * ͨ��id��������
	 * @param newsId
	 * @return
	 */
	public News getNewsById(int newsId);
	
	/**
	 * ����������ѯ��������
	 * @param map
	 * @return
	 */
	public Long getNewsCount(Map<String,Object> map);
	
	
	/**
	 * ����
	 * @param news
	 */
	public void saveNews(News news);
	
	/**
	 * �޸�
	 * @param news
	 */
	public void updateNews(News news);
	
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(Integer id);
}
