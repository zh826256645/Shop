package com.zhonghao.adminuser.service;

import com.zhonghao.adminuser.domain.AdminUser;

public interface AdminUserService {

	public AdminUser findByUid(Integer uid);
	
	public AdminUser findByUsername(String username);
}
