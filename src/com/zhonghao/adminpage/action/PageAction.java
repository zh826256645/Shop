package com.zhonghao.adminpage.action;

import com.opensymphony.xwork2.ActionSupport;

// ������ʾ���ҳ��� Action
public class PageAction extends ActionSupport {

	public String top() {
		return "top";
	}
	
	public String left() {
		return "left";
	}
	
	public String right() {
		return "right";
	}
	
	public String down() {
		return "down";
	}
}
