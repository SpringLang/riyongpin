package com.zpark.entity;

import java.util.Date;

/**
 * ����ʵ����
 * @author yuyang
 *
 */
public class News {

	private int id;
	private String title;//��Ŀ
	private String content;//����
	private Date createTime;//����ʱ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
