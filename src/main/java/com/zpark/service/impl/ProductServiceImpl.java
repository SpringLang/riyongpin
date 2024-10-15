package com.zpark.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpark.dao.ProductDao;
import com.zpark.entity.Product;
import com.zpark.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductDao productDao;
	
	public List<Product> findProductList(Map<String, Object> map) {
		return productDao.findProductList(map);
	}

	public Long getProductCount(Map<String,Object> map) {
		return productDao.getProductCount(map);
	}

	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	public void saveProduct(Product product){
		productDao.saveProduct(product);
	}
	
	public void updateProduct(Product product){
		productDao.updateProduct(product);
	}

	public void deleteProduct(Integer id) {
		productDao.deleteProduct(id);
	}

	public void setProductWithHot(Integer productId){
		productDao.setProductWithHot(productId);
	}

	public void delProductWithHot(Integer productId){
		productDao.delProductWithHot(productId);
	}
	
	public void setProductWithSpecialPrice(Integer productId){
		productDao.setProductWithSpecialPrice(productId);
	}
	public void delProductWithSpecialPrice(Integer productId){
		productDao.delProductWithSpecialPrice(productId);
	}

	public boolean existProductWithSmallTypeId(int smallTypeId) {
		int total = productDao.existProductWithSmallTypeId(smallTypeId);
		if(total>0){
			return true;
		}else{
			return false;
		}
	}

	

	

}
