package com.zhonghao.adminuser.dao.impl;

import java.util.List;

import com.zhonghao.adminuser.dao.AdminUserDao;
import com.zhonghao.adminuser.domain.AdminUser;
import com.zhonghao.utils.BaseDaoHibernate4;

/**
 * DAO ���
 * @author ZhongHao
 *
 */
public class AdminUserDaoImpl extends BaseDaoHibernate4<AdminUser> implements AdminUserDao {

	// ͨ�� username ���� AdminUser
	public AdminUser findByUsername(String username) {
		List<AdminUser> list = find("from AdminUser au where au.username = ?",username);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
