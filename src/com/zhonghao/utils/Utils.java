package com.zhonghao.utils;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;




public class Utils {

	/**
	 * 获取 UUID
	 * @return
	 */
	public static String getUUID() { 
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 给注册用户发送验证邮件
	 * @param code 验证码
	 * @param toAddress 用户地址
	 */
	public static void sendCodeMeil(String code,String toAddress) {
		Mail mail = new Mail("18813973744@163.com", toAddress, "钟浩商城发给你激活邮件", 
				"<h1>点击下面的连接激活你的账号！！</h1><a href='http://192.168.1.103:8888/shopBySSH/activate.action?code=" + code + "'>http://192.168.1.103/shopBySSH/activate.action?code=" + code + "</a>");
		try {
			MailUtils.sendMail("smtp.163.com", "18813973744", "rwwjwmdhyzh", mail);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
