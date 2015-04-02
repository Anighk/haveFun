package com.shfb.common.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shfb.admin.rs.NewsDTO;
import com.shfb.admin.service.AdminService;
import com.shfb.common.util.Constant;

@Controller
public class JumpAct {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/toAddNews.do")
	public String toAddNews(ModelMap modelMap) {
		return Constant.VIEW_PATH + "news/add.html";
	}
	
	@RequestMapping("/toEditNews.do")
	public String toEditNews(HttpServletRequest request,ModelMap modelMap) {
		String id=request.getParameter("id");
		String strWhere=" id='"+id+"'";
		NewsDTO dto=adminService.findNews(strWhere, 1, 1);
		modelMap.addAttribute("info",dto.getList().get(0));
		return Constant.VIEW_PATH + "news/edit.html";
	}

}
