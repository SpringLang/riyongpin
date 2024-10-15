package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.News;

public interface NewsDao {
	
	/**
	 * 初始化新闻列表及根据条件查找新闻
	 * @param map
	 * @return
	 */
	public List<News> findNewsList(Map<String,Object> map);
	
	/**
	 * 通过id查找新闻
	 * @param newsId
	 * @return
	 */
	public News getNewsById(int newsId);
	
	/**
	 * 根据条件查询留言数量
	 * @param map
	 * @return
	 */
	public Long getNewsCount(Map<String,Object> map);
	
	
	/**
	 * 保存
	 * @param news
	 */
	public void saveNews(News news);
	
	/**
	 * 修改
	 * @param news
	 */
	public void updateNews(News news);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
}
