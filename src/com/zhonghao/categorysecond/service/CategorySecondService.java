package com.zhonghao.categorysecond.service;

import com.zhonghao.categorysecond.domain.CategorySecond;
import com.zhonghao.utils.PageBean;

public interface CategorySecondService {
	public PageBean<CategorySecond> getPageBean(int page);

	public void addCategorySecond(String csname, int cid);

	public CategorySecond findByCsid(int csid);

	public void update(String csname, int csid);

	public void delete(int csid);
}
