package com.shfb.common.rs.info;

import java.io.Serializable;

public class CountInfo implements Serializable {
	private static final long serialVersionUID = -4083235285281314387L;
	private String name;
	private int num;
	public CountInfo(){}
	public CountInfo(String name,int num){
		this.name=name;
		this.num=num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
