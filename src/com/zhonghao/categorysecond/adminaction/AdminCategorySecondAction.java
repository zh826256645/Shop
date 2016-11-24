package com.zhonghao.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.category.domain.Category;
import com.zhonghao.category.service.CategoryService;
import com.zhonghao.categorysecond.domain.CategorySecond;
import com.zhonghao.categorysecond.service.CategorySecondService;
import com.zhonghao.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport{

	private int page;
	
	private int csid;
	
	private String csname;
	
	private int cid;
	
	private CategorySecondService categorySecondService;
	
	private CategoryService categoryService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setCsid(int csid) {
		this.csid = csid;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	// ��ѯ���ж�������
	public String findAllCategorySecond() {
		PageBean<CategorySecond> pageBean= categorySecondService.getPageBean(page);
		ActionContext.getContext().getValueStack().set("csPageBean", pageBean);
		return "all";
	}
	
	// ������Ӷ�������ҳ��
	public String toAddPage() {
		return "add";
	}
	
	// ��Ӷ�������
	public String addCategorySecond() {
		categorySecondService.addCategorySecond(csname,cid);
		this.upData();
		return "succToAdd";
	}
	
	// ���� csid �������ҳ��
	public String toUpdatePage() {
		ActionContext.getContext().getValueStack().set("categorySecond", categorySecondService.findByCsid(csid));
		return "update";
	}
	
	// ���¶�������
	public String updateCategorySecond() {
		categorySecondService.update(csname,csid);
		this.upData();
		return "succToUpdate";
	}
	
	public String deleteCategorySecond() {
		categorySecondService.delete(csid);
		this.upData();
		return "succToDelete";
	}
	
	protected void upData() {
		List<Category> list = categoryService.findAll();
		ActionContext.getContext().getSession().put("categories", list);
	}
}
