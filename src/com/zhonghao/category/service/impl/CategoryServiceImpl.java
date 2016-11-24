package com.zhonghao.category.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.support.DaoSupport;

import com.zhonghao.category.dao.CategoryDao;
import com.zhonghao.category.domain.Category;
import com.zhonghao.order.domain.Order;

public class CategoryServiceImpl implements com.zhonghao.category.service.CategoryService {

	// 注入 DAO 组件
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	// 查找所有一级分类
	@Transactional
	public List<Category> findAll() {
		return getCategoryDao().findAll(Category.class);
	}

	// 添加一级分类方法
	@Transactional
	public void addCategory(String cname) {
		Category category = new Category();
		category.setCname(cname);
		categoryDao.save(category);
	}

	// 删除一级分类
	@Transactional
	public void deleteCategory(Integer cid) {
		Category category = categoryDao.load(Category.class, cid);
		categoryDao.delete(category);
	}

	// 通过 Oid 查找一级分类
	@Transactional
	public Category findByCid(Integer cid) {
		return categoryDao.get(Category.class,cid);
	}

	// 更新 一级分类
	@Transactional
	public void updateCategory(Integer cid, String cname) {
		Category category = categoryDao.load(Category.class, cid);
		category.setCname(cname);
		categoryDao.update(category);
	}

}
