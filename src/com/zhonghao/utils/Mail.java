package com.zhonghao.utils;


import java.util.ArrayList;
import java.util.List;

public class Mail {

	//发件人
	private String form;
	//收件人
	private StringBuilder toAddress = new StringBuilder();
	//抄送人
	private StringBuilder ccAddress = new StringBuilder();
	//暗送人
	private StringBuilder bccAddress = new StringBuilder();
	//邮件标题
	private String subject;
	//邮件文本内容
	private String content;

	//邮件附件
	private List<AttachBean> attachlist = new ArrayList<AttachBean>();

	//获得发件人
	public String getForm() {
		return form;
	}

	//设置发件人
	public void setForm(String form) {
		this.form = form;
	}

	//获得所有收件人地址
	public String getToAddress() {
		return toAddress.toString();
	}

	//设置收件人地址，可以设置多个
	public void setToAddress(String toAddress) {
		if(this.toAddress.length() > 0 )
			this.toAddress.append(",");
		this.toAddress.append(toAddress);
	}

	//获得所有抄送人地址
	public String getCcAddress() {
		return ccAddress.toString();
	}

	//设置抄送人地址，可以设置多个
	public void setCcAddress(String ccAddress) {
		if(this.ccAddress.length() > 0 )
			this.ccAddress.append(",");
		this.ccAddress.append(ccAddress);
	}

	//获得所有暗送人的地址
	public String getBccAddress() {
		return bccAddress.toString();
	}

	//设置暗送人的地址，可以是多个
	public void setBccAddress(String bccAddress) {
		if(this.bccAddress.length() > 0)
			this.bccAddress.append(",");
		this.bccAddress.append(bccAddress);
	}

	//获得主题
	public String getSubject() {
		return subject;
	}

	//设置主题
	public void setSubject(String subject) {
		this.subject = subject;
	}

	//获得邮件文本内容
	public String getContent() {
		return content;
	}

	//设置邮件文本内容
	public void setContent(String content) {
		this.content = content;
	}

	//获得所有附件
	public List<AttachBean> getAttachlist() {
		return attachlist;
	}

	//设置附件
	public void setAttachlist(AttachBean attach) {
		this.attachlist.add(attach);
	}

	public Mail(){}
	
	public Mail(String form, String toAddress, String ccAddress, String bccAddress, String subject,
			String content, AttachBean... attachBean) {
		super();
		this.form = form;
		this.toAddress.append(toAddress);
		this.ccAddress.append(ccAddress);
		this.bccAddress.append(bccAddress);
		this.subject = subject;
		this.content = content;
		if(attachBean.length > 0){
			for(AttachBean attach : attachBean){
				this.attachlist.add(attach);			
			}
		}
	}
	
	public Mail(String form, String toAddress, String ccAddress, String subject,
			String content, AttachBean... attachBean) {
		this(form,toAddress,ccAddress,null,subject,content,attachBean);
	}
	
	public Mail(String form, String toAddress, String subject,
			String content, AttachBean... attachBean) {
		this(form,toAddress,null,null,subject,content,attachBean);
	}
}
