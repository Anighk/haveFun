package com.shfb.common.rs.info;

import java.io.Serializable;

import com.shfb.common.util.BaseUtil;

public class CountClass implements Serializable {
	
	private static final long serialVersionUID = -3499677088013879503L;
	private String column;
	private String ptype;
	private int num;
	
	public CountClass(){}
	public CountClass(String ptype, int num) {
		super();
		this.ptype=ptype;
		this.column = BaseUtil.getType(ptype);
		this.num = num;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = BaseUtil.getType(column);
	}

	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

	
}
