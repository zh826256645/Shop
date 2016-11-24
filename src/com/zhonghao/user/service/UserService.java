package com.zhonghao.user.service;

import com.zhonghao.user.domain.User;

public interface UserService {
	
	public User findByUsername(String username);
	
	public boolean save(User user);
	
	public User findByCode(String code);
}
