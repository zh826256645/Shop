package com.zhonghao.adminuser.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhonghao.adminuser.domain.AdminUser;
import com.zhonghao.adminuser.service.AdminUserService;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser = new AdminUser();
	
	// ע�� Service ���
	private AdminUserService adminUserService;

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public AdminUser getModel() {
		return adminUser;
	}
	
	public String page() {
		return "index";
	}
	
	// �����û���¼
	public String login() {
		AdminUser user = adminUserService.findByUsername(adminUser.getUsername());
		if(user != null && user.getPassword().equals(adminUser.getPassword())) {
			ActionContext.getContext().getSession().put("adminUser", user);
			return SUCCESS;
		} else {
			this.addActionMessage("�û������ڻ�������������������룡��");
			return INPUT;
		}
	}
	
}
