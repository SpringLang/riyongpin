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
	 * 查找所有的商品大类，初始化用到
	 */
	public List<ProductBigType> findAllBigTypeList() {
		return productBigTypeDao.findAllBigTypeList();
	}

	/**
	 * 通过商品大类id查找商品大类，用于商品大类与商品小类双向一对多关联
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
