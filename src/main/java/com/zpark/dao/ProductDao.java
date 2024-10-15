package com.zpark.dao;

import com.zpark.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
	
	/**
	 * 初始化商品类表及根据条件查询商品
	 * @param map
	 * @return
	 */
	public List<Product> findProductList(Map<String,Object> map);
	
	/**
	 * 根据条件获取商品数量
	 * @param map
	 * @return
	 */
	public Long getProductCount(Map<String,Object> map);
	
	/**
	 * 根据id查询商品
	 * @param productId
	 * @return
	 */
	public Product getProductById(Integer productId);
	
	/**
	 * 保存商品
	 * @param product
	 */
	public void saveProduct(Product product);
	
	/**
	 * 修改商品
	 * @param product
	 */
	public void updateProduct(Product product);
	
	/**
	 * 删除商品
	 * @param id
	 */
	public void deleteProduct(Integer id);
	
	/**
	 * 把商品设置成热卖
	 * @param productId
	 */
	public void setProductWithHot(Integer productId);
	
	/**
	 * 把商品设置成特价
	 * @param productId
	 */
	public void setProductWithSpecialPrice(Integer productId);
	
	/**
	 * 查询小类下有没有商品
	 * @param smallTypeId
	 * @return
	 */
	public int existProductWithSmallTypeId(int smallTypeId);

	public void delProductWithHot(Integer productId);

	public void delProductWithSpecialPrice(Integer productId);
}
