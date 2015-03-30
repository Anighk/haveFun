package com.shfb.admin.rs;

import java.io.Serializable;
import java.util.List;

public class MemberInfo implements Serializable {
	private static final long serialVersionUID = -341560522126385578L;
	private Znode_user odept;
	private List<Znode_user> ulist;
	public Znode_user getOdept() {
		return odept;
	}
	public void setOdept(Znode_user odept) {
		this.odept = odept;
	}
	public List<Znode_user> getUlist() {
		return ulist;
	}
	public void setUlist(List<Znode_user> ulist) {
		this.ulist = ulist;
	}
	
}
