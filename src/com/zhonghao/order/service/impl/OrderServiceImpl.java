package com.zhonghao.order.service.impl;

import javax.transaction.Transactional;

import com.zhonghao.order.dao.OrderDao;
import com.zhonghao.order.domain.Order;
import com.zhonghao.order.domain.OrderItem;
import com.zhonghao.order.service.OrderService;
import com.zhonghao.utils.PageBean;

/**
 * 订单 Service
 * @author lenovo
 *
 */
@SuppressWarnings("unchecked")
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单
	@Transactional
	public void saveOrder(Order order) {
		getOrderDao().save(order);
	}

	// 查找用户订单
	@Transactional
	public PageBean<Order> findByUid(Integer uid,int limit,int page) {
		PageBean<Order> pageBean = orderDao.findByUid(uid,limit,page);
		return pageBean;
	}

	// 删除订单
	@Transactional
	public boolean removeOrder(Integer oid,Integer uid) {
		Order order = (Order) getOrderDao().load(Order.class, oid);
		if(order.getUser().getUid() == uid) {
			getOrderDao().delete(order);
			return true;
		}
		return false;
	}

	// 删除订单项
	@Transactional
	public boolean removeOrderItem(Integer itemid,Integer uid) {
		OrderItem item = (OrderItem) getOrderDao().load(OrderItem.class,itemid);
		Order order = item.getOrder();
		if(order.getUser().getUid() == uid) {
			order.setTotal(order.getTotal() - item.getSubtotal());
			getOrderDao().delete(item);
			return true;
		}
		return false;
	}

	// 根据订单 Id 查询订单
	@Transactional
	public Order findByOid(Integer oid, Integer uid) {
		Order order = (Order) getOrderDao().get(Order.class, oid);
		if(order.getUser().getUid() == uid) {
			return order;
		}
		return null;
	}

	// 更新订单
	@Transactional
	public void update(Order order) {
		getOrderDao().update(order);
	}
}
