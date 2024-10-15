package com.zpark.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品实体类
 * @author yuyang
 *
 */
public class Product {

	private int id;
	private String name;//名称
	private Integer price;//价格
	private String stock;//库存
	private String proPic;//图片
	private String description;//商品描述
	private String hot;//是否热卖
	private Date hotTime;//热卖时间
	private String specialPrice;//是否特价
	private Date specialPriceTime;//特价时间
	
	private ProductBigType bigType;
	private ProductSmallType smallType;
	private List<OrderProduct> orderProductList=new ArrayList<OrderProduct>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	public Date getHotTime() {
		return hotTime;
	}
	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}
	public String getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}
	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	public ProductSmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public String getStock() {
		return stock;
	}
	
	
	
	
}
