package com.zhonghao.order.action;

import java.sql.Date;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.order.domain.Order;
import com.zhonghao.order.domain.OrderItem;
import com.zhonghao.order.service.OrderService;
import com.zhonghao.shopcar.domain.ShopCar;
import com.zhonghao.shopcar.domain.ShopCarItem;
import com.zhonghao.user.domain.User;
import com.zhonghao.utils.PageBean;

/**
 * ���� Action
 * @author ZhongHao
 *
 */
public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private OrderService orderService;

	private int page;

	private Integer oid;
	
	private Integer itemid;
	
	private String phone;
	
	private String addr;
	
	private String name;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ��Ӷ���
	public String createOrder() {
		Order order = new Order();
		order.setOrdertime(new Date(System.currentTimeMillis()));
		order.setState(0);
		User user = (User) ActionContext.getContext().getSession().get("user");
		order.setUser(user);
		order.setPhone(user.getPhone());
		order.setName(user.getName());
		order.setAddr(user.getAddr());
		ShopCar shopCar = (ShopCar) ActionContext.getContext().getSession().get("shopCar");
		for (ShopCarItem sItem : shopCar.getCollection()) {
			OrderItem oItem = new OrderItem();
			oItem.setProduct(sItem.getProduct());
			oItem.setSubtotal(sItem.getSubTotal());
			oItem.setCount(sItem.getNumber());
			oItem.setOrder(order);

			order.getOrderItems().add(oItem);
		}
		order.setTotal(shopCar.getTotal());
		getOrderService().saveOrder(order);
		ActionContext.getContext().getValueStack().set("order", order);
		return SUCCESS;
	}

	// �����û������ж���
	public String findByUid() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		Integer uid = user.getUid();
		PageBean<Order> pageBean = getOrderService().findByUid(uid,3,page);
		ActionContext.getContext().getValueStack().set("orderBean", pageBean);
		return SUCCESS;

	}

	// ���� ID ɾ������
	public String removeOrder() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(getOrderService().removeOrder(oid,user.getUid())) {
			return SUCCESS;
		} else {
			this.addActionError("ɾ��ʧ��");
			return SUCCESS;
		}
	}
	
	// ɾ�������еĶ�����
	public String removeOrderItem() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(getOrderService().removeOrderItem(itemid,user.getUid())) {
			return SUCCESS;
		} else {
			this.addActionError("ɾ��ʧ��");
			return SUCCESS;
		}
	}
	
	// ���ݶ��� ID ��ѯ����
	public String findByOid() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		Order order = getOrderService().findByOid(oid,user.getUid());
		if(order != null) {
			ActionContext.getContext().getValueStack().set("order", order);
			return SUCCESS;
		} else {
			this.addActionError("��ѯʧ��");
			return ERROR;
		}
	}
	
	// ����(��δ��������)
	public String paymentForOid() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		Order order = getOrderService().findByOid(oid, user.getUid());
		if(order != null) {
			order.setAddr(addr);
			order.setName(name);
			order.setPhone(phone);
			order.setState(1);
			getOrderService().update(order);
			this.addActionMessage("��ϲ����ɹ�����ȴ�������");
			return SUCCESS;
		}		
		this.addActionMessage("����ʧ�ܣ�����ϵ�̼ң���");
		return ERROR;
	}
}
