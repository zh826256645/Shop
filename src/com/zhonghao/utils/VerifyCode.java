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
	
	//设置图片的大小
	private int w = 70;
	private int h = 35;
	//创建一个随机对象
	private Random r = new Random();
	//创建字体数组
	private String[] fontNames = {"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"};
	//创建存放字符的字符串
	private String code = "23456789abcdefghijknmopqrstuvwsyzABCDEFGHIJKLNMPQRSTUVWSYZ";
	//创建背景颜色
	private Color bgColor = new Color(255,255,255);
	//创建存放验证码的字符串
	private String text;
	
	//生成一个随机的颜色
	private Color randomColor(){
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	//生成一个随机的字体
	private Font randomFont(){
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(5)+24;
		return new Font(fontName,style,size);
	}
	
	//生成随机干扰线
	private void drawLine(BufferedImage image){
		//设置干扰线的数量
		int num = 3;
		//获得绘画环境
		Graphics2D g = (Graphics2D)image.getGraphics();
		//在图片上循环画num条线
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
	
	//生成随机字符
	private char randomChar(){
		int index = r.nextInt(code.length());
		return code.charAt(index);
	}
	
	//创建BufferedImage
	private BufferedImage createImage(){
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, w, h);
		return image;
		
	}
	
	//调用这个方法创建出随机的验证码图片
	public BufferedImage getImage(){
		//创建一个图片
		BufferedImage image = createImage();
		//获得图片的绘画环境
		Graphics2D g = (Graphics2D)image.getGraphics();
		//用来装载生成的验证码文本
		StringBuilder sb = new StringBuilder();
		//像图片中画4个字符
		for(int i=0;i<4;i++){
			String a = randomChar()+"";
			sb.append(a);
			//设置当前字体的x轴坐标
			float x = i * 1.0F * w / 4;
			g.setFont(randomFont());
			g.setColor(randomColor());
			g.drawString(a, x, h-5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}
	
	//获得验证码的文本
	public String getText(){
		return text;
	}
	
	//调用这个方法绘制随机验证码图片
	public static void output(BufferedImage image,OutputStream out) throws IOException{
		ImageIO.write(image, "JPEG", out);
	}
	
}
