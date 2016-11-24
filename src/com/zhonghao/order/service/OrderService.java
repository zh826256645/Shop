package com.zhonghao.order.service;

import com.zhonghao.order.domain.Order;
import com.zhonghao.utils.PageBean;

public interface OrderService {

	public void saveOrder(Order order);
	
	public PageBean<Order> findByUid(Integer uid,int limit,int page);
	
	public boolean removeOrder(Integer oid,Integer uid);
	
	public boolean removeOrderItem(Integer itemid,Integer uid);

	public Order findByOid(Integer oid, Integer uid);

	public void update(Order order);
}
