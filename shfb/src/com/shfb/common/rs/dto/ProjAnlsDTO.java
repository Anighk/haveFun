package com.shfb.common.rs.dto;

import java.util.List;

import com.shfb.common.rs.info.CountInfo;

public class ProjAnlsDTO extends BaseDTO {
	
	private static final long serialVersionUID = 4055107998540154269L;
	private Integer total;
	private Integer assigned;
	private String data;
	private List<CountInfo> projList;
	private List<CountInfo> signList;
	private String signJson;
	private String xarray;
	private String yarray;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getAssigned() {
		return assigned;
	}
	public void setAssigned(Integer assigned) {
		this.assigned = assigned;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<CountInfo> getProjList() {
		return projList;
	}
	public void setProjList(List<CountInfo> projList) {
		this.projList = projList;
	}
	public List<CountInfo> getSignList() {
		return signList;
	}
	public void setSignList(List<CountInfo> signList) {
		this.signList = signList;
	}
	public String getSignJson() {
		return signJson;
	}
	public void setSignJson(String signJson) {
		this.signJson = signJson;
	}
	public String getXarray() {
		return xarray;
	}
	public void setXarray(String xarray) {
		this.xarray = xarray;
	}
	public String getYarray() {
		return yarray;
	}
	public void setYarray(String yarray) {
		this.yarray = yarray;
	}
	
}
