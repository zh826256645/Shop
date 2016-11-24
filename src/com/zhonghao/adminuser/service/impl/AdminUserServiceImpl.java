package com.zhonghao.adminuser.service.impl;

import javax.transaction.Transactional;

import com.zhonghao.adminuser.dao.AdminUserDao;
import com.zhonghao.adminuser.domain.AdminUser;
import com.zhonghao.adminuser.service.AdminUserService;

/**
 * Service ���
 * @author ZhongHao
 *
 */
public class AdminUserServiceImpl implements AdminUserService {
	
	// ע�� DAO ���
	private AdminUserDao adminUserDao;
	
	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	@Transactional
	public AdminUser findByUid(Integer uid) {

		return adminUserDao.get(AdminUser.class, uid);
	}

	// ͨ�� username ����
	@Transactional
	public AdminUser findByUsername(String username) {
		return adminUserDao.findByUsername(username);
	}

}
