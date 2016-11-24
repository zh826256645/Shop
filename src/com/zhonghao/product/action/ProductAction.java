package com.zhonghao.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;
import com.zhonghao.utils.PageBean;

public class ProductAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	private ProductService productService;
	
	private Integer pid;
	
	private Integer cid;
	
	private Integer csid;
	
	private int page;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String execute() throws Exception {
		Product product = getProductService().findById(pid);
		ActionContext.getContext().getValueStack().set("product", product);
		return SUCCESS;
	}
	
	public String findByCid() {
		PageBean<Product> pageBean = getProductService().findByCid(cid, 12, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return SUCCESS;
	}
	
	public String findByCsid() {
		PageBean<Product> pageBean = getProductService().findByCsid(csid, 12, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return SUCCESS;
	}
}
