package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Notice;

public interface NoticeDao {
	
	/**
	 * 初始化公告列表及根据条件查询公告
	 * @param map
	 * @return
	 */
	public List<Notice> findNoticeList(Map<String,Object> map);
	
	/**
	 * 根据公告id查询公告
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(int noticeId);
	
	/**
	 * 根据条件查询公告总数
	 * @param map
	 * @return
	 */
	public Long getNoticeCount(Map<String,Object> map);
	
	/**
	 * 保存
	 * @param notice
	 */
	public void saveNotice(Notice notice);
	
	/**
	 * 修改
	 * @param notice
	 */
	public void updateNotice(Notice notice);
	
	/**
	 * 删除
	 * @param notice
	 */
	public void delete(Integer id);
}
