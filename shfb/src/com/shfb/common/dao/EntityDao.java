package com.shfb.common.dao;

import java.util.List;
/**
 * 对数据库操作的增删改查操作
 * @author song009
 *
 */
public interface EntityDao {
	public List<Object> createQuery(final String queryString);
	public Object save(final Object model);
	public void update(final Object model);
	public void delete(final Object model);
}
