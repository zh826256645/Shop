package com.zhonghao.category.adminaction;

import java.util.List;

import javax.swing.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.category.domain.Category;
import com.zhonghao.category.service.CategoryService;
/**
 * 管理后台一级分类的 Action
 * @author ZhongHao
 *
 */
public class AdminCategoryAction extends ActionSupport{
	
	private String cname;
	
	private Integer cid;
	
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	private CategoryService categoryService;

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 查询所有一级分类
	public String findAllCategory() {
		return "all";
	}
	
	// 跳转到添加页面
	public String toAdd() {
		return "add";
	}
	
	// 添加一级分类
	public String addCategory() {
		List<Category> list = (List<Category>) ActionContext.getContext().getSession().get("categories");
		for (Category category : list) {
			if(category.getCname() == cname) {
				return ERROR;
			}
		}
		categoryService.addCategory(cname);
		List<Category> categories = categoryService.findAll();
		ActionContext.getContext().getSession().put("categories", categories);
		return "succToAdd";
	}
	
	// 删除一级分类
	public String deleteCategory() {
		categoryService.deleteCategory(cid);
		List<Category> categories = categoryService.findAll();
		ActionContext.getContext().getSession().put("categories", categories);
		return "succToDelete";
	}
	
	// 跳转到编辑页面
	public String toEdit() {
		Category category = categoryService.findByCid(cid);
		ActionContext.getContext().getValueStack().set("category", category);
		return "edit";
	}
	
	public String updateCategory() {
		categoryService.updateCategory(cid,cname);
		ActionContext.getContext().getSession().put("categories", categoryService.findAll());
		return "succToUpdate";
	}
}
