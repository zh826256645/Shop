package com.zhonghao.utils;


import java.util.ArrayList;
import java.util.List;

public class Mail {

	//������
	private String form;
	//�ռ���
	private StringBuilder toAddress = new StringBuilder();
	//������
	private StringBuilder ccAddress = new StringBuilder();
	//������
	private StringBuilder bccAddress = new StringBuilder();
	//�ʼ�����
	private String subject;
	//�ʼ��ı�����
	private String content;

	//�ʼ�����
	private List<AttachBean> attachlist = new ArrayList<AttachBean>();

	//��÷�����
	public String getForm() {
		return form;
	}

	//���÷�����
	public void setForm(String form) {
		this.form = form;
	}

	//��������ռ��˵�ַ
	public String getToAddress() {
		return toAddress.toString();
	}

	//�����ռ��˵�ַ���������ö��
	public void setToAddress(String toAddress) {
		if(this.toAddress.length() > 0 )
			this.toAddress.append(",");
		this.toAddress.append(toAddress);
	}

	//������г����˵�ַ
	public String getCcAddress() {
		return ccAddress.toString();
	}

	//���ó����˵�ַ���������ö��
	public void setCcAddress(String ccAddress) {
		if(this.ccAddress.length() > 0 )
			this.ccAddress.append(",");
		this.ccAddress.append(ccAddress);
	}

	//������а����˵ĵ�ַ
	public String getBccAddress() {
		return bccAddress.toString();
	}

	//���ð����˵ĵ�ַ�������Ƕ��
	public void setBccAddress(String bccAddress) {
		if(this.bccAddress.length() > 0)
			this.bccAddress.append(",");
		this.bccAddress.append(bccAddress);
	}

	//�������
	public String getSubject() {
		return subject;
	}

	//��������
	public void setSubject(String subject) {
		this.subject = subject;
	}

	//����ʼ��ı�����
	public String getContent() {
		return content;
	}

	//�����ʼ��ı�����
	public void setContent(String content) {
		this.content = content;
	}

	//������и���
	public List<AttachBean> getAttachlist() {
		return attachlist;
	}

	//���ø���
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
