package com.zhonghao.utils;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	// ���� ID ����ʵ�壬��֧���ӳټ���
	public T get(Class<T> entityClazz,Serializable id);
	
	// ���� ID ����ʵ�壬֧���ӳټ���
	public T load(Class<T> entityClazz,Serializable id);
	
	// ����ʵ��
	public Serializable save(T entity);
	
	// ����ʵ��
	public void update(T entity);
	
	// ɾ��ʵ��
	public void delete(T entity);
	
	// ���� ID ɾ��ʵ��
	public void delete(Class<T> entityClazz,Serializable id,String idName);
	
	// ��ȡ����ʵ��
	public List<T> findAll(Class<T> entityClazz);
	
	// ��ȡʵ������
	public long findCount(Class<T> entityClazz);
}
