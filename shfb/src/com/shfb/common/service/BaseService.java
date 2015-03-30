package com.shfb.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shfb.common.dao.EntityDao;



@Service
public class BaseService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public List<Object> getAllList(String tableSimpleName){
		StringBuffer sff = new StringBuffer();
		sff.append("select a from ").append(tableSimpleName).append(" a ");
		List<Object> list = entityDao.createQuery(sff.toString());
		return list;
	}
	/**
	 * find the list of entity by where conditions 
	 * @param tableSimpleName
	 * @param columns
	 * @param values
	 * @return  list
	 */
	public List<Object> findByWhere(String tableSimpleName,String[] columns,String[] values){
		StringBuffer sb = new StringBuffer();
		sb.append("select a from ").append(tableSimpleName).append( " a where ");
		if(columns.length==values.length){
			for(int i = 0; i < columns.length; i++){
				sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");
				if(i < columns.length-1){
					sb.append(" and ");
				}
			}
			List<Object> list = entityDao.createQuery(sb.toString());
			return list.size()>0?list:null;
		}else{
			return null;
		}
	}
	/**
	 * save
	 * @param obj
	 */
	public void save(Object obj){
		entityDao.save(obj);
	}
	/**
	 * delete
	 * @param obj
	 */
	public void delete(Object obj){
		entityDao.delete(obj);
	}
	/**
	 * update
	 * @param obj
	 */
	public void update(Object obj){
		entityDao.update(obj);
	}
}
