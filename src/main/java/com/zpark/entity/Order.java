package com.zpark.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * @author yuyang
 *
 */
public class Order {

	private int id;
	private String orderNo;//订单号
	private Date createTime;//创建时间
	private float cost;//总钱数
	private int status; // 状态  1 待审核  2 审核通过 3 卖家已发货  4 已收获
	private User user;//用户
	
	private List<OrderProduct> orderProductList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	
	
	
}
