package com.zhonghao.product.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.zhonghao.product.dao.ProductDao;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;
import com.zhonghao.utils.PageBean;

public class ProductServiceImpl implements ProductService {

	// ע�� Dao ���
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// ��ȡ������Ʒ
	@Transactional
	public List<Product> findHotProduct() {
		List<Product> list = getProductDao().findHotProduct();
		return list;
	}

	// ��ȡ������Ʒ
	@Transactional
	public List<Product> findNewProduct() {
		List<Product> list = getProductDao().findNewProduct();
		return list;
	}

	// ͨ����Ʒ ID ��ȡ��Ʒ
	@Transactional
	public Product findById(Integer pid) {
		Product product = getProductDao().get(Product.class, pid);
		return product;
	}

	// ͨ��һ����ǩ��ȡ��Ʒ
	@Transactional
	public PageBean<Product> findByCid(int cid, int limit,int page) {
		PageBean<Product> pageBean = getProductDao().findByCid(cid, limit, page);
		return pageBean;
	}

	// ͨ��������ǩ��ȡ��Ʒ
	@Transactional
	public PageBean<Product> findByCsid(int csid,int limit,int page) {
		PageBean<Product> pageBean = getProductDao().findByCsid(csid, limit, page);
		return pageBean;
	}

}
