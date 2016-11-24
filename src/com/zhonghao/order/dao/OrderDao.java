package com.zhonghao.order.dao;

import com.zhonghao.order.domain.Order;
import com.zhonghao.utils.BaseDao;
import com.zhonghao.utils.PageBean;

public interface OrderDao<T> extends BaseDao<T> {

	public PageBean<Order> findByUid(Integer uid,int limit,int page);
	
}
