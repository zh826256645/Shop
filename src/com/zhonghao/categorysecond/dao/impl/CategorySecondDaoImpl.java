package com.zhonghao.categorysecond.dao.impl;

import java.util.List;

import com.zhonghao.categorysecond.dao.CategorySecondDao;
import com.zhonghao.categorysecond.domain.CategorySecond;
import com.zhonghao.product.domain.Product;
import com.zhonghao.utils.BaseDaoHibernate4;
import com.zhonghao.utils.PageBean;

/**
 * Dao 组件
 * @author ZhongHao
 *
 */
public class CategorySecondDaoImpl extends BaseDaoHibernate4<CategorySecond> implements CategorySecondDao {

	// 获取二级标签的分页 Bean
	public PageBean<CategorySecond> getCategorySecondPageBean(int limit,int page) {
		return getPageBean("from CategorySecond", limit, page);
	}
	
	protected PageBean<CategorySecond> getPageBean(String hql ,int limit,int page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int totalCount = find(hql).size();
		pageBean.setTotalCount(totalCount);
		int totalPage;
		if(totalCount % limit > 0) {
			totalPage = totalCount / limit + 1;
		} else {
			totalPage = totalCount / limit;
		}
		pageBean.setTotalPage(totalPage);

		List<CategorySecond> list = findByPage(hql, page, limit);
		pageBean.setList(list);
		return pageBean;
	}


}
