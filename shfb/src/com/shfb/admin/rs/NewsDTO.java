package com.shfb.admin.rs;

import java.util.List;

import com.shfb.common.entity.News;
import com.shfb.common.rs.dto.BaseDTO;

public class NewsDTO extends BaseDTO {
	private static final long serialVersionUID = -7700284949210376576L;
	private List<News> list;

	public List<News> getList() {
		return list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}
	
}
