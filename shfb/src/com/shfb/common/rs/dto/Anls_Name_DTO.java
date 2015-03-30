package com.shfb.common.rs.dto;

import java.util.List;

import com.shfb.common.rs.info.Anls_Name;

public class Anls_Name_DTO extends BaseDTO {
	private static final long serialVersionUID = -736614315519465070L;
	private String xarray;
	private String yarray;
	private List<Anls_Name> list;
	
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
	public List<Anls_Name> getList() {
		return list;
	}
	public void setList(List<Anls_Name> list) {
		this.list = list;
	}
	
}
