package com.shfb.common.rs.info;

public class Znode{
	private String id;
	private String pId;
	private String name;
	private String naclass;
	private String conms;
	
	public Znode() {}

	public Znode(String id, String pId, String name, String naclass,
			String conms) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.naclass = naclass;
		this.conms = conms;
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

	public String getNaclass() {
		return naclass;
	}

	public void setNaclass(String naclass) {
		this.naclass = naclass;
	}

	public String getConms() {
		return conms;
	}

	public void setConms(String conms) {
		this.conms = conms;
	}
	
}
