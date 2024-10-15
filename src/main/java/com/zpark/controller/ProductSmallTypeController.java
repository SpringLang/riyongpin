package com.zpark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpark.entity.PageBean;
import com.zpark.entity.ProductBigType;
import com.zpark.entity.ProductSmallType;
import com.zpark.service.ProductService;
import com.zpark.service.ProductSmallTypeService;
import com.zpark.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/productSmallType")
public class ProductSmallTypeController {
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	@Resource
	private ProductService productService;

	@RequestMapping("/comboList")
	public String comboList(String bigTypeId,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bigTypeId", bigTypeId);
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findSmallTypeList(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigType","productList"});
		JSONArray rows=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(response, jsonArray);
		return null;
	}
	
	@RequestMapping("/saveorupdate")
	public String save(ProductBigType bigType,Integer bigTypeId,ProductSmallType productSmallType,HttpServletResponse response)throws Exception{
		
		bigType.setId(bigTypeId);
		productSmallType.setBigType(bigType);
		
		if(productSmallType.getId()==0){
			productSmallTypeService.saveProductSmallType(productSmallType);
		}else{
			productSmallTypeService.updateProductSmallType(productSmallType);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	@RequestMapping("/list")
	public String list(String bigTypeId,String smallTypeName,String page,String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("bigTypeId", bigTypeId);
		map.put("smallTypeName", smallTypeName);
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findSmallTypeList(map);
		long total=productSmallTypeService.getProductSmallTypeCount(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList"});
		jsonConfig.registerJsonValueProcessor(ProductBigType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		JSONArray rowss=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowss);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(productService.existProductWithSmallTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "商品小类包含商品");
			}else{
				productSmallTypeService.delete(Integer.parseInt(idsStr[i]));				
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
