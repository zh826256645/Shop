package com.zhonghao.user.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhonghao.utils.VerifyCode;

public class VerifyCodeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 生成验证码的 Action
	public String execute() throws Exception {
		// 获得 response 对象
		HttpServletResponse re = ServletActionContext.getResponse();
		// 设置编码
		re.setContentType("text/html;charset=utf-8");
		// 获得输出流
		OutputStream out = re.getOutputStream();
		// 生成输出验证码图片
		VerifyCode vc = new VerifyCode();
		VerifyCode.output(vc.getImage(), out);
		// 记录验证码
		ActionContext.getContext().getSession().put("verifyCode", vc.getText());
		return NONE;
	}
}
