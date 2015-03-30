package com.shfb.common.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shfb.common.dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	/** 统计指定类的所有持久化对象 */
	public int countAll(String clazz) {
		final String hql = "select count(*) from " + clazz + " as a";
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 统计指定类的查询结果 */
	public int countQuery(String hql) {
		final String counthql = hql;
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(counthql);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});
		return count.intValue();
	}

	/** 删除指定ID的持久化对象 */
	public void delById(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
	}

	/** 删除对象 */
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	/** 装载指定类的所有持久化对象 */
	public List listAll(String clazz) {
		return getHibernateTemplate().find(
				"from " + clazz + " as a order by a.id desc");
	}

	/** 装载指定类的所有持久化对象 */
	public List find(String hql) {
		System.out.println(hql);
		return getHibernateTemplate().find(hql);
	}

	/** 分页装载指定类的所有持久化对象 */
	public List listAll(String clazz, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		// final String hql = "from "+clazz+ " as a order by a.id desc";
		final String hql = clazz;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}

	/** 加载指定ID的持久化对象 */
	public Object loadById(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	/** 加载满足条件的持久化对象 */
	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});
		if (list.size() > 0)
			obj = list.get(0);
		return obj;
	}

	/** 查询指定类的满足条件的持久化对象 */
	public List query(String hql) {
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});
	}
	
	/** 查询指定类的满足条件的持久化对象 */
	public List querys(String sql) {
		final String sql1 = sql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql1);
				return query.list();
			}
		});
	}

	/** 用sql语句查询指定类的满足条件的持久化对象数组 */
	public List queryArray(String sql) {
		final String sql1 = sql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql1);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}
	
	/** 用sql语句查询指定类的满足条件的持久化对象数组 */
	public List queryTypeCount(String sql) {
		final String sql1 = sql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql1).addScalar("PTYPE",Hibernate.STRING).addScalar("NUM", Hibernate.STRING);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}
	
	/** 用sql语句查询指定类的满足条件的持久化对象数组 */
	public List queryTaskCount(String sql) {
		final String sql1 = sql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql1).addScalar("USERID",Hibernate.STRING).addScalar("NUM", Hibernate.STRING);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

	/** 分页查询指定类的满足条件的持久化对象 */
	public List query(String hql, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}
	
	/** 分页查询指定类的满足条件的持久化对象 */
	public List querySe(String hql, int startNum, int maxResults) {
		final int startNo = startNum;
		final int maxresults = maxResults;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql1);
				query.setMaxResults(maxresults);
				query.setFirstResult(startNo);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

	/** 保存或更新指定的持久化对象 */
	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/** 条件更新数据 */
	public int update(String hql) {
		final String hql1 = hql;
		return ((Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql1);
						return query.executeUpdate();
					}
				})).intValue();
	}

	/** 查询指定类的满足条件的持久化对象 --sql 查询 */
	public String getSingleValueBySql(String sql) {
		String result = "";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createSQLQuery(sql);
		List list = query.list();

		if (!list.isEmpty()) {
			result = (String) list.get(0);
		}
		session.close();
		return result;
	}

	/** 执行sql语句，返回单个个数值 */
	public int getCountValueBySql(String sql) {
		sql = "SELECT COUNT(*) AS count_of_query FROM (" + sql + ") a";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		SQLQuery q = session.createSQLQuery(sql);
		q.addScalar("count_of_query", Hibernate.INTEGER);
		Object o = (Object) q.uniqueResult();
		session.close();
		return ((Integer) o).intValue();
	}

	/**
	 * 查询指定类的满足条件的持久化对象 --sql 查询
	 * 
	 * @throws SQLException
	 */
	public Vector getVectorBySql(String sql) throws SQLException {
		Vector vData = new Vector();
		java.sql.Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		Session session = null;
		try {
			session = this.getHibernateTemplate().getSessionFactory()
					.openSession();
			conn = session.connection();

			stmt = (java.sql.Statement) conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			meta = rs.getMetaData();
			int nColNum = meta.getColumnCount();
			while (rs.next()) {
				Vector vRow = new Vector();
				for (int i = 1; i <= nColNum; i++) {
					if (!meta.getColumnName(i).equalsIgnoreCase("cout")) {
						String sValue = rs.getString(i);
						if (sValue == null) {
							sValue = "";
						} else {
							sValue = sValue.trim();
						}
						vRow.addElement(sValue);
					}
				}
				vData.addElement(vRow);
			}
			if (vData.size() == 0) {
				vData = null;
			}
		} finally {
			rs.close();
			stmt.close();
			conn.close();
			session.close();

		}
		return vData;

	}

	/** 执行sql语句 */
	public boolean executeSql(String sql) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		int i = session.createSQLQuery(sql).executeUpdate();
		t.commit();
		session.close();
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List sqlQuery(String sql, int pageNow, int pageSize) {
		final int pNo = pageNow;
		final int pSize = pageSize;
		final String sql1 = sql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List<Object[]> result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

	@Override
	public int getSequenceNextnval(String seq) {
		String sql="select "+seq+".nextval as count_of_query from dual";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		SQLQuery q = session.createSQLQuery(sql);
		q.addScalar("count_of_query", Hibernate.INTEGER);
		Object o = (Object) q.uniqueResult();
		session.close();
		return ((Integer) o).intValue();
	}

	@Override
	public void callProcedure(String procedure) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Connection conn = session.connection();   
		ResultSet rs =null;  
		CallableStatement call;
		String hql="{Call "+procedure+"()}";
		try {
			call = conn.prepareCall(hql);
			rs = call.executeQuery(); 
			rs.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally{
			session.close();
		}
		
	}

}
