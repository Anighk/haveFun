package com.shfb.common.dao;

import com.shfb.common.entity.Users;

public interface SearchDao {
	public Users findUsersById(Integer id);
}
