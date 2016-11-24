package com.zhonghao.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class BaseDaoHibernate4<T> implements BaseDao<T> {

	// Dao ������г־û������ײ������� SessionFactory ���
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ���� ID ����ʵ�壬��֧���ӳټ���
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
	}
	
	// ���� ID ����ʵ�壬֧���ӳټ���
	@SuppressWarnings("unchecked")
	public T load(Class<T> entityClazz, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().load(entityClazz, id);
	}

	// ����ʵ��
	public Serializable save(T entity) {
		return getSessionFactory().getCurrentSession().save(entity);
	}

	// ����ʵ��
	public void update(T entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	// ɾ��ʵ��
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	// ���� ID ɾ��ʵ��
	public void delete(Class<T> entityClazz, Serializable id,String idName) {
		getSessionFactory().getCurrentSession()
			.createQuery("delete" + entityClazz.getSimpleName() + "en where en." + idName + "= ?")
			.setParameter(0, id).executeUpdate();
	}

	public List<T> findAll(Class<T> entityClazz) {
		
		return find("select en from " + entityClazz.getSimpleName() + " en");
	}


	public long findCount(Class<T> entityClazz) {
		List<?> list = find("select count(*) from " + entityClazz.getSimpleName());
		// ���ز�ѯ����õ���ʵ������
		if(list != null && list.size() == 1) {
			return (Long) list.get(0);
		}
		return 0;
	}

	// ���� HQL ����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) {
		return (List<T>)getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	// ���ݴ�ռλ�������� HQL ����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql,Object... params) {
		 // ������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i = 0,len = params.length;i < len;i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>)query.list();
	}

	// ʹ�� HQL �����з�ҳ��ѯ����
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize) {
				// ������ѯ
		return getSessionFactory().getCurrentSession().createQuery(hql)
				// ִ�з�ҳ
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
	
	// ʹ�� HQL �����з�ҳ��ѯ����
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql,int pageNo,int pageSize,Object... params) {
		// ������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		// Ϊ����ռλ���� HQL ������ò���
		for(int i = 0,len = params.length;i < len;i++){
			query.setParameter(i, params[i]);
		}
		// ִ�з�ҳ�������ؽ��
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
	}



}
