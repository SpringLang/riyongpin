package com.zpark.dao;

import java.util.List;

import com.zpark.entity.OrderProduct;

public interface OrderProductDao {
	
	/**
	 * ���ݶ����Ų�ѯ������Ʒ
	 * @param orderId
	 * @return
	 */
	public List<OrderProduct> getOrderProductByOrderId(Integer orderId);
}
