package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductBigType;

public interface ProductBigTypeDao {

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

	/**
	 * ��ʼ����Ʒ���༰����������ѯ��Ʒ����
	 * @param map
	 * @return
	 */
	public List<ProductBigType> findProductBigTypeList(Map<String,Object> map);

	/**
	 * �������������Ʒ�������
	 * @param map
	 * @return
	 */
	public Long getProductBigTypeCount(Map<String,Object> map);
	
	/**
	 * ������Ʒ����
	 * @param productBigType
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * �޸���Ʒ����
	 * @param productBigType
	 */
	public void updateProductBigType(ProductBigType productBigType);

	/**
	 * ɾ����Ʒ����
	 * @param id
	 */
	public void delete(Integer id);

	
	/**
	 * ͨ��id��ȡ��Ʒ����
	 * @param id
	 * @return
	 */
	public ProductBigType getProductBigTypeById(Integer id);
}
