package com.shfb.admin.rs;

import java.util.List;

import com.shfb.common.rs.dto.BaseDTO;

public class TaskDTO extends BaseDTO {
	private static final long serialVersionUID = 9046787975772039591L;
	private Integer projid;
	private String projname;
	private Integer total;
	private List<UserTaskInfo> list;
	public Integer getProjid() {
		return projid;
	}
	public void setProjid(Integer projid) {
		this.projid = projid;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<UserTaskInfo> getList() {
		return list;
	}
	public void setList(List<UserTaskInfo> list) {
		this.list = list;
	}
	
	
}
