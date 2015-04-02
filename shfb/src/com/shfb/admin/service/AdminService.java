package com.shfb.admin.service;

import java.util.List;

import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.NewsDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.News;
import com.shfb.common.entity.Renew;

public interface AdminService {
	

	//得到Signdict表的Sequence
	public Integer getSequence(String table);
	
	/**用户*/
	//按条件查询用户
	public MemberDTO findUsers(String strWhere,Integer pageNow,Integer pageSize);
	//用户查询
	public List<Members> listMembers(String strWhere);
	//编辑用户
	public MemberDTO editUser(Members user,String strWhere,Integer pageNow,Integer pageSize);
	//续费
	public MemberDTO renew(Renew rn,String strWhere,Integer pageNow,Integer pageSize);
	//删除用户
	public MemberDTO deleteUser(List<Members> list);
	//续费信息查询
	public RenewDTO findRenew(String strWhere,Integer pageNow,Integer pageSize);
	//续费信息 关联查询
	public RenewDTO findRenew_combine(String strWhere,Integer pageNow,Integer pageSize);
	
	/**新闻*/
	//新闻检索
	public NewsDTO findNews(String strWhere,Integer pageNow,Integer pageSize);
	//编辑新闻
	public NewsDTO editNews(News nw,String strWhere,Integer pageNow,Integer pageSize);
	
}
