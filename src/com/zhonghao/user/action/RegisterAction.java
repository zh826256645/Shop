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

	// ͨ��ģ�����������û��������Ĳ���
	private User user = new User();

	// ajax����֤ͼƬ
	private InputStream inputStream;

	// ע�� UserService ���
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

	// ʹ�� Ajax ��֤
	public String verify() throws IOException {
		// ����֤����Ϣ���û���ʱ
		if(user.getUsername() != null) {
			User user_1 = getUserService().findByUsername(user.getUsername());
			// �ж��û��������ɶ�Ӧ����Ӧ
			if(user_1 != null) {
				ActionContext.getContext().put("user_1", user_1);
				// �û���������ʹ�ã���ͻ��˷��Ͳ���ʹ�õ���Ϣ
				inputStream = new ByteArrayInputStream(getText("username.ajax.no").getBytes("UTF-8"));
			}else {
				// �û�������ʹ�ã����û������Ϳ���ʹ�õ���Ϣ
				inputStream = new ByteArrayInputStream(getText("username.ajax.yes").getBytes("UTF-8"));
			}
			return SUCCESS;
		} 
		// ����֤����Ϣ����֤��ʱ
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

	// �û�ע��
	public String register() {
		// �����û��Ĵ������
		User user_1 = (User) ActionContext.getContext().get("user_1");
		if(user_1 != null && user_1.getUsername().equals(user.getUsername())){
			this.addFieldError("user.username", getText("username.repeat"));
			return INPUT;
		}
		if(getUserService().save(user)){
			this.addActionMessage("��ϲע��ɹ�����ȥ�������ע��");
			ActionContext.getContext().getSession().remove("verifyCode");
			return SUCCESS;
		}else{
			System.out.println("2");
			this.addFieldError("user.username", getText("username.repeat"));
			return INPUT;
		}
	}

	// �û�����
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
	
	// У��
	public void validateRegister() {

		String verifyCode = (String) ActionContext.getContext().getSession().get("verifyCode");
		if(verifyCode != null && !user.getVerifyCode().trim().isEmpty() && !user.getVerifyCode().equalsIgnoreCase(verifyCode)) {
			addFieldError("user.verifyCode", getText("verifyCode.erro2"));
		}
	}
}
