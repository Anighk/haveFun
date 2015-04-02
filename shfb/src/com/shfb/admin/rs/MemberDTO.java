package com.shfb.admin.rs;

import java.util.List;

import com.shfb.common.entity.Members;
import com.shfb.common.rs.dto.BaseDTO;

public class MemberDTO extends BaseDTO {
	private static final long serialVersionUID = -2910470285068731221L;
	private List<Members> mList;
	public List<Members> getmList() {
		return mList;
	}
	public void setmList(List<Members> mList) {
		this.mList = mList;
	}
	
}
