package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductBigType;

public interface ProductBigTypeService {

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
	
	public List<ProductBigType> findProductBigTypeList(Map<String,Object> map);
	
	public Long getProductBigTypeCount(Map<String,Object> map);
	
	public void saveProductBigType(ProductBigType productBigType);

	public void updateProductBigType(ProductBigType productBigType);
	
	public void delete(Integer id);

	public ProductBigType getProductBigTypeById(Integer id);
}
