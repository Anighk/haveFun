package com.shfb.admin.rs;

import java.util.List;

import com.shfb.common.entity.Renew;
import com.shfb.common.rs.dto.BaseDTO;

public class RenewDTO extends BaseDTO {
	private static final long serialVersionUID = -8981934734421534534L;
	public List<Renew>  list;
	public List<Renew> getList() {
		return list;
	}
	public void setList(List<Renew> list) {
		this.list = list;
	}
	
}
