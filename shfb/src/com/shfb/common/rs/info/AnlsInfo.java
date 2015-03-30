package com.shfb.common.rs.info;

import java.io.Serializable;

public class AnlsInfo implements Serializable,Comparable<AnlsInfo> {
	private static final long serialVersionUID = -6216009816903136589L;
	private String xAxis;
	private String yAxis;
	private String zAxis;
	public AnlsInfo() {
	}
	
	public AnlsInfo(String xAxis, String yAxis, String zAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.zAxis = zAxis;
	}

	public String getxAxis() {
		return xAxis;
	}
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	public String getyAxis() {
		return yAxis;
	}
	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
	public String getzAxis() {
		return zAxis;
	}
	public void setzAxis(String zAxis) {
		this.zAxis = zAxis;
	}
	@Override
	public int compareTo(AnlsInfo info) {
		return ((Integer)Integer.parseInt(info.getyAxis())).compareTo((Integer)Integer.parseInt(this.yAxis));
	}
}
