package com.shfb.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shfb.common.dao.EntityDao;


public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao{
	/**
	 * query
	 */
	@SuppressWarnings("unchecked")
	public List<Object> createQuery(final String queryString) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						List<Object> rows = query.list();
						return rows;
					}
				});
	}
	/**
	 * add
	 */
	public Object save(final Object model) {
		return  getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						session.save(model);
						return null;
					}
				});
	}
	/**
	 * update
	 */
	public void update(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.update(model);
				return null;
			}
		});
	}
	/**
	 * delete
	 */
	public void delete(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.delete(model);
				return null;
			}
		});
	}
}
