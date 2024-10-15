package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductSmallType;

public interface ProductSmallTypeService {
	
	/**
	 * ͨ����Ʒ����id������ƷС�࣬������Ʒ��������ƷС��˫��һ�Զ����
	 * @param id
	 * @return
	 */
	public ProductSmallType findSmallTypeByBigTypeId(Integer id);
	
	/**
	 * ��������������ƷС��list
	 * @param map
	 * @return
	 */
	public List<ProductSmallType> findSmallTypeList(Map<String,Object> map);
	
	/**
	 * ͨ����ƷС��id������ƷС�࣬������Ʒ����ƷС��˫��һ�Զ����
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
