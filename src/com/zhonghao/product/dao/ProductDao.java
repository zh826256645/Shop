package com.zhonghao.product.dao;

import java.util.List;

import com.zhonghao.product.domain.Product;
import com.zhonghao.utils.BaseDao;
import com.zhonghao.utils.PageBean;

public interface ProductDao extends BaseDao<Product> {

	public List<Product> findHotProduct();
	
	public List<Product> findNewProduct();
	
	public PageBean<Product> findByCid(int cid,int limit,int page);
	
	public PageBean<Product> findByCsid(int csid,int limit,int page);
}
