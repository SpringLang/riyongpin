package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Notice;

public interface NoticeDao {
	
	/**
	 * ��ʼ�������б�����������ѯ����
	 * @param map
	 * @return
	 */
	public List<Notice> findNoticeList(Map<String,Object> map);
	
	/**
	 * ���ݹ���id��ѯ����
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(int noticeId);
	
	/**
	 * ����������ѯ��������
	 * @param map
	 * @return
	 */
	public Long getNoticeCount(Map<String,Object> map);
	
	/**
	 * ����
	 * @param notice
	 */
	public void saveNotice(Notice notice);
	
	/**
	 * �޸�
	 * @param notice
	 */
	public void updateNotice(Notice notice);
	
	/**
	 * ɾ��
	 * @param notice
	 */
	public void delete(Integer id);
}
