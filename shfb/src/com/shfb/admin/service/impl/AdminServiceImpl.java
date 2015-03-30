package com.shfb.admin.service.impl;

import java.util.List;

import com.shfb.admin.dao.AdminDao;
import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.admin.service.AdminService;
import com.shfb.common.dao.SearchDao;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.Renew;
import com.shfb.common.entity.Users;
import com.shfb.common.util.BaseUtil;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;
	private SearchDao searchDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public SearchDao getSearchDao() {
		return searchDao;
	}
	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}
	@Override
	public MemberDTO findUsers(String strWhere, Integer pageNow, Integer pageSize) {
		return adminDao.findUsers(strWhere, pageNow, pageSize);
	}
	@Override
	public MemberDTO editUser(Members member, String strWhere,Integer pageNow, Integer pageSize) {
		boolean flag=adminDao.saveMember(member);
		MemberDTO dto=adminDao.findUsers(strWhere, pageNow, pageSize);
		if(!flag){
			dto.setMessage("error");
		}
		return dto;
	}
	@Override
	public MemberDTO deleteUser(List<Members> list) {
		for(Members u:list){
			adminDao.deleteUser(u);
		}
		return adminDao.findUsers("",1, 10);
	}
	@Override
	public Integer getSequence(String table) {
		return adminDao.getSequence(table);
	}
	@Override
	public List<Members> listMembers(String strWhere) {
		return adminDao.listMembers(strWhere);
	}
	@Override
	public MemberDTO renew(Renew rn,String strWhere, Integer pageNow, Integer pageSize) {
		String where = " user_id = '"+rn.getUser_id()+"'";
		Members mb=adminDao.listMembers(where).get(0);
		mb.setOverdue_date(rn.getRenew_to());
		rn.setReg_date(mb.getReg_date());
		rn.setNick_name(mb.getNick_name());
		adminDao.saveRenew(rn);
		adminDao.saveMember(mb);
		return adminDao.findUsers(strWhere, pageNow, pageSize);
	}
	@Override
	public RenewDTO findRenew(String strWhere, Integer pageNow, Integer pageSize) {
		return adminDao.findRenew(strWhere, pageNow, pageSize);
	}
	@Override
	public RenewDTO findRenew_combine(String strWhere, Integer pageNow,
			Integer pageSize) {
		return adminDao.findRenew_combine(strWhere, pageNow, pageSize);
	}

}
