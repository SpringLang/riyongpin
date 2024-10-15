package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.News;

public interface NewsService {

	public List<News> findNewsList(Map<String, Object> map);
	
	public News getNewsById(int newsId);
	
	public Long getNewsCount(Map<String,Object> map);
	
	public void saveNews(News news);
	
	public void updateNews(News news);
	
	public void delete(Integer id);
}
