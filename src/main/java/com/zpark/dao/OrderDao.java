package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.Order;
import com.zpark.entity.OrderProduct;

public interface OrderDao {
	
	/**
	 * ���涩��
	 * @param notice
	 */
	public void saveOrder(Order order);
	
	/**
	 * ������Ʒ���浽������Ʒ������
	 * @param list
	 */
	public void saveOrderProduct(List<OrderProduct> list);
	
	/**
	 * ��ʼ�������б������������Ҷ���
	 * @param map
	 * @return
	 */
	public List<Order> findOrder(Map<String,Object> map);
	
	/**
	 * �޸Ķ���״̬
	 * @param map
	 */
	public void updateOrderStatus(Map<String,Object> map);
	
	/**
	 * ����������ȡ��������
	 * @param map
	 * @return
	 */
	public Long getOrderCount(Map<String,Object> map);
	
	/**
	 * ����id���Ҷ���
	 * @param id
	 * @return
	 */
	public Order getOrderById(Integer id);
}
