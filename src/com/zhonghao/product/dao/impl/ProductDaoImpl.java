package com.zhonghao.product.dao.impl;

import java.util.List;

import com.zhonghao.product.dao.ProductDao;
import com.zhonghao.product.domain.Product;
import com.zhonghao.utils.BaseDaoHibernate4;
import com.zhonghao.utils.PageBean;

public class ProductDaoImpl extends BaseDaoHibernate4<Product> implements ProductDao {

	// ����������Ʒ
	public List<Product> findHotProduct() {
		List<Product> list = findByPage("from Product p where p.is_hot = ? order by p.pdate desc", 1, 10, 1);
		return list;
	}

	// ��������Ʒ
	public List<Product> findNewProduct() {
		List<Product> list = findByPage("from Product p order by p.pdate desc", 1, 10);
		return list;
	}

	// ͨ��һ����ǩ������Ʒ
	public PageBean<Product> findByCid(int cid,int limit,int page) {

		PageBean<Product> pageBean = getPageBean("Select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?", cid, limit, page);
		return pageBean;
	}

	// ͨ��������ǩ������Ʒ
	public PageBean<Product> findByCsid(int csid,int limit,int page) {
		PageBean<Product> pageBean = getPageBean("Select p from Product p join p.categorySecond cs where cs.csid = ?", csid, limit, page);
		return pageBean;
	}

	protected PageBean<Product> getPageBean(String hql , int id ,int limit,int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int totalCount = find(hql,id).size();
		pageBean.setTotalCount(totalCount);
		int totalPage;
		if(totalCount % limit > 0) {
			totalPage = totalCount / limit + 1;
		} else {
			totalPage = totalCount / limit;
		}
		pageBean.setTotalPage(totalPage);

		List<Product> list = findByPage(hql, page, limit, id);
		pageBean.setList(list);
		return pageBean;
	}
}

