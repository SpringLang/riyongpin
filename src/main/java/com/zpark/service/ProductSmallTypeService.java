package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductSmallType;

public interface ProductSmallTypeService {
	
	/**
	 * 通过商品大类id查找商品小类，用于商品大类与商品小类双向一对多关联
	 * @param id
	 * @return
	 */
	public ProductSmallType findSmallTypeByBigTypeId(Integer id);
	
	/**
	 * 根据条件查找商品小类list
	 * @param map
	 * @return
	 */
	public List<ProductSmallType> findSmallTypeList(Map<String,Object> map);
	
	/**
	 * 通过商品小类id查找商品小类，用于商品与商品小类双向一对多关联
	 * @param BigTypeId
	 * @return
	 */
	public ProductSmallType findProductSmallTypeBySmallTypeId(Integer smallTypeId);

	public boolean existSmallTypeWithBigTypeId(Integer bigTypeId);
	
	public Long getProductSmallTypeCount(Map<String,Object> map);
	
	public ProductSmallType getProductSmallTypeById(int id);
	
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	public void updateProductSmallType(ProductSmallType productSmallType);
	
	public void delete(Integer id);
}
