package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Order;
import com.zpark.entity.OrderProduct;

public interface OrderService {

	public void saveOrder(Order order);
	
	public void saveOrderProduct(List<OrderProduct> list);
	
	public List<Order> findOrder(Map<String,Object> map);
	
	public void updateOrderStatus(Map<String,Object> map);
	
	public Order getOrderById(Integer id);

	public Long getOrderCount(Map<String,Object> map);
}
