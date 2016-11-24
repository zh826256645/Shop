package com.zhonghao.category.service;

import java.util.List;

import com.zhonghao.category.domain.Category;

public interface CategoryService {

	public List<Category> findAll();

	public void addCategory(String cname);

	public void deleteCategory(Integer cid);

	public Category findByCid(Integer cid);

	public void updateCategory(Integer cid, String cname);
}
