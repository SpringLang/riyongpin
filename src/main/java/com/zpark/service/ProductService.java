package com.zpark.service;

import com.zpark.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public List<Product> findProductList(Map<String,Object> map);
	
	public Long getProductCount(Map<String,Object> map);
	
	public Product getProductById(Integer productId);
	
	public void saveProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Integer id);
	
	public void setProductWithHot(Integer productId);
	
	public void setProductWithSpecialPrice(Integer productId);
	
	public boolean existProductWithSmallTypeId(int smallTypeId);

	public void delProductWithHot(Integer parseInt);

	public void delProductWithSpecialPrice(Integer parseInt);
}
