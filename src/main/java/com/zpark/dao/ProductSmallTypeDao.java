package com.zpark.dao;

import java.util.List;
import java.util.Map;

import com.zpark.entity.ProductSmallType;

public interface ProductSmallTypeDao {

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

	/**
	 * ��ѯ��Ʒ��������û����ƷС��
	 * @param bigTypeId
	 * @return
	 */
	public Long existSmallTypeWithBigTypeId(Integer bigTypeId);
	
	/**
	 * ͨ��������ѯ��ƷС������
	 * @param map
	 * @return
	 */
	public Long getProductSmallTypeCount(Map<String,Object> map);
	
	/**
	 * ������ƷС����Ϣ
	 * @param productSmallType
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * �޸���ƷС����Ϣ
	 * @param productSmallType
	 */
	public void updateProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * ɾ����ƷС����Ϣ
	 * @param id
	 */
	public void delete(Integer id);
}
