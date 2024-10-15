package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.ProductBigTypeDao;
import com.zpark.entity.ProductBigType;
import com.zpark.service.ProductBigTypeService;

@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService{

	@Resource
	private ProductBigTypeDao productBigTypeDao;
	
	/**
	 * �������е���Ʒ���࣬��ʼ���õ�
	 */
	public List<ProductBigType> findAllBigTypeList() {
		return productBigTypeDao.findAllBigTypeList();
	}

	/**
	 * ͨ����Ʒ����id������Ʒ���࣬������Ʒ��������ƷС��˫��һ�Զ����
	 * @param BigTypeId
	 * @return
	 */
	public ProductBigType findProductBigTypeByBigTypeId(Integer BigTypeId){
		return productBigTypeDao.findProductBigTypeByBigTypeId(BigTypeId);
	}
	
	public List<ProductBigType> findProductBigTypeList(Map<String,Object> map){
		return productBigTypeDao.findProductBigTypeList(map);
	}

	
	public Long getProductBigTypeCount(Map<String,Object> map){
		return productBigTypeDao.getProductBigTypeCount(map);
	}
	
	public void saveProductBigType(ProductBigType productBigType){
		productBigTypeDao.saveProductBigType(productBigType);
	}

	public void updateProductBigType(ProductBigType productBigType){
		productBigTypeDao.updateProductBigType(productBigType);
	}
	
	public void delete(Integer id){
		productBigTypeDao.delete(id);
	}

	public ProductBigType getProductBigTypeById(Integer id){
		return productBigTypeDao.getProductBigTypeById(id);
	}
	
}
