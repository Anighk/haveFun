package com.shfb.common.rs.info;

import java.io.Serializable;

public class IndexInfo implements Serializable {

	private static final long serialVersionUID = 1452711653333521934L;
	
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
