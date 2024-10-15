package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Notice;

public interface NoticeService {

	public List<Notice> findNoticeList(Map<String, Object> map);
	
	public Notice getNoticeById(int noticeId);
	
	public Long getNoticeCount(Map<String,Object> map);
	
	public void saveNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public void delete(Integer id);
}
