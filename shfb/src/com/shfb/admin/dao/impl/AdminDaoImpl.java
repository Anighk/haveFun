package com.shfb.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.shfb.admin.dao.AdminDao;
import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.NewsDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.common.dao.BaseDao;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.News;
import com.shfb.common.entity.Renew;
import com.shfb.common.entity.Users;
import com.shfb.common.util.Constant;

public class AdminDaoImpl implements AdminDao {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MemberDTO findUsers(String strWhere, Integer pageNow, Integer pageSize) {
		MemberDTO dto=new MemberDTO();
		if(strWhere!=null){
			strWhere=strWhere.trim();
		}
		if(strWhere!=null&&!"".equals(strWhere)){
			strWhere="where "+strWhere;
		}
		String hql="from Members "+strWhere;
		List<Members> list=new ArrayList<Members>();
		list=baseDao.query(hql,pageNow,pageSize);
		dto.setmList(list);
		hql="select count(*) from Members "+strWhere;
		String count=baseDao.query(hql).get(0).toString();
		dto.setTotal(Integer.parseInt(count));
		return dto;
	}
	@Override
	public boolean saveMember(Members member) {
		try{
			baseDao.saveOrUpdate(member);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public boolean deleteUser(Members m) {
		try{
			baseDao.delete(m);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public Integer getSequence(String table) {
		return baseDao.getSequenceNextnval(table);
	}
	@Override
	public List<Members> listMembers(String strWhere) {
		String hql="from Members where "+strWhere;
		List<Members> list=new ArrayList<Members>();
		list=baseDao.query(hql);
		return list;
	}
	@Override
	public boolean saveRenew(Renew rn) {
		try{
			baseDao.saveOrUpdate(rn);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public RenewDTO findRenew(String strWhere, Integer pageNow, Integer pageSize) {
		RenewDTO dto=new RenewDTO();
		if(strWhere!=null){
			strWhere=strWhere.trim();
		}
		if(strWhere!=null&&!"".equals(strWhere)){
			strWhere="where "+strWhere;
		}
		String hql=" from Renew "+strWhere;
		List<Renew> list=new ArrayList<Renew>();
		list=baseDao.query(hql, pageNow, pageSize);
		dto.setList(list);
		hql=" select count(*) from Renew "+strWhere;
		String count=baseDao.query(hql).get(0).toString();
		dto.setTotal(Integer.parseInt(count));
		return dto;
	}
	@Override
	public RenewDTO findRenew_combine(String strWhere, Integer pageNow,
			Integer pageSize) {
		RenewDTO dto=new RenewDTO();
		String hql=" select r from Renew r,Members m where r.user_id=m.user_id and "+strWhere;
		List<Renew> list=new ArrayList<Renew>();
		list=baseDao.query(hql, pageNow, pageSize);
		dto.setList(list);
		hql=" select count(*) from Renew r,Members m where r.user_id=m.user_id and "+strWhere;
		String count=baseDao.query(hql).get(0).toString();
		dto.setTotal(Integer.parseInt(count));
		return dto;
	}
	@Override
	public boolean saveNews(News nw) {
		try{
			baseDao.saveOrUpdate(nw);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public NewsDTO findNews(String strWhere, Integer pageNow, Integer pageSize) {
		NewsDTO dto=new NewsDTO();
		if(strWhere!=null){
			strWhere=strWhere.trim();
		}
		if(strWhere!=null&&!"".equals(strWhere)){
			strWhere="where "+strWhere;
		}
		String hql=" from News "+strWhere;
		List<News> list=new ArrayList<News>();
		list=baseDao.query(hql, pageNow, pageSize);
		dto.setList(list);
		hql=" select count(*) from News "+strWhere;
		String count=baseDao.query(hql).get(0).toString();
		dto.setTotal(Integer.parseInt(count));
		return dto;
	}
	

}
