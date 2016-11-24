package com.zhonghao.shopcar.domain;

import com.zhonghao.product.domain.Product;

/**
 * ��Ӧ���ﳵ�е�һ����Ŀ
 * ÿ����Ŀ��Ӧһ����Ʒ
 * @author lenovo
 *
 */
public class ShopCarItem {

	// ��Ӧ����Ʒ
	private Product product;
	
	// ��Ʒ����
	private int number;
	
	// С��
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
