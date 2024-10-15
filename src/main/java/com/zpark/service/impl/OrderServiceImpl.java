package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.OrderDao;
import com.zpark.entity.Order;
import com.zpark.entity.OrderProduct;
import com.zpark.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderDao orderDao;
	
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}
	
	public void saveOrderProduct(List<OrderProduct> list){
		orderDao.saveOrderProduct(list);
	}

	public List<Order> findOrder(Map<String,Object> map) {
		return orderDao.findOrder(map);
	}

	
	public void updateOrderStatus(Map<String,Object> map) {
		orderDao.updateOrderStatus(map);
	}

	public Long getOrderCount(Map<String,Object> map) {
		return orderDao.getOrderCount(map);
	}


	public Order getOrderById(Integer id) {
		return orderDao.getOrderById(id);
	}


}
