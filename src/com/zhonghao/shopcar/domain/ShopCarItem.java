package com.zhonghao.shopcar.domain;

import com.zhonghao.product.domain.Product;

/**
 * 对应购物车中的一个项目
 * 每个项目对应一个商品
 * @author lenovo
 *
 */
public class ShopCarItem {

	// 对应的商品
	private Product product;
	
	// 商品数量
	private int number;
	
	// 小计
	private Double subTotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Double getSubTotal() {
		return subTotal =  product.getShop_price() * number;
	}

}
