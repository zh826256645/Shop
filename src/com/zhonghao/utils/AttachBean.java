package com.zhonghao.utils;
import java.io.File;

public class AttachBean {

	//�ļ�·��
	private String file;
	//�ļ�����
	private String filename;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public File getFiles(){
		return new File(file);
	}
	
	public AttachBean(String file, String filename) {
		super();
		this.file = file;
		this.filename = filename;
	}
	
}
