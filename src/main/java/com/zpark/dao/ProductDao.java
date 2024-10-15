package com.zpark.dao;

import com.zpark.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
	
	/**
	 * ��ʼ����Ʒ�������������ѯ��Ʒ
	 * @param map
	 * @return
	 */
	public List<Product> findProductList(Map<String,Object> map);
	
	/**
	 * ����������ȡ��Ʒ����
	 * @param map
	 * @return
	 */
	public Long getProductCount(Map<String,Object> map);
	
	/**
	 * ����id��ѯ��Ʒ
	 * @param productId
	 * @return
	 */
	public Product getProductById(Integer productId);
	
	/**
	 * ������Ʒ
	 * @param product
	 */
	public void saveProduct(Product product);
	
	/**
	 * �޸���Ʒ
	 * @param product
	 */
	public void updateProduct(Product product);
	
	/**
	 * ɾ����Ʒ
	 * @param id
	 */
	public void deleteProduct(Integer id);
	
	/**
	 * ����Ʒ���ó�����
	 * @param productId
	 */
	public void setProductWithHot(Integer productId);
	
	/**
	 * ����Ʒ���ó��ؼ�
	 * @param productId
	 */
	public void setProductWithSpecialPrice(Integer productId);
	
	/**
	 * ��ѯС������û����Ʒ
	 * @param smallTypeId
	 * @return
	 */
	public int existProductWithSmallTypeId(int smallTypeId);

	public void delProductWithHot(Integer productId);

	public void delProductWithSpecialPrice(Integer productId);
}
