package com.shfb.common.service;

import com.shfb.common.rs.dto.AnlsDTO;
import com.shfb.common.rs.dto.Anls_Name_DTO;

public interface AnalysisService {
	public AnlsDTO anls(String anlsType,String strWhere,String rankNum);
	public Anls_Name_DTO anls_name(String strWhere);
}
