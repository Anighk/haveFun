package com.shfb.common.rs.dto;

import java.io.Serializable;

public class BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer total;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
