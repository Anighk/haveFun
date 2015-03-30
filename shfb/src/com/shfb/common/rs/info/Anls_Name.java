package com.shfb.common.rs.info;

import java.io.Serializable;
import java.util.Date;

public class Anls_Name implements Serializable {
	private static final long serialVersionUID = 7393404068439299884L;
	private String num;
	private String nick_name;
	private String user_id;
	private String company;
	private String city;
	private String province;
	private Date overdue_date;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Date getOverdue_date() {
		return overdue_date;
	}
	public void setOverdue_date(Date overdue_date) {
		this.overdue_date = overdue_date;
	}
	
	
}
