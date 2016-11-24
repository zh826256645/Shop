package com.zhonghao.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.zhonghao.user.dao.UserDao;
import com.zhonghao.user.domain.User;
import com.zhonghao.user.service.UserService;
import com.zhonghao.utils.Utils;

public class UserServiceImpl implements UserService {

	// 注入 dao 组件
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 验证用户名是否已经验证
	 * 用户名存在，返回 true 否则返回 false
	 */
	@Transactional
	public User findByUsername(String username) {
		List<User> list = getUserDao().findByUsername(username);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 用户注册
	 */
	@Transactional
	public boolean save(User user) {
		if(findByUsername(user.getUsername()) == null) {
			user.setState(0);
			String code = Utils.getUUID();
			user.setCode(code);
			getUserDao().save(user);
			Utils.sendCodeMeil(code, user.getEmail());
			return true;
		}
		return false;
	}

	/**
	 * 用户激活
	 */
	@Transactional
	public User findByCode(String code) {
		List<User> list = getUserDao().findByCode(code);
		if(list != null && list.size() > 0) {
			User user = list.get(0);
			user.setState(1);
			user.setCode(null);
			getUserDao().update(user);
			return user;
		}
		return null;
	}
}
