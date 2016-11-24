package com.zhonghao.shopcar.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhonghao.product.domain.Product;

public class ShopCar {

	// ���ﳵ�е���Ŀ
	private Map<Integer,ShopCarItem> map = new LinkedHashMap<Integer,ShopCarItem>();
	
	private Collection<ShopCarItem> collection;

	// �ܼ�
	private double total = 0;
	
	// �����Ŀ
	public void addShopCarItem(Product product, int number) {
		// �жϸ���Ŀ�Ƿ����
		Integer pid = product.getPid();
		if(map.containsKey(pid)) {
			ShopCarItem item = map.get(pid);
			item.setNumber(item.getNumber() + number);
			map.put(pid, item);
			// �����ܼ�
			total += item.getSubTotal();
		} else {
			ShopCarItem item = new ShopCarItem();
			item.setProduct(product);
			item.setNumber(number);
			map.put(pid, item);
			// �����ܼ�
			total += item.getSubTotal();
		}
	}
	
	// ɾ����Ŀ
	public void removItem(int pid) {
		ShopCarItem item = map.remove(pid);
		// �����ܼ�
		total -= item.getSubTotal();
	}
	
	//����չ��ﳵ
	public void clearShopCar() {
		map.clear();
		// ����ܼ�
		total = 0;
	}
	
	// �����Ŀ����
	public Collection<ShopCarItem> getCollection() {
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}
}
