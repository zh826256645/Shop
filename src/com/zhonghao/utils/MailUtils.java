package com.zhonghao.utils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class MailUtils {

	/**
	 * �����ʼ�
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws IOException 
	 */
	public static void sendMail(String host,final String username,final String password,Mail mail) throws AddressException, MessagingException, IOException{

		Properties props = new Properties();
		props.setProperty("mail.host", host);
		props.setProperty("mail."+ getProtocol(host) +".auth", "true");

		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		MimeMessage message = new MimeMessage(session);
		message.setSubject(mail.getSubject());

		//�����ռ���
		message.setFrom(new InternetAddress(mail.getForm()));

		String toAddress = mail.getToAddress();
		if(!toAddress.equals("null")){
			String[] address = toAddress.split(",");
			for(int i = 0 ;i < address.length;i++){
				message.setRecipients(RecipientType.TO, address[i]);
			}
		}

		//���ó�����
		String ccAddress = mail.getCcAddress();
		if(!ccAddress.equals("null")){
			String[] address = ccAddress.split(",");
			for(int i = 0 ;i < address.length;i++){
				message.setRecipients(RecipientType.CC, address[i]);
			}
		}

		//���ð�����
		String bccAddress = mail.getBccAddress();
		if(!bccAddress.equals("null")){
			String[] address = bccAddress.split(",");
			for(int i = 0 ;i < address.length;i++){
				message.setRecipients(RecipientType.BCC, address[i]);
			}
		}

		//�����ಿ��
		MimeMultipart parts = new MimeMultipart();

		//�����ʼ��������岿�� 
		MimeBodyPart contentpart = new MimeBodyPart();
		//�������岿�ֵ�����
		contentpart.setContent(mail.getContent(),"text/html;charset=utf-8");
		parts.addBodyPart(contentpart);

		if(mail.getAttachlist() == null || mail.getAttachlist().isEmpty()){
			message.setContent(parts);
			Transport.send(message);
			return ;
		}

		List<AttachBean> list = mail.getAttachlist();
		//��Ӹ���
		for(AttachBean attach : list){
			MimeBodyPart  part = new MimeBodyPart();
			part.attachFile(attach.getFiles());
			part.setFileName(MimeUtility.encodeText(attach.getFilename()));
			parts.addBodyPart(part);
		}
		message.setContent(parts);
		Transport.send(message);
	}


	private static String getProtocol(String host){

		int i = host.indexOf(".");
		if(i == -1)
			return null;
		String protocol = host.substring(0, i);
		return protocol;
	}

}
