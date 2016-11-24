package com.zhonghao.product.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.zhonghao.product.dao.ProductDao;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;
import com.zhonghao.utils.PageBean;

public class ProductServiceImpl implements ProductService {

	// 注入 Dao 组件
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 获取热门商品
	@Transactional
	public List<Product> findHotProduct() {
		List<Product> list = getProductDao().findHotProduct();
		return list;
	}

	// 获取最新商品
	@Transactional
	public List<Product> findNewProduct() {
		List<Product> list = getProductDao().findNewProduct();
		return list;
	}

	// 通过商品 ID 获取商品
	@Transactional
	public Product findById(Integer pid) {
		Product product = getProductDao().get(Product.class, pid);
		return product;
	}

	// 通过一级标签获取商品
	@Transactional
	public PageBean<Product> findByCid(int cid, int limit,int page) {
		PageBean<Product> pageBean = getProductDao().findByCid(cid, limit, page);
		return pageBean;
	}

	// 通过二级标签获取商品
	@Transactional
	public PageBean<Product> findByCsid(int csid,int limit,int page) {
		PageBean<Product> pageBean = getProductDao().findByCsid(csid, limit, page);
		return pageBean;
	}

}
