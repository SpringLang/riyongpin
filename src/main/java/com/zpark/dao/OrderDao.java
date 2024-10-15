package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Order;
import com.zpark.entity.OrderProduct;

public interface OrderDao {
	
	/**
	 * 保存订单
	 * @param notice
	 */
	public void saveOrder(Order order);
	
	/**
	 * 购买商品保存到订单商品关联表
	 * @param list
	 */
	public void saveOrderProduct(List<OrderProduct> list);
	
	/**
	 * 初始化订单列表及根据条件查找订单
	 * @param map
	 * @return
	 */
	public List<Order> findOrder(Map<String,Object> map);
	
	/**
	 * 修改订单状态
	 * @param map
	 */
	public void updateOrderStatus(Map<String,Object> map);
	
	/**
	 * 根据条件获取订单数量
	 * @param map
	 * @return
	 */
	public Long getOrderCount(Map<String,Object> map);
	
	/**
	 * 根据id查找订单
	 * @param id
	 * @return
	 */
	public Order getOrderById(Integer id);
}
