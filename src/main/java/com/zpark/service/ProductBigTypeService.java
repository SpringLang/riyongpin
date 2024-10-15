package com.zpark.service;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductBigType;

public interface ProductBigTypeService {

	/**
	 * �������е���Ʒ���࣬��ʼ���õ�
	 */
	public List<ProductBigType> findAllBigTypeList();
	
	/**
	 * ͨ����Ʒ����id������Ʒ���࣬������Ʒ��������ƷС��˫��һ�Զ����
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
