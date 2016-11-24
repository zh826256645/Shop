package com.zhonghao.user.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhonghao.user.domain.User;
import com.zhonghao.user.service.UserService;

public class RegisterAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 通过模型驱动接受用户传过来的参数
	private User user = new User();

	// ajax、验证图片
	private InputStream inputStream;

	// 注入 UserService 组件
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public User getModel() {
		return user;
	}

	// 使用 Ajax 验证
	public String verify() throws IOException {
		// 当验证的信息是用户名时
		if(user.getUsername() != null) {
			User user_1 = getUserService().findByUsername(user.getUsername());
			// 判断用户名，生成对应的响应
			if(user_1 != null) {
				ActionContext.getContext().put("user_1", user_1);
				// 用户名不可以使用，向客户端发送不可使用的信息
				inputStream = new ByteArrayInputStream(getText("username.ajax.no").getBytes("UTF-8"));
			}else {
				// 用户名可以使用，向用户名发送可以使用的信息
				inputStream = new ByteArrayInputStream(getText("username.ajax.yes").getBytes("UTF-8"));
			}
			return SUCCESS;
		} 
		// 当验证的信息是验证码时
		else if(user.getVerifyCode() != null) {
			String verifyCode = (String) ActionContext.getContext().getSession().get("verifyCode");
			if(verifyCode.equalsIgnoreCase(user.getVerifyCode())) {
				inputStream = new ByteArrayInputStream(getText("verifyCode.succ").getBytes("UTF-8"));
			}else {
				inputStream = new ByteArrayInputStream(getText("verifyCode.erro").getBytes("UTF-8"));
			}
			return SUCCESS;
		}
		return SUCCESS;
	}

	// 用户注册
	public String register() {
		// 过滤用户的错误操作
		User user_1 = (User) ActionContext.getContext().get("user_1");
		if(user_1 != null && user_1.getUsername().equals(user.getUsername())){
			this.addFieldError("user.username", getText("username.repeat"));
			return INPUT;
		}
		if(getUserService().save(user)){
			this.addActionMessage("恭喜注册成功！请去邮箱进行注册");
			ActionContext.getContext().getSession().remove("verifyCode");
			return SUCCESS;
		}else{
			System.out.println("2");
			this.addFieldError("user.username", getText("username.repeat"));
			return INPUT;
		}
	}

	// 用户激活
	public String activate() {
		String code = ServletActionContext.getRequest().getParameter("code");
		if(code != null){
			User user_1 = getUserService().findByCode(code);
			if(user_1 == null) {
				this.addActionMessage(getText("user.activate.erro"));
				return LOGIN;
			}
			this.addActionMessage(getText("user.activate.succ"));
			return LOGIN;
		}
		else{
			this.addActionMessage(getText("erro"));
			return LOGIN;
		}
	}
	
	// 校验
	public void validateRegister() {

		String verifyCode = (String) ActionContext.getContext().getSession().get("verifyCode");
		if(verifyCode != null && !user.getVerifyCode().trim().isEmpty() && !user.getVerifyCode().equalsIgnoreCase(verifyCode)) {
			addFieldError("user.verifyCode", getText("verifyCode.erro2"));
		}
	}
}
