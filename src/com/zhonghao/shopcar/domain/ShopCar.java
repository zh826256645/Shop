package com.zhonghao.shopcar.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhonghao.product.domain.Product;

public class ShopCar {

	// 购物车中的项目
	private Map<Integer,ShopCarItem> map = new LinkedHashMap<Integer,ShopCarItem>();
	
	private Collection<ShopCarItem> collection;

	// 总计
	private double total = 0;
	
	// 添加项目
	public void addShopCarItem(Product product, int number) {
		// 判断该项目是否存在
		Integer pid = product.getPid();
		if(map.containsKey(pid)) {
			ShopCarItem item = map.get(pid);
			item.setNumber(item.getNumber() + number);
			map.put(pid, item);
			// 更新总计
			total += item.getSubTotal();
		} else {
			ShopCarItem item = new ShopCarItem();
			item.setProduct(product);
			item.setNumber(number);
			map.put(pid, item);
			// 更新总计
			total += item.getSubTotal();
		}
	}
	
	// 删除项目
	public void removItem(int pid) {
		ShopCarItem item = map.remove(pid);
		// 更新总计
		total -= item.getSubTotal();
	}
	
	//　清空购物车
	public void clearShopCar() {
		map.clear();
		// 清空总计
		total = 0;
	}
	
	// 获得项目集合
	public Collection<ShopCarItem> getCollection() {
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}
}
