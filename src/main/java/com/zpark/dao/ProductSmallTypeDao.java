package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductSmallType;

public interface ProductSmallTypeDao {

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

	/**
	 * 查询商品大类下有没有商品小类
	 * @param bigTypeId
	 * @return
	 */
	public Long existSmallTypeWithBigTypeId(Integer bigTypeId);
	
	/**
	 * 通过条件查询茶品小类数量
	 * @param map
	 * @return
	 */
	public Long getProductSmallTypeCount(Map<String,Object> map);
	
	/**
	 * 保存商品小类信息
	 * @param productSmallType
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 修改商品小类信息
	 * @param productSmallType
	 */
	public void updateProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 删除商品小类信息
	 * @param id
	 */
	public void delete(Integer id);
}
