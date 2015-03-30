package com.shfb.admin.rs;

import java.util.List;

import com.shfb.common.entity.Renew;
import com.shfb.common.rs.dto.BaseDTO;

public class RenewDTO extends BaseDTO {
	private static final long serialVersionUID = -8981934734421534534L;
	private Integer total;
	public List<Renew>  list;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Renew> getList() {
		return list;
	}
	public void setList(List<Renew> list) {
		this.list = list;
	}
	
}
