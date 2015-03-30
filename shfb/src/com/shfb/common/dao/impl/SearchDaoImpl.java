package com.shfb.common.dao.impl;

import com.shfb.common.dao.BaseDao;
import com.shfb.common.dao.SearchDao;
import com.shfb.common.entity.Users;

public class SearchDaoImpl implements SearchDao {

	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public Users findUsersById(Integer id) {
		return (Users) baseDao.loadById(Users.class,id);
	}

}
