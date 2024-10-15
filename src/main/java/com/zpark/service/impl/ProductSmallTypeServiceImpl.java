package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.ProductSmallTypeDao;
import com.zpark.entity.ProductSmallType;
import com.zpark.service.ProductSmallTypeService;

@Service("productSmallTypeService")
public class ProductSmallTypeServiceImpl implements ProductSmallTypeService{

	@Resource
	private ProductSmallTypeDao productSmallTypeDao;
	
	/**
	 * 通过商品大类id查找商品小类，用于商品大类与商品小类双向一对多关联
	 */
	public ProductSmallType findSmallTypeByBigTypeId(Integer id){
		return productSmallTypeDao.findSmallTypeByBigTypeId(id);
	}
	
	/**
	 * 根据条件查找商品小类list
	 */
	public List<ProductSmallType> findSmallTypeList(Map<String, Object> map) {
		return productSmallTypeDao.findSmallTypeList(map);
	}

	public ProductSmallType findProductSmallTypeBySmallTypeId(Integer smallTypeId) {
		return productSmallTypeDao.findProductSmallTypeBySmallTypeId(smallTypeId);
	}
	
	public List<ProductSmallType> findProductSmallTypeList(Map<String,Object> map) {
		return productSmallTypeDao.findSmallTypeList(map);
	}

	public boolean existSmallTypeWithBigTypeId(Integer bigTypeId) {
		Long total = productSmallTypeDao.existSmallTypeWithBigTypeId(bigTypeId);
		if(total>0){
			return true;
		}else{
			return false;
		}
	}

	public Long getProductSmallTypeCount(Map<String,Object> map){
		return productSmallTypeDao.getProductSmallTypeCount(map);
	}


	public ProductSmallType getProductSmallTypeById(int id) {
		return productSmallTypeDao.findProductSmallTypeBySmallTypeId(id);
	}


	public void saveProductSmallType(ProductSmallType productSmallType){
		productSmallTypeDao.saveProductSmallType(productSmallType);
	}
	
	public void updateProductSmallType(ProductSmallType productSmallType){
		productSmallTypeDao.updateProductSmallType(productSmallType);
	}
	
	public void delete(Integer id){
		productSmallTypeDao.delete(id);
	}


}
