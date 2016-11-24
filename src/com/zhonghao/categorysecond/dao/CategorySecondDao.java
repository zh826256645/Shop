package com.zhonghao.categorysecond.dao;

import com.zhonghao.categorysecond.domain.CategorySecond;
import com.zhonghao.utils.BaseDao;
import com.zhonghao.utils.PageBean;

public interface CategorySecondDao extends BaseDao<CategorySecond>{

	public PageBean<CategorySecond> getCategorySecondPageBean(int limit,int page);
}
