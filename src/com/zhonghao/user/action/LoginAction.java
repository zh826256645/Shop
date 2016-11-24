package com.zhonghao.user.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhonghao.user.domain.User;
import com.zhonghao.user.service.UserService;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;

	public User user = new User();
	
	// 注入 Service 组件
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 实现模型驱动
	public User getModel() {
		return user;
	}

	/**
	 * 用户登录
	 * @return
	 */
	public String login() {
		User user_1 = getUserService().findByUsername(user.getUsername());
		if(user_1 == null) {
			addFieldError("user.username", this.getText("username.undefined"));
			return INPUT;
		}
		if(user_1.getPassword().equals(user.getPassword()) && user_1.getState() == 1) {
			ActionContext.getContext().getSession().put("user", user_1);
			ActionContext.getContext().getSession().remove("verifyCode");
			return SUCCESS;
		}
		addFieldError("user.password",this.getText("password.erro"));
		return INPUT;
	}
	
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return SUCCESS;
	}
	
	// 校验
	public void validateLogin() 
	{
		String verifyCode = (String) ActionContext.getContext().getSession().get("verifyCode");
		if(!user.getVerifyCode().trim().isEmpty() && !user.getVerifyCode().equalsIgnoreCase(verifyCode)) {
			addFieldError("user.verifyCode", getText("verifyCode.erro2"));
		}
	}
}
