package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.NoticeDao;
import com.zpark.entity.Notice;
import com.zpark.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Resource
	private NoticeDao noticeDAO;
	
	public List<Notice> findNoticeList(Map<String, Object> map) {
		return noticeDAO.findNoticeList(map);
	}

	public Notice getNoticeById(int noticeId) {
		return noticeDAO.getNoticeById(noticeId);
	}

	public Long getNoticeCount(Map<String,Object> map){
		return noticeDAO.getNoticeCount(map);
	}
	
	public void saveNotice(Notice notice){
		noticeDAO.saveNotice(notice);
	}
	
	public void updateNotice(Notice notice){
		noticeDAO.updateNotice(notice);
	}
	
	public void delete(Integer id){
		noticeDAO.delete(id);
	}

}
