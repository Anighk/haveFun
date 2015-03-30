package com.shfb.admin.rs;

public class Znode_user {
	private String id;
	private String pId;
	private String name;
	private Integer uid;
	private String dept;
	private boolean checked=false;
	public Znode_user(){}
	public Znode_user(String id,String pId,String name,Integer uid,String dept){
		this.id=id;
		this.pId=pId;
		this.name=name;
		this.uid=uid;
		this.dept=dept;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
