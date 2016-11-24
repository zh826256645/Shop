package com.zhonghao.user.dao.impl;

import java.util.List;

import com.zhonghao.user.dao.UserDao;
import com.zhonghao.user.domain.User;
import com.zhonghao.utils.BaseDaoHibernate4;

public class UserDaoImpl extends BaseDaoHibernate4<User> implements UserDao {

	// 根据用户名查找实体
	public List<User> findByUsername(String username) {
		return find("from User u where u.username = ?",username);
	}

	// 根据验证码查找实体
	public List<User> findByCode(String code) {
		return find("from User u where u.code = ?",code);
	}

}
