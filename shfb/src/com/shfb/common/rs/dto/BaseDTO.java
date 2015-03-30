package com.shfb.common.rs.dto;

import java.io.Serializable;

public class BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
