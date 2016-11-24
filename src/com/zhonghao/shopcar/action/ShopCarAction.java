package com.zhonghao.shopcar.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;
import com.zhonghao.shopcar.domain.ShopCar; 


public class ShopCarAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private Integer pid;

	private int number;

	private ProductService productService;


	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	// 添加到购物车
	public String addShopCarItem() throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession();
		ShopCar shopCar = (ShopCar) session.get("shopCar");		
		Product p = productService.findById(pid);
		if(shopCar != null) {
			shopCar.addShopCarItem(p, number);
			session.put("shopCar", shopCar);
		} else {
			ShopCar shopCar_ = new ShopCar();
			shopCar_.addShopCarItem(p, number);
			session.put("shopCar", shopCar_);
		}
		return SUCCESS;
	}

	// 清空购物车
	public String clearShopCar() {
		Map<String,Object> session = ActionContext.getContext().getSession();
		ShopCar shopCar = (ShopCar) session.get("shopCar");
		if(shopCar != null) {
			shopCar.clearShopCar();
			session.put("shopCar", shopCar);
		}
		return SUCCESS;
	}

	// 删除商品
	public String removeShopCarItem() {
		Map<String,Object> session = ActionContext.getContext().getSession();
		ShopCar shopCar = (ShopCar) session.get("shopCar");
		if(shopCar != null) {
			shopCar.removItem(pid);
		}
		return SUCCESS;
	}
}
