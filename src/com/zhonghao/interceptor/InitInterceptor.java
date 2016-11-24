package com.zhonghao.interceptor;

import java.util.List;

import javax.swing.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zhonghao.category.domain.Category;
import com.zhonghao.category.service.CategoryService;
import com.zhonghao.product.domain.Product;
import com.zhonghao.product.service.ProductService;

/**
 * 在用户首次访问网站时，生成一级标签
 * @author lenovo
 *
 */
public class InitInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	// 注入 Service 组件
	private CategoryService categoryService;
	
	private ProductService productService;
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		@SuppressWarnings("unchecked")
		List<Category> list = (List<Category>) ctx.getSession().get("categories");
		if(list == null || list.size() == 0) {
			List<Category> categories = getCategoryService().findAll();
			List<Product> hotProducts = getProductService().findHotProduct();
			List<Product> newProducts = getProductService().findNewProduct();
			ctx.getSession().put("categories", categories);
			ctx.getSession().put("hotProducts", hotProducts);
			ctx.getSession().put("newProducts", newProducts);
			invocation.invoke();
		}
		invocation.invoke();
		return "success";
	}
}
