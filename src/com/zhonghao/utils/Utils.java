package com.zhonghao.utils;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;




public class Utils {

	/**
	 * ��ȡ UUID
	 * @return
	 */
	public static String getUUID() { 
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * ��ע���û�������֤�ʼ�
	 * @param code ��֤��
	 * @param toAddress �û���ַ
	 */
	public static void sendCodeMeil(String code,String toAddress) {
		Mail mail = new Mail("18813973744@163.com", toAddress, "�Ӻ��̳Ƿ����㼤���ʼ�", 
				"<h1>�����������Ӽ�������˺ţ���</h1><a href='http://192.168.1.103:8888/shopBySSH/activate.action?code=" + code + "'>http://192.168.1.103/shopBySSH/activate.action?code=" + code + "</a>");
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
