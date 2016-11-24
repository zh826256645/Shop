package com.zhonghao.order.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.zhonghao.order.dao.OrderDao;
import com.zhonghao.order.domain.Order;
import com.zhonghao.order.domain.OrderItem;
import com.zhonghao.product.domain.Product;
import com.zhonghao.utils.BaseDaoHibernate4;
import com.zhonghao.utils.PageBean;

public class OrderDaoImpl<T> extends BaseDaoHibernate4<T> implements OrderDao<T> {

	// 通过用户查找订单
	public PageBean<Order> findByUid(Integer uid, int limit, int page) {
		PageBean<Order> pageBean = getPageBean("Select o from Order o join o.user u where u.uid = ?", uid, limit, page);
		return pageBean;
	}

	protected PageBean<Order> getPageBean(String hql , int id ,int limit,int page) {
		PageBean<Order> pageBean = new PageBean<Order>();
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

		@SuppressWarnings("unchecked")
		List<Order> list = (List<Order>) findByPage(hql, page, limit, id);
		pageBean.setList(list);
		return pageBean;
	}
}
