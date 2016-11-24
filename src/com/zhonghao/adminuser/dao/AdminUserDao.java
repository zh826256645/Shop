package com.zhonghao.adminuser.dao;

import com.zhonghao.adminuser.domain.AdminUser;
import com.zhonghao.utils.BaseDao;

public interface AdminUserDao extends BaseDao<AdminUser> {

	public AdminUser findByUsername(String username);
}
