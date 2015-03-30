package com.shfb.admin.rs;

import java.io.Serializable;

public class UserTaskInfo  implements Serializable {
	private static final long serialVersionUID = 8211135405720297246L;
	private Integer userid;
	private String username;
	private String truename;
	private Integer num = 0;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
