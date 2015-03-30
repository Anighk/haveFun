package com.shfb.common.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shfb.common.rs.dto.AnlsDTO;
import com.shfb.common.rs.dto.Anls_Name_DTO;
import com.shfb.common.service.AnalysisService;
import com.shfb.common.util.Constant;

@Controller
public class AnalysisAct {
	
	@Autowired
	private AnalysisService analysisService;
	
	@RequestMapping("/anls_member.do")
	public String statistics(HttpServletRequest request,ModelMap modelMap){
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String strWhere =  getCondition(startdt,enddt);
		Anls_Name_DTO dto=analysisService.anls_name(strWhere);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("xarray",dto.getXarray());
		modelMap.addAttribute("yarray", dto.getYarray());
		modelMap.addAttribute("startdt", startdt);
		modelMap.addAttribute("enddt", enddt);
		return Constant.VIEW_PATH+"sys/anls_member.html";
	}
	
	@RequestMapping("/anls.do")
	public String anls(HttpServletRequest request,ModelMap modelMap){
		String type=request.getParameter("type");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String strWhere =  getCondition(startdt,enddt);
		AnlsDTO dto=analysisService.anls(type,strWhere,"");
		modelMap.addAttribute("List",dto.getAnList());
		modelMap.addAttribute("xarray",dto.getXarray());
		modelMap.addAttribute("yarray", dto.getYarray());
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("startdt", startdt);
		modelMap.addAttribute("enddt", enddt);
		return Constant.VIEW_PATH+"sys/anls.html";
	}
	
	private String getCondition(String startdt,String enddt){
		String condition="";
		if(startdt!=null && !"".equals(startdt)){
			condition+=" and psl.search_log >= to_date('"+startdt+"','yyyy-MM-dd') ";
		}
		if(enddt!=null && !"".equals(enddt)){
			condition+=" and psl.search_log <= to_date('"+enddt+"','yyyy-MM-dd') ";
		}
		return condition;
	}
}
