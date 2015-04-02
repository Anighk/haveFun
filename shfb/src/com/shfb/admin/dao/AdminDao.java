package com.shfb.admin.dao;

import java.util.List;

import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.NewsDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.News;
import com.shfb.common.entity.Renew;

public interface AdminDao {
	//按条件查询用户
	public MemberDTO findUsers(String strWhere,Integer pageNow,Integer pageSize);
	//用户查询
	public List<Members> listMembers(String strWhere);
	//编辑用户
	public boolean saveMember(Members member);
	//删除用户
	public boolean deleteUser(Members u);
	//得到指定表的Sequence
	public Integer getSequence(String table);
	
	//添加续费信息
	public boolean saveRenew(Renew rn);
	//查询续费信息
	public RenewDTO findRenew(String strWhere,Integer pageNow,Integer pageSize);
	//续费信息 关联查询
	public RenewDTO findRenew_combine(String strWhere,Integer pageNow,Integer pageSize);
	
	//编辑新闻
	public boolean saveNews(News nw);
	//新闻检索
	public NewsDTO findNews(String strWhere,Integer pageNow,Integer pageSize);
}
