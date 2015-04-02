package com.shfb.common.dao;

import com.shfb.common.rs.dto.AnlsDTO;
import com.shfb.common.rs.dto.Anls_Name_DTO;

public interface AnalysisDao {
	public AnlsDTO anls(String anlsType,String strWhere);
	public Anls_Name_DTO anls_name(String strWhere);
}
