package com.zhonghao.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	
	//����ͼƬ�Ĵ�С
	private int w = 70;
	private int h = 35;
	//����һ���������
	private Random r = new Random();
	//������������
	private String[] fontNames = {"����","���Ŀ���","����","΢���ź�","����_GB2312"};
	//��������ַ����ַ���
	private String code = "23456789abcdefghijknmopqrstuvwsyzABCDEFGHIJKLNMPQRSTUVWSYZ";
	//����������ɫ
	private Color bgColor = new Color(255,255,255);
	//���������֤����ַ���
	private String text;
	
	//����һ���������ɫ
	private Color randomColor(){
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	//����һ�����������
	private Font randomFont(){
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(5)+24;
		return new Font(fontName,style,size);
	}
	
	//�������������
	private void drawLine(BufferedImage image){
		//���ø����ߵ�����
		int num = 3;
		//��û滭����
		Graphics2D g = (Graphics2D)image.getGraphics();
		//��ͼƬ��ѭ����num����
		for(int i=0;i<num;i++){
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g.setStroke(new BasicStroke(1.5F));
			g.setColor(Color.BLUE);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	//��������ַ�
	private char randomChar(){
		int index = r.nextInt(code.length());
		return code.charAt(index);
	}
	
	//����BufferedImage
	private BufferedImage createImage(){
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, w, h);
		return image;
		
	}
	
	//������������������������֤��ͼƬ
	public BufferedImage getImage(){
		//����һ��ͼƬ
		BufferedImage image = createImage();
		//���ͼƬ�Ļ滭����
		Graphics2D g = (Graphics2D)image.getGraphics();
		//����װ�����ɵ���֤���ı�
		StringBuilder sb = new StringBuilder();
		//��ͼƬ�л�4���ַ�
		for(int i=0;i<4;i++){
			String a = randomChar()+"";
			sb.append(a);
			//���õ�ǰ�����x������
			float x = i * 1.0F * w / 4;
			g.setFont(randomFont());
			g.setColor(randomColor());
			g.drawString(a, x, h-5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}
	
	//�����֤����ı�
	public String getText(){
		return text;
	}
	
	//��������������������֤��ͼƬ
	public static void output(BufferedImage image,OutputStream out) throws IOException{
		ImageIO.write(image, "JPEG", out);
	}
	
}
