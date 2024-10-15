package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductBigType;

public interface ProductBigTypeDao {

	/**
	 * 查找所有的商品大类，初始化用到
	 */
	public List<ProductBigType> findAllBigTypeList();
	
	/**
	 * 通过商品大类id查找商品大类，用于商品大类与商品小类双向一对多关联
	 * @param BigTypeId
	 * @return
	 */
	public ProductBigType findProductBigTypeByBigTypeId(Integer BigTypeId);

	/**
	 * 初始化商品大类及根据条件查询商品大类
	 * @param map
	 * @return
	 */
	public List<ProductBigType> findProductBigTypeList(Map<String,Object> map);

	/**
	 * 根据条件获得商品大类个数
	 * @param map
	 * @return
	 */
	public Long getProductBigTypeCount(Map<String,Object> map);
	
	/**
	 * 保存商品大类
	 * @param productBigType
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * 修改商品大类
	 * @param productBigType
	 */
	public void updateProductBigType(ProductBigType productBigType);

	/**
	 * 删除商品大类
	 * @param id
	 */
	public void delete(Integer id);

	
	/**
	 * 通过id获取商品大类
	 * @param id
	 * @return
	 */
	public ProductBigType getProductBigTypeById(Integer id);
}
