package com.shfb.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public interface BaseDao {
	/** 加载指定ID的持久化对象 */
	public Object loadById(Class clazz, Serializable id);

	/** 加载满足条件的持久化对象 */
	public Object loadObject(String hql);

	/** 删除指定ID的持久化对象 */
	public void delById(Class clazz, Serializable id);

	/** 删除指定的持久化对象 */
	public void delete(Object obj);
	
	/** 保存或更新指定的持久化对象 */
	public void saveOrUpdate(Object obj);
	
	/** 装载指定类的所有持久化对象 */
	public List listAll(String clazz);

	/** 分页装载指定类的所有持久化对象 */
	public List listAll(String clazz, int pageNo, int pageSize);

	/** 统计指定类的所有持久化对象 */
	public int countAll(String clazz);

	/** 查询指定类的满足条件的持久化对象 */
	public List query(String hql);
	
	/** 查询指定类的满足条件的持久化对象 */
	public List querys(String sql);
	
	/** 查询指定类的满足条件的持久化对象 */
	public List queryTypeCount(String sql);
	
	/** 查询指定类的满足条件的持久化对象 */
	public List queryTaskCount(String sql);

	/** 查询指定类的满足条件的持久化对象数组 */
	public List queryArray(String sql);

	public List find(String hql);

	/** 分页查询指定类的满足条件的持久化对象 */
	public List query(String hql, int pageNow, int pageSize);
	
	/** 分页查询指定类的满足条件的持久化对象 */
	public List querySe(String hql, int startNum, int maxResults);
	
	/** 分页查询指定类的满足条件的持久化对象 */
	public List sqlQuery(String sql, int pageNow, int pageSize);

	/** 统计指定类的查询结果 */
	public int countQuery(String hql);

	/** 条件更新数据 */
	public int update(String hql);

	/** 执行sql语句，返回Vector对象 */

	public Vector getVectorBySql(String sql) throws SQLException;

	/** 执行sql语句，返回单个String值 */
	public String getSingleValueBySql(String sql);

	/** 执行sql语句，返回单个个数值 */
	public int getCountValueBySql(String sql);

	/** 执行sql语句 */
	public boolean executeSql(String sql);

	
	/**返回指定序列的nextval*/
	public int getSequenceNextnval(String seq);
	
	/**调用无返回值的存储过程*/
	public void callProcedure(String procedure);
}
