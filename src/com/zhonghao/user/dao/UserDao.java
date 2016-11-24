package com.zhonghao.user.dao;

import java.util.List;

import com.zhonghao.user.domain.User;
import com.zhonghao.utils.BaseDao;

public interface UserDao extends BaseDao<User> {

	// ͨ�����û�����ȡ�û���Ϣ
	public List<User> findByUsername(String username);
	
	public List<User> findByCode(String code);
}
