package com.zhonghao.adminuser.service.impl;

import javax.transaction.Transactional;

import com.zhonghao.adminuser.dao.AdminUserDao;
import com.zhonghao.adminuser.domain.AdminUser;
import com.zhonghao.adminuser.service.AdminUserService;

/**
 * Service 组件
 * @author ZhongHao
 *
 */
public class AdminUserServiceImpl implements AdminUserService {
	
	// 注入 DAO 组件
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

	// 通过 username 查找
	@Transactional
	public AdminUser findByUsername(String username) {
		return adminUserDao.findByUsername(username);
	}

}
