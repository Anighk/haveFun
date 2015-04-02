package com.shfb.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.shfb.common.dao.AnalysisDao;
import com.shfb.common.dao.BaseDao;
import com.shfb.common.rs.dto.AnlsDTO;
import com.shfb.common.rs.dto.Anls_Name_DTO;
import com.shfb.common.rs.info.AnlsInfo;
import com.shfb.common.rs.info.Anls_Name;
import com.shfb.common.util.BaseUtil;

public class AnalysisDaoImpl implements AnalysisDao {

	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
	
	@Override
	public AnlsDTO anls(String anlsType, String strWhere) {
		AnlsDTO dto=new AnlsDTO();
		String sql=null;
		sql="select count(*),"+anlsType+" from pat_search_log psl, zlt_user zu where psl.SEARCH_FROM=zu.USER_ID and "+anlsType+" is not null"
				+strWhere+ " group by "+anlsType+" order by 1 desc ";
		List<Object[]> list = new ArrayList<Object[]>();
		List<AnlsInfo> anList=new ArrayList<AnlsInfo>();
		list=baseDao.queryArray(sql);
		
		for(Object[] obj:list){
			AnlsInfo ai=new AnlsInfo();
			ai.setxAxis(BaseUtil.stringValueOf(obj[1]));
			ai.setyAxis(BaseUtil.stringValueOf(obj[0]));
			anList.add(ai);
		}
		dto.setAnList(anList);
		return dto;
	}
	
	private String subString(String s){
		String r=null;
		if(s.contains("|")){
			String[] sary=s.split("|");
			r=sary[0].trim();
		}else{
			r=s;
		}
		return r;
	}
	
	@Override
	public Anls_Name_DTO anls_name(String strWhere) {
		Anls_Name_DTO dto=new Anls_Name_DTO();
		String sql="select col1,nn,user_id,overdue_date,company,city,province from zlt_user zltu,(select count(*) col1,nick_name nn from pat_search_log psl,zlt_user zu where psl.search_from=zu.user_id  and zu.nick_name is not null "
							+strWhere+"group by zu.nick_name order by 1 desc) cnn where zltu.nick_name=cnn.nn";
		List<Object[]> list = new ArrayList<Object[]>();
		list=baseDao.queryArray(sql);
		List<Anls_Name> alist=new ArrayList<Anls_Name>();
		for(Object[] obj:list){
			Anls_Name an=new Anls_Name();
			an.setNum(BaseUtil.stringValueOf(obj[0]));
			an.setNick_name(BaseUtil.stringValueOf(obj[1]));
			an.setUser_id(BaseUtil.stringValueOf(obj[2]));
			an.setOverdue_date(BaseUtil.StringToDate(BaseUtil.stringValueOf(obj[3]),"yyyy-MM-dd"));
			an.setCompany(BaseUtil.stringValueOf(obj[4]));
			an.setCity(BaseUtil.stringValueOf(obj[5]));
			an.setProvince(BaseUtil.stringValueOf(obj[6]));
			alist.add(an);
		}
		dto.setList(alist);
		return dto;
	}

}
