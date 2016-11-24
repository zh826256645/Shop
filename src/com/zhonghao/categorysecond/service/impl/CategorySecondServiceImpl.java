package com.zhonghao.categorysecond.service.impl;

import javax.transaction.Transactional;

import com.zhonghao.category.dao.CategoryDao;
import com.zhonghao.category.domain.Category;
import com.zhonghao.categorysecond.dao.CategorySecondDao;
import com.zhonghao.categorysecond.domain.CategorySecond;
import com.zhonghao.categorysecond.service.CategorySecondService;
import com.zhonghao.utils.PageBean;

/**
 * Service ���
 * @author ZhongHao
 *
 */
public class CategorySecondServiceImpl implements CategorySecondService {
	
	// ע�� Dao ���
	private CategorySecondDao categorySecondDao;
	
	private CategoryDao categoryDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// ���ط�ҳ Bean
	@Transactional
	public PageBean<CategorySecond> getPageBean(int page) {
		return categorySecondDao.getCategorySecondPageBean(10, page);
	}


	// ��Ӷ�������
	@Transactional
	public void addCategorySecond(String csname, int cid) {
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCategory(categoryDao.get(Category.class, cid));
		categorySecond.setCsname(csname);
		categorySecondDao.save(categorySecond);
	}

	// ��ѯ��������
	@Transactional
	public CategorySecond findByCsid(int csid) {
		return categorySecondDao.get(CategorySecond.class, csid);
	}

	// ���¶�������
	@Transactional
	public void update(String csname, int csid) {
		CategorySecond categorySecond = categorySecondDao.get(CategorySecond.class, csid);
		categorySecond.setCsname(csname);
		categorySecondDao.update(categorySecond);
	}

	// ɾ����������
	@Transactional
	public void delete(int csid) {
		CategorySecond categorySecond = categorySecondDao.load(CategorySecond.class, csid);
		categorySecondDao.delete(categorySecond);
	}

}
