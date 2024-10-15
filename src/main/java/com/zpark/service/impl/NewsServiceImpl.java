package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.NewsDao;
import com.zpark.entity.News;
import com.zpark.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService{

	@Resource
	private NewsDao newsDao;
	
	public List<News> findNewsList(Map<String, Object> map) {
		return newsDao.findNewsList(map);
	}

	public News getNewsById(int newsId) {
		return newsDao.getNewsById(newsId);
	}
	
	public Long getNewsCount(Map<String,Object> map){
		return newsDao.getNewsCount(map);
	}
	
	public void saveNews(News news){
		newsDao.saveNews(news);
	}
	
	public void updateNews(News news){
		newsDao.updateNews(news);
	}
	
	public void delete(Integer id){
		newsDao.delete(id);
	}
}
