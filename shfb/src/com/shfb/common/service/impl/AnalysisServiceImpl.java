package com.shfb.common.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.shfb.common.dao.AnalysisDao;
import com.shfb.common.rs.dto.AnlsDTO;
import com.shfb.common.rs.dto.Anls_Name_DTO;
import com.shfb.common.rs.info.AnlsInfo;
import com.shfb.common.rs.info.Anls_Name;
import com.shfb.common.rs.info.CountInfo;
import com.shfb.common.service.AnalysisService;


public class AnalysisServiceImpl implements AnalysisService {
	
	private AnalysisDao analysisDao;
	
	public AnalysisDao getAnalysisDao() {
		return analysisDao;
	}
	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}

	@Override
	public AnlsDTO anls(String anlsType, String strWhere, String rankNum) {
		AnlsDTO dto=analysisDao.anls(anlsType, strWhere);
		List<AnlsInfo> list=dto.getAnList();
		dto.setXarray(getXaxis(list,20));
		dto.setYarray(getYaxis(list,20));
		return dto;
	}
	
	@Override
	public Anls_Name_DTO anls_name(String strWhere) {
		Anls_Name_DTO dto=analysisDao.anls_name(strWhere);
		List<Anls_Name> list=dto.getList();
		List<AnlsInfo> alist=getInfo(list,20); 
		dto.setXarray(getXaxis(alist,20));
		dto.setYarray(getYaxis(alist,20));
		return dto;
	}

	//得到统计专利的总量
	private Integer getTotal(List<AnlsInfo> list){
		Integer total=0;
		for(int i=0;i<list.size();i++){
			total+=Integer.parseInt(list.get(i).getyAxis());
		}
		return total;
	}
	
	//设置zaxis为此项的百分比
	private List<AnlsInfo> setZAxis(List<AnlsInfo> list,Integer total){
		double dividend=total*1.0;
		double divisor=0;
		NumberFormat nf = NumberFormat.getPercentInstance();  
		nf.setMinimumFractionDigits(2);    //    保留到小数点后2位
		for (int i = 0; i < list.size(); i++) {
			divisor=Integer.parseInt(list.get(i).getyAxis())*1.0;
			list.get(i).setzAxis(nf.format(divisor/dividend));
		}
		return list;
	}
	
	//饼图数据加工
	private String getHighChartsData(List<AnlsInfo> list,String rankNum,String anlsType,Integer total){
		String data=null;
		int rn=Integer.parseInt(rankNum);
		int length=0;
		if(list.size()>rn){
			length=rn;
		}else{
			length=list.size();
		}
		if("ad".equals(anlsType)||"pd".equals(anlsType)){
			Collections.sort(list);
		}
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		int top10num=0;
		for(int i=0;i<length;i++){
			sb.append("['").append(list.get(i).getxAxis()).append("',").append(list.get(i).getyAxis()).append("],");
			top10num+=Integer.parseInt(list.get(i).getyAxis());
		}
		if((total-top10num)==0){
			sb.deleteCharAt(sb.lastIndexOf(",")).append("]");
		}else{
			sb.append("['其他',").append(total-top10num).append("]]");
		}
		
		data=sb.toString();
		return data;
	}
	
	//线图和柱图x轴数据的获得
	private String getXaxis(List<AnlsInfo> list,int rankNum){
		if(list.size()<=0){
			return "[]";
		}
		int size=rankNum;
		if(list.size()<rankNum){
			size=list.size();
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append("'").append(list.get(i).getxAxis()).append("',");
		}
		sb.deleteCharAt(sb.lastIndexOf(",")).append("]");
		return sb.toString();
	}
	
	//线图和柱图y轴数据的获得
	private String getYaxis(List<AnlsInfo> list,int rankNum){
		if(list.size()<=0){
			return "[]";
		}
		int size=rankNum;
		if(list.size()<rankNum){
			size=list.size();
		}
		StringBuilder sb=new StringBuilder();
		sb.append("[{data:[");
		for (int i = 0; i < size; i++) {
			sb.append(list.get(i).getyAxis()).append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(",")).append("]}]");
		return sb.toString();
	}
	
	
	//标引项分析饼图数据的生成
	private String getSignData(List<CountInfo> list){
		StringBuilder sb=new StringBuilder();
		if(list.size()>0){
			sb.append("[");
			for(CountInfo ci:list){
				sb.append("['").append(ci.getName()).append("',").append(ci.getNum()).append("],");
			}
			sb.deleteCharAt(sb.lastIndexOf(",")).append("]");
		}
		return sb.toString();
	}
	
	//得到前20项的分析对象List
	private List<AnlsInfo> getInfo(List<Anls_Name> list,int rankNum){
		List<AnlsInfo> alist=new ArrayList<AnlsInfo>();
		int size=rankNum;
		if(list.size()<rankNum){
			size=list.size();
		}
		for(int i=0;i<size;i++){
			AnlsInfo ai=new AnlsInfo(list.get(i).getNick_name(),list.get(i).getNum(),"");
			alist.add(ai);
		}
		return alist;
	}
	
}
