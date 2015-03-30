package com.shfb.common.rs.dto;

import java.util.ArrayList;
import java.util.List;

import com.shfb.common.rs.info.AnlsInfo;

public class AnlsDTO extends BaseDTO {

	private static final long serialVersionUID = -541667896807235389L;
	
	private Integer total;
	private String json;
	private String data;
	private String xarray;
	private String yarray;
	private List<AnlsInfo> anList=new ArrayList<AnlsInfo>();

	public List<AnlsInfo> getAnList() {
		return anList;
	}

	public void setAnList(List<AnlsInfo> anList) {
		this.anList = anList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
