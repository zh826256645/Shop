package com.zhonghao.product.service;

import java.util.List;

import com.zhonghao.product.domain.Product;
import com.zhonghao.utils.PageBean;

public interface ProductService {

	public List<Product> findHotProduct();
	
	public List<Product> findNewProduct();
	
	public Product findById(Integer pid);
	
	public PageBean<Product> findByCid(int cid,int limit,int page);
	
	public PageBean<Product> findByCsid(int csid,int limit,int page);
}
