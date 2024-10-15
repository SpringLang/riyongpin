package com.zpark.dao;

import java.util.List;

import com.zpark.entity.OrderProduct;

public interface OrderProductDao {
	
	/**
	 * 根据订单号查询订单商品
	 * @param orderId
	 * @return
	 */
	public List<OrderProduct> getOrderProductByOrderId(Integer orderId);
}
