package com.zhonghao.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;
/**
 * 用于访问 index.jsp
 * @author lenovo
 *
 */
public class IndexAction extends ActionSupport {

	// 注入商品模块的 Servic 组件
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
