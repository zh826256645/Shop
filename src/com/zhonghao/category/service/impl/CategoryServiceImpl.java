package com.zhonghao.category.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.support.DaoSupport;

import com.zhonghao.category.dao.CategoryDao;
import com.zhonghao.category.domain.Category;
import com.zhonghao.order.domain.Order;

public class CategoryServiceImpl implements com.zhonghao.category.service.CategoryService {

	// ע�� DAO ���
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	// ��������һ������
	@Transactional
	public List<Category> findAll() {
		return getCategoryDao().findAll(Category.class);
	}

	// ���һ�����෽��
	@Transactional
	public void addCategory(String cname) {
		Category category = new Category();
		category.setCname(cname);
		categoryDao.save(category);
	}

	// ɾ��һ������
	@Transactional
	public void deleteCategory(Integer cid) {
		Category category = categoryDao.load(Category.class, cid);
		categoryDao.delete(category);
	}

	// ͨ�� Oid ����һ������
	@Transactional
	public Category findByCid(Integer cid) {
		return categoryDao.get(Category.class,cid);
	}

	// ���� һ������
	@Transactional
	public void updateCategory(Integer cid, String cname) {
		Category category = categoryDao.load(Category.class, cid);
		category.setCname(cname);
		categoryDao.update(category);
	}

}
