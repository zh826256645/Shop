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

	// ������֤��� Action
	public String execute() throws Exception {
		// ��� response ����
		HttpServletResponse re = ServletActionContext.getResponse();
		// ���ñ���
		re.setContentType("text/html;charset=utf-8");
		// ��������
		OutputStream out = re.getOutputStream();
		// ���������֤��ͼƬ
		VerifyCode vc = new VerifyCode();
		VerifyCode.output(vc.getImage(), out);
		// ��¼��֤��
		ActionContext.getContext().getSession().put("verifyCode", vc.getText());
		return NONE;
	}
}
