package com.zhonghao.category.adminaction;

import java.util.List;

import javax.swing.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.category.domain.Category;
import com.zhonghao.category.service.CategoryService;
/**
 * �����̨һ������� Action
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

	// ��ѯ����һ������
	public String findAllCategory() {
		return "all";
	}
	
	// ��ת�����ҳ��
	public String toAdd() {
		return "add";
	}
	
	// ���һ������
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
	
	// ɾ��һ������
	public String deleteCategory() {
		categoryService.deleteCategory(cid);
		List<Category> categories = categoryService.findAll();
		ActionContext.getContext().getSession().put("categories", categories);
		return "succToDelete";
	}
	
	// ��ת���༭ҳ��
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
